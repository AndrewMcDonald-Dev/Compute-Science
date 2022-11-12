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
            line = Regex::new(" +")
                .unwrap()
                .replace(line.trim(), " ")
                .to_string();

            //gets rid of everything after $
            line = line.split_inclusive("$").take(1).collect::<Vec<_>>()[0].to_string();
            println!("The expressions to be evaluated is {}\n", line);

            //assignment
            line = assign_variables(&line);

            //evaluate
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
    let mut assignd: Vec<i32> = Vec::new();
    let mut result: Vec<String> = vec!["".to_string(); tokens.len()];

    for (i, token) in tokens.iter().enumerate() {
        if Regex::new("^[a-z]+$").unwrap().is_match(token) {
            if !variable.contains(&token.to_string()) {
                print!("Enter the value of {} > ", token);
                std::io::stdout().flush().expect("Error writing message.");
                let mut line = String::new();
                std::io::stdin().read_line(&mut line).expect("Bad Input.");

                match line.trim().parse::<i32>() {
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
    let tokens = exp.split_whitespace().collect::<Vec<&str>>();
    let mut stack: Vec<isize> = Vec::new();

    let mut result: Result<String, String> = Ok("-1".to_string());

    for token in tokens {
        if Regex::new("-?[0-9]+").unwrap().is_match(token) {
            match token.trim().parse::<isize>() {
                Ok(value) => {
                    stack.push(value);
                }
                Err(_) => {
                    result = Err("Could not parse isize from number.".to_string());
                    break;
                }
            }
        } else {
            match token {
                "+" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push(left + right),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "-" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push(left - right),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "/" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => {
                            if right == 0 {
                                result = Err(format!("Cannot divide by zero."));
                                break;
                            }
                            stack.push(left / right);
                        }
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "*" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push(left * right),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "_" => {
                    let operand = stack.pop();
                    match operand {
                        Some(operand) => stack.push(-operand),
                        None => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operand.", token));
                            break;
                        }
                    }
                }
                "!" => {
                    let mut factorial = 1;
                    let operand = stack.pop();
                    match operand {
                        Some(operand) => {
                            if operand < 0 {
                                result =
                                    Err(format!("Cannot take the factorial of a negative number."));
                                break;
                            }
                            for i in 1..(operand + 1) {
                                factorial *= i;
                            }
                            stack.push(factorial);
                        }
                        None => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operand.", token));
                            break;
                        }
                    }
                }
                "#" => {
                    let operand = stack.pop();
                    match operand {
                        Some(operand) => {
                            if operand < 0 {
                                result = Err(format!("Cannot take the sqrt of a negative number."));
                                break;
                            }
                            stack.push((operand as f64).sqrt() as isize);
                        }
                        None => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "^" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => {
                            stack.push(left.pow(right.try_into().unwrap()))
                        }

                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "<" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push((left < right) as isize),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                ">" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push((left > right) as isize),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "<=" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push((left <= right) as isize),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                ">=" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push((left >= right) as isize),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "==" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push((left == right) as isize),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "!=" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => stack.push((left != right) as isize),
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "&&" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => {
                            stack.push(((left != 0) && (right != 0)) as isize)
                        }
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "||" => {
                    let right = stack.pop();
                    let left = stack.pop();
                    match (right, left) {
                        (Some(right), Some(left)) => {
                            stack.push(((left != 0) || (right != 0)) as isize)
                        }
                        _ => {
                            result =
                                Err(format!("OPERATOR: {} could not find its operands.", token));
                            break;
                        }
                    }
                }
                "$" => {
                    let res = stack.pop();
                    result = match res {
                        Some(result) => Ok(result.to_string()),
                        None => Err("Error with writing result or $.".to_string()),
                    }
                }
                _ => {
                    result = Err("Could not parse token".to_string());
                    break;
                }
            }
        }
    }
    result
}
