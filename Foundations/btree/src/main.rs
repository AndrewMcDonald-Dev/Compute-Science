#[derive(Debug)]
struct Node {
    idx: usize,
    keys: Vec<i32>,
    parent: Option<usize>,
    children: Vec<usize>,
}

impl Node {
    fn new(idx: usize, keys: Vec<i32>, children: Vec<usize>, parent: Option<usize>) -> Self {
        Node {
            idx,
            keys,
            children,
            parent,
        }
    }
}

//Memory Arena
struct BTree {
    nodes: Vec<Node>,
}

impl BTree {
    fn add(&mut self, keys: Vec<i32>, children: Vec<usize>, parent: Option<usize>) -> usize {
        let idx = self.nodes.len();
        self.nodes.push(Node::new(idx, keys, children, parent));
        idx
    }

    fn insert(&mut self, value: i32) -> usize {
        // Find where to insert value

        // Insert value into that node
        // Check if node has hit limit and if so split
        todo!()
    }
}

fn main() {}
