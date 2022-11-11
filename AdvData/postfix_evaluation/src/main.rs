use regex::Regex;
use std::fs::File;
use std::io;
use std::io::prelude::*;
fn main() {
    println!("Hello! This is a postfix form expression calculator.");

    let file = File::open("in.dat").expect("Could not find file");
    let buf_reader = io::BufReader::new(file);

    for line in buf_reader.lines() {
        if let Ok(mut line) = line {
            //trim all whitespace
            line = line.trim().replace(" +", " ");

            //gets rid of everything after $
            line = line.split("$").take(1).collect::<Vec<_>>()[0].to_string();
            println!("The expressions to be evaluated is {}\n", line);

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
    let tokens = &exp.split_whitespace().collect::<Vec<&str>>();
    let mut variable: Vec<String> = Vec::new();
    let mut assignd: Vec<u32> = Vec::new();
    let mut result: Vec<String> = vec!["".to_string(); tokens.len()];

    for (i,token) in tokens.iter().enumerate() {
        if Regex::new("^[a-z]+$").unwrap().is_match(token) {
            if !variable.contains(&token.to_string()) {
                print!("Enter the value of {} > ", token);
                std::io::stdout().flush().expect("Error writing message.");
                let mut line = String::new();
                std::io::stdin().read_line(&mut line).expect("Bad Input.");

                match line.trim().parse::<u32>() {
                    Ok(value) => {
                        result[i] = value.to_string();
                        variable.push(token.to_string());
                        assignd.push(value);
                    }
                    Err(_) => panic!("Bad Input. Entered string as int."),
                }
            } else {
                let j = variable.iter().position(|r| r == token).unwrap();
                result[i] = assignd[j].to_string();
            }
        } else {
            result[i] = token.to_string();
        }
    }
    println!();
    result.join(" ")
}

fn evaluate_exp(exp: &str) -> Result<String, String> {
    Ok(exp.to_string())
}
