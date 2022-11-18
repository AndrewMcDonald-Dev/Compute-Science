mod node;
mod tree;

use regex::Regex;
use std::{
    fs::File,
    io::{stdin, BufRead, BufReader, Write},
};

use crate::tree::Tree;

const SPLAY: &str = "S";
const FIND: &str = "F";
const INSERT: &str = "I";
const DELETE: &str = "D";
const END: &str = "END";

fn main() {
    let file = File::open("in.dat").expect("Could not open in.dat");
    let reader = BufReader::new(file).lines();

    let mut tree = None;

    for line in reader {
        tree = match line {
            Ok(s) => Tree::insert(&mut tree, s.parse::<i32>().unwrap()),
            Err(_) => panic!("Could not properly read input"),
        };
    }

    println!("Starting Tree:");
    println!();
    Tree::print_tree(tree.as_ref().unwrap(), tree.as_ref().unwrap());
    println!();

    loop {
        print!("Enter Command: ");
        std::io::stdout().flush().expect("Error writing message.");
        let mut command = String::new();
        stdin()
            .read_line(&mut command)
            .expect("Failed to read input.");

        let splits: Vec<&str> = Regex::new(" +")
            .expect("Bad regex.")
            .split(&command)
            .collect();
        if splits.len() > 1 {
            let (command, key) = (splits[0], splits[1]);
            // if {
            match command {
                SPLAY => {
                    let key: i32 = match key.trim().parse() {
                        Ok(num) => num,
                        Err(_) => {
                            println!("Bad key.");
                            continue;
                        }
                    };
                    tree = Tree::splay(&tree, key);
                    println!("Splay is done.");
                    if let Some(tree) = tree.clone() {
                        Tree::print_tree(&tree, &tree)
                    }
                }
                FIND => {
                    let key: i32 = match key.trim().parse() {
                        Ok(num) => num,
                        Err(_) => {
                            println!("Bad key.");
                            continue;
                        }
                    };
                    tree = Tree::splay(&tree, key);
                    match tree.clone() {
                        Some(root) => {
                            if root.value == key {
                                println!("Search is successful")
                            }else {
                                println!("Search is unsuccessful.")
                            }
                        },
                        None => println!("Search is unsuccessful."),
                    }
                    if let Some(tree) = tree.clone() {
                        Tree::print_tree(&tree, &tree);
                    }
                }
                INSERT => {
                    let key: i32 = match key.trim().parse() {
                        Ok(num) => num,
                        Err(_) => {
                            println!("Bad key.");
                            continue;
                        }
                    };
                    tree = Tree::splay_insert(tree, key);
                    match tree {
                        Some(_) => println!("The key is inserted into the tree."),
                        None => println!("Duplicate Keys."),
                    };
                    if let Some(tree) = tree.clone() {
                        Tree::print_tree(&tree, &tree)
                    }
                }
                DELETE => {
                    let key: i32 = match key.trim().parse() {
                        Ok(num) => num,
                        Err(_) => {
                            println!("Bad key.");
                            continue;
                        }
                    };
                    tree = Tree::delete(tree.clone(), key); 
                    match tree{
                        Some(_) => {
                            println!("The key is deleted from the tree.");
                        }
                        None => println!("The key is not in the tree."),
                    }
                    if let Some(tree) = tree.clone() {
                        Tree::print_tree(&tree, &tree)
                    }
                }
                END => {
                    println!("Thank you for using this tool!");
                    break;
                }
                _ => {
                    println!("Bad command.");
                    continue;
                }
            }
        } else {
            println!("Could not read command. Not enough arguments");
            continue;
        }
        println!();
    }
}
