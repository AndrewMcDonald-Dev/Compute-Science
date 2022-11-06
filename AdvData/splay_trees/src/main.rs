mod node;
mod tree;

use std::{
    fs::File,
    io::{BufRead, BufReader},
};

use crate::tree::Tree;

fn main() {
    let file = File::open("in.dat").expect("Could not open in.dat");
    let reader = BufReader::new(file).lines();

    let mut tree = None;

    for line in reader {
        tree = match line {
            Ok(s) => Some(Tree::insert(&mut tree, s.parse::<i32>().unwrap())),
            Err(_) => panic!("Could not properly read input"),
        };
    }

    match &tree {
        Some(t) => Tree::print_tree(t, t),
        None => panic!("Unable to draw tree"),
    }
    println!();
    println!();
    println!();

    match Tree::find(&tree, 89) {
        Some(node) => println!("{}", node.value),
        None => (),
    }

    tree = Tree::splay(&tree, 51);
    match &tree {
        Some(t) => Tree::print_tree(t, t),
        None => panic!("Unable to draw tree"),
    }
}
