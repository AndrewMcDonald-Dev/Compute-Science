use super::node::Node;
use std::cmp::Ordering;

pub struct Tree {}

impl Tree {
    pub fn depth(root: &Box<Node<i32>>, key: &Box<Node<i32>>) -> isize {
        if Node::<i32>::same(root, key) {
            return 0;
        }

        let mut left = -1;
        let mut right = -1;

        match root.left {
            Some(_) => left = Self::depth(root.left.as_ref().unwrap(), &key),
            None => (),
        };
        match root.right {
            Some(_) => right = Self::depth(root.right.as_ref().unwrap(), &key),
            None => (),
        }

        if left != -1 {
            return left + 1;
        }
        if right != -1 {
            return right + 1;
        }
        -1
    }

    pub fn print_tree(root: &Box<Node<i32>>, first_root: &Box<Node<i32>>) {
        match root.right {
            Some(_) => Self::print_tree(root.right.as_ref().unwrap(), first_root),
            None => (),
        }
        for _ in 0..(Self::depth(first_root, root) * 4) {
            print!(" ");
        }
        println!("{:?}", root.value);
        match root.left {
            Some(_) => Self::print_tree(root.left.as_ref().unwrap(), first_root),
            None => (),
        }
    }

    pub fn insert(root: &mut Option<Box<Node<i32>>>, key: i32) -> Box<Node<i32>> {
        match root {
            Some(r) => {
                match key.cmp(&r.value) {
                    Ordering::Less => r.left = Some(Self::insert(&mut r.left, key)),
                    Ordering::Greater => r.right = Some(Self::insert(&mut r.right, key)),
                    Ordering::Equal => (),
                };
                r.clone()
            }
            None => Node::new(key, None, None),
        }
    }

    pub fn splay_insert(mut root: Option<Box<Node<i32>>>, key: i32) -> Box<Node<i32>> {
        root = Self::splay(&root, key);
        match root {
            Some(mut root) => match key.cmp(&root.value) {
                Ordering::Less => Node::new(key, root.left.clone(), Some(root.clone())),
                Ordering::Equal => {
                    root.value = key;
                    root
                }
                Ordering::Greater => Node::new(key, Some(root.clone()), root.right.clone()),
            },

            None => Node::new(key, None, None),
        }
    }

    pub fn find(root: &Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
        match root {
            Some(root) => match key.cmp(&root.value) {
                Ordering::Less => Self::find(&root.left, key),
                Ordering::Equal => Some((*root).clone()),
                Ordering::Greater => Self::find(&root.right, key),
            },
            None => None,
        }
    }

    pub fn right_rotate(root: &mut Box<Node<i32>>) -> Box<Node<i32>> {
        let mut temp = root.left.as_ref().unwrap().clone();
        root.left = temp.right;
        temp.right = Some(root.clone());
        temp
    }

    pub fn left_rotate(root: &mut Box<Node<i32>>) -> Box<Node<i32>> {
        let mut temp = root.right.as_ref().unwrap().clone();
        root.right = temp.left;
        temp.left = Some(root.clone());
        temp
    }

    pub fn splay(root: &Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
        match root.clone() {
            None => None,
            Some(mut root2) => match key.cmp(&root2.value) {
                Ordering::Less => match root2.left.clone() {
                    Some(mut left) => {
                        println!("{:?}", key.cmp(&left.value));
                        match key.cmp(&left.value) {
                            Ordering::Less => {
                                //zig zig
                                left.left = Self::splay(&left.left, key);
                                root2 = Self::right_rotate(&mut root2);
                            }
                            Ordering::Greater => {
                                // zig zag
                                left.right = Self::splay(&left.right, key);
                                // if let Some(_) = &left.right {
                                match left.right {
                                    Some(_) => left = Self::left_rotate(&mut left),
                                    None => (),
                                }
                            }
                            Ordering::Equal => (),
                        };

                        match root2.left {
                            Some(_) => Some(Self::right_rotate(&mut root2)),
                            None => Some(root2), //zig
                        }
                    }
                    None => Some(root2),
                },
                Ordering::Greater => match root2.right.clone() {
                    Some(mut right) => {
                        match key.cmp(&right.value) {
                            Ordering::Less => {
                                //zag zig
                                right.left = Self::splay(&right.left, key);
                                if let Some(_) = &right.left {
                                    right = Self::right_rotate(&mut right)
                                };
                            }
                            Ordering::Equal => (),
                            Ordering::Greater => {
                                //zag zag
                                right.right = Self::splay(&right.right, key);
                                root2 = Self::left_rotate(&mut root2);
                            }
                        };
                        match root2.right {
                            Some(_) => Some(Self::left_rotate(&mut root2)),
                            None => Some(root2), //zag
                        }
                    }
                    None => Some(root2),
                },
                Ordering::Equal => Some(root2),
            },
        }
    }
}
