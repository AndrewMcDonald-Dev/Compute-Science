use std::fmt::Debug;
fn main() {
    let left = Node::new(6, None, None);
    let right = Node::new(12, None, None);
    let mut root = Node::new(8, Some(left), Some(right));

    Node::add_to_bst(&mut root, 19);
    // println!("New: {:?}", root.right.unwrap().right.unwrap())
    Node::print_tree(&root, &root);
    println!("{}", Node::search(&root, 5));
}

#[derive(Debug)]
struct Node<E: Ord> {
    left: Option<Box<Node<E>>>,
    right: Option<Box<Node<E>>>,
    value: E,
}

impl<E: Ord + Debug> Node<E> {
    fn new(value: E, left: Option<Box<Node<E>>>, right: Option<Box<Node<E>>>) -> Box<Node<E>> {
        Box::new(Node { left, right, value })
    }
    fn same<T>(a: *const T, b: *const T) -> bool {
        a == b
    }
    fn set_left(&mut self, new_left: Option<Box<Node<E>>>) -> Option<Box<Node<E>>> {
        std::mem::replace(&mut self.left, new_left)
    }
    fn set_right(&mut self, new_right: Option<Box<Node<E>>>) -> Option<Box<Node<E>>> {
        std::mem::replace(&mut self.right, new_right)
    }
    fn add_to_bst(root: &mut Node<E>, value: E) {
        if value < root.value {
            if root.left.is_some() {
                Self::add_to_bst(&mut root.left.as_mut().unwrap(), value);
            } else {
                root.set_left(Some(Node::new(value, None, None)));
            }
        } else {
            if root.right.is_some() {
                Self::add_to_bst(&mut root.right.as_mut().unwrap(), value);
            } else {
                root.set_right(Some(Node::new(value, None, None)));
            }
        }
    }
    fn search(root: &Node<E>, value: E) -> bool {
        if value == root.value {
            return true;
        }
        if root.left.is_some() && value < root.value {
            return Self::search(root.left.as_ref().unwrap(), value);
        }
        if root.right.is_some() && value > root.value {
            return Self::search(root.right.as_ref().unwrap(), value);
        }
        false
    }
    fn depth(root: &Node<E>, key: &Node<E>) -> isize {
        if Node::<i32>::same(root, key) {
            return 0;
        }

        let mut left = -1;
        let mut right = -1;

        if root.left.is_some() {
            left = Self::depth(root.left.as_ref().unwrap(), key);
        }
        if root.right.is_some() {
            right = Self::depth(root.right.as_ref().unwrap(), key);
        }

        if left != -1 {
            return left + 1;
        }
        if right != -1 {
            return right + 1;
        }
        -1
    }
    pub fn print_tree(root: &Node<E>, first_root: &Node<E>) {
        if root.right.is_some() {
            Self::print_tree(root.right.as_ref().unwrap(), first_root);
        }

        for _ in 0..(Self::depth(first_root, root) * 4) {
            print!(" ")
        }
        println!("{:?}", root.value);

        if root.left.is_some() {
            Self::print_tree(root.left.as_ref().unwrap(), first_root);
        }
    }
}
