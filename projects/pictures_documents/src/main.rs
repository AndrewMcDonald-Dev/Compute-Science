use mysql::prelude::*;
use mysql::*;
use regex::Regex;
use std::error::Error;
use std::result::Result;

struct Location {
    full_content: String,
    cid: u32,
}

fn main() -> Result<(), Box<dyn Error>> {
    let url = "mysql://root@localhost:3306/map_newpaltz";
    let pool = Pool::new(url)?;

    let mut conn = pool.get_conn()?;

    // Pull relevant data from table and map onto objects
    let pull_query = "SELECT full_content, cid FROM a_all;";
    let locations = conn
        .query_map(pull_query, |(full_content, cid)| Location {
            full_content,
            cid,
        })
        .expect("Could not pull correct data");

    // Find relevant part of fulltext and isolate it
    let re_pic = Regex::new(r#"(?m)<[img|IMG][^>]*src="([^"]+)"[^>]*>"#).unwrap();
    let re_doc = Regex::new(r"images/hpc_data/FullSurvey.xls").unwrap();
    let re_map = Regex::new(r"center=\d{2}.\d*,-\d{2}.\d*").unwrap();

    locations.iter().for_each(|loc| {
        let res_pic = re_pic.captures_iter(&loc.full_content);
        let res_doc = re_doc.captures(&loc.full_content).is_some();

        let mut images: Vec<&str> = Vec::new();

        for mat in res_pic {
            let image = mat.get(1).unwrap().as_str();

            if re_map.find(image).is_none() {
                images.push(mat.get(1).unwrap().as_str());
            }
        }

        let update_query = if res_doc {
            format!(
                "UPDATE a_all SET picture_links=\"{}\", document_links=\"{}\" WHERE cid={}",
                images.join("|"),
                "images/hpc_data/FullSurvey.xls",
                loc.cid
            )
        } else {
            format!(
                "UPDATE a_all SET picture_links=\"{}\" WHERE cid={}",
                images.join("|"),
                loc.cid
            )
        };

        conn.query_drop(update_query).unwrap_or_else(|err| {
            panic!("Could not update {} in database. Because {}.", loc.cid, err)
        });
    });
    Ok(())
}
