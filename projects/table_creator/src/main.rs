use itertools::Itertools;
use mysql::prelude::*;
use mysql::*;
use regex::Regex;
use std::collections::HashMap;
use std::error::Error;
use std::result::Result;

struct Location {
    id: u32,
    title: String,
	introtext: String,
    fulltext: String,
}

fn main() -> Result<(), Box<dyn Error>> {
    let url = "mysql://root@localhost:3306/map_newpaltz";
    let pool = Pool::new(url)?;

    let mut conn = pool.get_conn()?;

    // Create a new tables if they do not exist
    let init_query1 = "CREATE TABLE IF NOT EXISTS a_items(`id` INT(11) NOT NULL,`cid` INT(11) NOT NULL AUTO_INCREMENT,`title` VARCHAR(255) NOT NULL,`lat` DECIMAL(15, 13) NOT NULL,`lng` DECIMAL(15,13) NOT NULL, `content` mediumtext, `data` mediumtext,  PRIMARY KEY (`cid`));";
    conn.query_drop(init_query1)?;
    let init_query2 = "CREATE TABLE IF NOT EXISTS a_pages(`id` INT(11) NOT NULL,`cid` INT(11) NOT NULL AUTO_INCREMENT,`title` VARCHAR(255) NOT NULL, `content` mediumtext, `data` mediumtext,  PRIMARY KEY (`cid`));";
    conn.query_drop(init_query2)?;

    // Pull relevant data from table and map onto objects
    let pull_query = "SELECT `id`, `title`, `introtext`, `fulltext` FROM jos_content;";
    let locations = conn
        .query_map(pull_query, |(id, title,introtext,  fulltext)| Location {
            id,
            title,
			introtext,
            fulltext,
        })
        .expect("Could not pull correct data");

    // Find relevant part of fulltext and isolate it
    let re = Regex::new(r"center=\d{2}.\d*,-\d{2}.\d*").unwrap();

    locations.iter().for_each(|loc| {
		// println!("{}, {}", loc.title, loc.id);
		let text = &loc.fulltext;
		// let intro = if loc.introtext.is_some() { loc.introtext.clone().unwrap()} else {"".to_string()}; 
            let (lat, lng) = match re.find(text) {
                Some(mat) => {
                    let temp = mat.as_str().replace("center=", "");
                    let unparsed_input: (&str, &str) = temp.split(',').collect_tuple().unwrap();
                    (
                        unparsed_input.0.parse::<f64>().unwrap(),
                        unparsed_input.1.parse::<f64>().unwrap(),
                    )
                }
                None => (0.0,0.0),
                
            };
			//Pull json data and serialize it
			let data = pull_data(text);
			let serialized = serde_json::to_string(&data).expect("Could not serialize data.").replace('\"', "\\\"").replace("\\\\\"", "\\\"");

			// Combine introtext and fulltext into one

			let content = loc.introtext.clone() + text;
			let content = content.replace('\"', "\\\"").replace("\\\\\"", "\\\"");
            if (lat, lng) != (0.0, 0.0) {
                let insert_query = 
                    format!(
						"INSERT INTO a_items(`id`, `title`, `lat`, `lng`, `content`, `data`) VALUES({},\"{}\",{},{},\"{}\",\"{}\");",
						loc.id, loc.title, lat, lng, content, serialized 
					);
                conn.query_drop(insert_query)
                    .unwrap_or_else(|err| panic!("Could not insert {} into database. Because {}.", loc.title, err));
            } else {
                let insert_query =
                        format!(
                    "INSERT INTO a_pages(`id`, `title`, `content`, `data`) VALUES({},\"{}\",\"{}\",\"{}\");",
                    loc.id, loc.title, content, serialized
                );
                conn.query_drop(insert_query)
                    .unwrap_or_else(|err| panic!("Could not insert {} into database. Because {}.", loc.title, err));
            }
    });
    Ok(())
}

fn pull_data(text: &str) -> HashMap<String, String> {
    let re = Regex::new(r">.*</td>").unwrap();
	let mut hash = HashMap::new();

	//Some entries are all on one long and that breaks the regex Crate
	//These lines fix the input for the regex
	let text = text.replace("</td>", "</td>\n");
	let text = text.replace("<tr>", "<tr>\n");

	let chunked_items: Vec<Vec<String>> = re
		.find_iter(&text)
		.chunks(2)
		.into_iter()
		.map(|chunk| chunk.map(|mat| mat.as_str().to_string()).collect())
		.collect();


	for mut item in chunked_items {
		if item.len() == 2  {
			let len1 = item[0].len();
			let len2 = item[1].len();
			item[0].truncate(len1 - 5);
			item[0].remove(0);
			item[1].truncate(len2 - 5);
			item[1].remove(0);
			// println!("{}, {}", item[0], item[1]);
			hash.insert(item[0].clone(), item[1].clone());
		}
	}
	hash
}
