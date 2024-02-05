use mysql::prelude::*;
use mysql::*;

fn main() -> Result<()> {
    let csv =
        std::fs::read_to_string("FullSurvey.csv").expect("File not found or could not be read.");

    let mut reader = csv::Reader::from_reader(csv.as_bytes());

    for record in reader.records() {
        let record = match record {
            Ok(rec) => rec,
            Err(_) => panic!("Could not parse record from csv."),
        };

        let data = format!(
            r#"{{"Tax parcel Number":"{}","Street number":"{}","Street Name":"{}","Present Use":"{}","Historic Function":"{}","Historic Use":"{}","Date of Construction":"{}","Dates of alteration":"{}","Style":"{}","No. of Stories":"{}","Construction method":"{}","Exterior material: historic 1":"{}","Exterior material: historic 2":"{}","Exterior material: present":"{}","Roof Type":"{}","Roof Material":"{}","Foundation Material":"{}","Additions":"{}","Porch location 1":"{}","Porch condition":"{}","Window condition":"{}","Overall integrity":"{}","Contributing":"{}","Endangered":"{}","Distinctive features":"{}","Notes":"{}","Outbldg 1 type":"{}","Outbldg 1 date":"{}","Outbldg 1 construction method":"{}","Outbldg 1 distinctive features":"{}","Outbldg 2 type":"{}","Outbldg 2 date":"{}","Landscape features":"{}"}}"#,
            &record[0].replace('\"', "\\\""),
			&record[1].replace('\"', "\\\""),
            &record[2].replace('\"', "\\\""),
            &record[3].replace('\"', "\\\""),
            &record[4].replace('\"', "\\\""),
            &record[5].replace('\"', "\\\""),
            &record[6].replace('\"', "\\\""),
            &record[7].replace('\"', "\\\""),
            &record[8].replace('\"', "\\\""),
            &record[9].replace('\"', "\\\""),
            &record[10].replace('\"', "\\\""),
            &record[11].replace('\"', "\\\""),
            &record[12].replace('\"', "\\\""),
            &record[13].replace('\"', "\\\""),
            &record[14].replace('\"', "\\\""),
            &record[15].replace('\"', "\\\""),
            &record[16].replace('\"', "\\\""),
            &record[17].replace('\"', "\\\""),
            &record[18].replace('\"', "\\\""),
            &record[19].replace('\"', "\\\""),
            &record[20].replace('\"', "\\\""),
            &record[21].replace('\"', "\\\""),
            &record[22].replace('\"', "\\\""),
            &record[23].replace('\"', "\\\""),
            &record[24].replace('\"', "\\\""),
            &record[25].replace('\"', "\\\""),
            &record[26].replace('\"', "\\\""),
            &record[27].replace('\"', "\\\""),
            &record[28].replace('\"', "\\\""),
            &record[29].replace('\"', "\\\""),
            &record[30].replace('\"', "\\\""),
            &record[31].replace('\"', "\\\""),
            &record[32].replace('\"', "\\\""),
        )
		.replace('\"', "\\\"")
		.replace("\\\\\"", "\\\\\\\"");

        let title = if record[1].is_empty() {
            record[2].to_string()
        } else {
            format!("{} {}", &record[1], &record[2])
        };

        let url = "mysql://root@localhost:3306/map_newpaltz";
        let pool = Pool::new(url)?;

        let mut conn = pool.get_conn()?;

        let pull_query = format!(r#"SELECT `title` FROM a_all WHERE `title`="{}";"#, title);
        let location: Option<String> = conn
            .query_first(pull_query)
            .expect("Could not pull correct data");

        if let Some(loc_title) = location {
            let update_query = format!(
                "UPDATE a_all_copy SET data=\"{}\" WHERE title=\"{}\"",
                data, loc_title
            );

            conn.query_drop(update_query).unwrap_or_else(|err| {
                panic!(
                    "Could not update {} in database. Because {}.",
                    loc_title, err
                )
            });
        } else {
            println!("{} ERRORERRORERRORERRORERRORERRORERROR", title);
        }
    }

    Ok(())
}
