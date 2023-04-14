use mysql::prelude::*;
use mysql::*;
use regex::Regex;
use std::error::Error;
use std::result::Result;

struct Location {
    id: u32,
    cid: u32,
    title: String,
    content: String,
    lat: String,
    lng: String,
    data: String,
    t: u8,
}

fn main() -> Result<(), Box<dyn Error>> {
    let url = "mysql://root@localhost:3306/map_newpaltz";
    let pool = Pool::new(url)?;

    let mut conn = pool.get_conn()?;

    // Create a new tables if they do not exist
    let init_query = "CREATE TABLE IF NOT EXISTS a_all(`id` INT(11) NOT NULL,`cid` INT(11) NOT NULL AUTO_INCREMENT,`title` VARCHAR(255) NOT NULL,`lat` DECIMAL(15, 13) NOT NULL,`lng` DECIMAL(15,13) NOT NULL, `full_content` mediumtext, `main_content` mediumtext, `report` mediumtext, `data` mediumtext, `type` INT(11) NOT NULL, `document_links` mediumtext, `picture_links` mediumtext,  PRIMARY KEY (`cid`));";
    conn.query_drop(init_query)?;

    // Pull relevant data from table and map onto objects
    let pull_query =
        "SELECT `id`, `cid`, `title`, `content`, `lat`, `lng`, `data`, `type`  FROM a_allitems;";
    let locations = conn
        .query_map(
            pull_query,
            |(id, cid, title, content, lat, lng, data, t)| Location {
                id,
                cid,
                title,
                content,
                lat,
                lng,
                data,
                t,
            },
        )
        .expect("Could not pull correct data");

    let re_table = Regex::new(r"</table>").unwrap();
    let re_map = Regex::new(r"center=\d{2}.\d*,-\d{2}.\d*").unwrap();
    let re_report =
        Regex::new("HISTORIC AND NATURAL DISTRICT INVENTORY FORM|HISTORIC RESOURCE INVENTORY FORM")
            .unwrap();
    let re_extra = Regex::new("The date of construction on this property could not be verified by the existing historic record.").unwrap();
    // Split content based on <hr>
    locations.iter().for_each(|loc| {
        let mut report = "";
        let mut main_content = String::new();
        let document_links = "".to_string();
        let picture_links = "".to_string();
        for section in loc.content.split("<hr>") {
            // Determine if given section is a table or map and ignores it
            if (re_table.find(section).is_some() && re_report.find(section).is_none())
                || re_map.find(section).is_some()
                || section.is_empty()
            {
            } else if re_report.find(section).is_some() {
                report = section;
            } else if re_extra.find(section).is_some() {
                main_content += "<br>";
                main_content += section;
            } else {
                main_content += section;
            }
            // picture_links += &grab_images(section);
        }
        let insert_query =
            format!(
        		"INSERT INTO a_all(`id`, `cid`, `title`, `lat`, `lng`, `full_content`, `main_content`, `report`, `data`, `type`, `document_links`, `picture_links`) VALUES({},{},\"{}\",{},{},\"{}\",\"{}\",\"{}\",\"{}\",{},\"{}\",\"{}\");",
        		loc.id, loc.cid, loc.title, loc.lat, loc.lng, loc.content.replace('\"', "\\\"").replace("\\\\\"", "\\\""), main_content.replace('\"', "\\\"").replace("\\\\\"", "\\\""), report.replace('\"', "\\\"").replace("\\\\\"", "\\\""), loc.data.replace('\"', "\\\"").replace("\\\\\"", "\\\""), loc.t, document_links, picture_links
        	);
        conn.query_drop(insert_query)
            .unwrap_or_else(|err| panic!("Could not insert {} into database. Because {}.", loc.title, err));
    });
    // If section has images save in csv fromat with possible desc text alongside it. If no desc put empty string.
    // println!("{}", grab_images("<img src=\"/images/rsgallery/original/STL_23.jpg\" alt=\"89 and 91 Main (1)\" width=\"745\" />"));
    Ok(())
}

fn _grab_images(section: &str) -> String {
    // Build regex for finding images
    let re_src = Regex::new(r#"src="/?images/(.*/.*)*\.(jpg|png)""#).unwrap();

    //Apply regex and grab all matches
    // Return as string for csv
    let mut csv = "".to_string();
    re_src.find_iter(section).for_each(|src| {
        let mut src = src.as_str().to_string();
        src = src.replace(" src=\"", "");
        src = src.replace("\" ", "");
        csv += &src;
        csv += ",";
    });
    csv
}
