use std::io;
use std::fs::File;
use std::io::prelude::*;
fn main() {
    println!("Hello! This is a postfix form expression calculator.");

    let file = File::open("in.dat").expect("Could not find file");
    let buf_reader =io::BufReader::new(file);

    for line in buf_reader.lines() {
        if let Ok(mut line) = line {
            //trim all whitespace
            line = line.trim().replace(" +", " ");

            //gets rid of everything after $
            line = line.split("$").take(1).collect::<Vec<_>>()[0].to_string();
            println!("The expressions tob e evaluated is {}\n", line );

            //assignment
            line = assign_variables(&line);

            let result = evaluate_exp(&line);
            match result {
                Ok(exp) => println!("The value of this expression is {}.", exp),
                Err(err) => println!("{}", err),
            }
            println!()
        }
    }
}

fn assign_variables(exp: &str) -> String {
    exp.to_string()
}

fn evaluate_exp(exp: &str) -> Result<String, String> {
    Ok(exp.to_string())
}