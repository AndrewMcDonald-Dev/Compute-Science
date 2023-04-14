use mysql::prelude::*;
use mysql::*;
use std::error::Error;
use std::result::Result;

struct Item {
    id: u32,
    title: String,
	lat: String,
	lng:String,
	content: String,
	data: String
}
struct Page {
    id: u32,
    title: String,
	content: String,
	data: String
}

fn main() -> Result<(), Box<dyn Error>> {
    let url = "mysql://root@localhost:3306/map_newpaltz";
    let pool = Pool::new(url)?;

    let mut conn = pool.get_conn()?;

    // Create a new tables if they do not exist
    let init_query = "CREATE TABLE IF NOT EXISTS a_allitems(`id` INT(11) NOT NULL,`cid` INT(11) NOT NULL AUTO_INCREMENT,`title` VARCHAR(255) NOT NULL,`lat` DECIMAL(15, 13) NOT NULL,`lng` DECIMAL(15,13) NOT NULL, `content` mediumtext, `data` mediumtext, `type` INT(11) NOT NULL, `document_links` mediumtext, `picture_links` mediumtext,  PRIMARY KEY (`cid`));";
    conn.query_drop(init_query)?;

    // Pull relevant data from a_items table and map onto objects
    let pull_query_items = "SELECT `id`, `title`, `content`, `lat`, `lng`, `data` FROM a_items;";
    let items = conn
        .query_map(pull_query_items, |(id, title, content, lat, lng, data)| Item {
            id,
            title,
			content,
			lat,
			lng,
			data
        })
        .expect("Could not pull correct data");

    // Pull relevant data from a_pages table and map onto objects
    let pull_query_pages = "SELECT `id`, `title`, `content`, `data` FROM a_pages;";
    let pages = conn
        .query_map(pull_query_pages, |(id, title, content, data)| Page {
            id,
            title,
			content,
			data
        })
        .expect("Could not pull correct data");

	// Insert all rows from a_items into a_allitems
    items.iter().for_each(|item| {
        let insert_query = 
            format!(
				"INSERT INTO a_allitems(`id`, `title`, `lat`, `lng`, `content`, `data`, `type`) VALUES({},\"{}\",{},{},\"{}\",\"{}\",{});",
				item.id, item.title, item.lat, item.lng, item.content.replace('\"', "\\\""), item.data.replace('\"', "\\\""), 0
			);
        conn.query_drop(insert_query)
            .unwrap_or_else(|err| panic!("Could not insert {} into database. Because {}.", item.title, err));
    });

	// Insert all rows from a_pages into a_allitems
    pages.iter().for_each(|page| {
        let insert_query = 
            format!(
				"INSERT INTO a_allitems(`id`, `title`, `lat`, `lng`, `content`, `data`, `type`) VALUES({},\"{}\",{},{},\"{}\",\"{}\",{});",
				page.id, page.title, 0.0, 0.0, page.content.replace('\"', "\\\""), page.data.replace('\"', "\\\""), 1
			);
        conn.query_drop(insert_query)
            .unwrap_or_else(|err| panic!("Could not insert {} into database. Because {}.", page.title, err));
    });

    Ok(())
}


