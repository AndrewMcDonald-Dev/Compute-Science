use super::node::Node;
use std::cmp::Ordering;
use std::mem;

pub struct Tree {}

impl Tree {
    pub fn depth(root: &Box<Node<i32>>, key: &Box<Node<i32>>) -> isize {
        if Node::<i32>::same(root, key) {
            return 0;
        }

        let mut left = -1;
        let mut right = -1;

        if let Some(_) = root.left {
            left = Self::depth(root.left.as_ref().unwrap(), &key)
        }
        if let Some(_) = root.right {
            right = Self::depth(root.right.as_ref().unwrap(), &key)
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
        if let Some(_) = root.right {
            Self::print_tree(root.right.as_ref().unwrap(), first_root);
        }

        for _ in 0..(Self::depth(first_root, root) * 4) {
            print!(" ");
        }
        println!("{}", root.value);
        if let Some(_) = root.left {
            Self::print_tree(root.left.as_ref().unwrap(), first_root)
        }
    }

    pub fn insert(root: &mut Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
        let mut err = false;
        let temp = match root {
            Some(r) => {
                match key.cmp(&r.value) {
                    Ordering::Less => r.left = Self::insert(&mut r.left, key),
                    Ordering::Greater => r.right = Self::insert(&mut r.right, key),
                    Ordering::Equal => err = true,
                };
                r.clone()
            }
            None => Node::new(key, None, None),
        };

        if err {
            return None;
        }
        Some(temp)
    }

    pub fn delete(root: Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
        match root {
            None => None,
            Some(mut root) => {
                root = Self::splay(&Some(root), key).expect("No tree.");
                match key.cmp(&root.value) {
                    Ordering::Less => Some(root),
                    Ordering::Equal => match root.left {
                        Some(mut left) => {
                            left = Self::splay(&Some(left.clone()), Self::max_value(&left))
                                .expect("No tree.");
                            left.right = root.right;
                            Some(left)
                        }

                        None => root.right,
                    },
                    Ordering::Greater => Some(root),
                }
            }
        }
    }

    pub fn max_value(root: &Box<Node<i32>>) -> i32 {
        let mut max_v = root.value;
        let mut root = root.clone();
        while let Some(right) = root.right {
            max_v = right.value;
            root = right;
        }
        max_v
    }

    pub fn splay_insert(root: Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
        // root = Self::insert(&mut root, key);
        // root = Self::splay(&root, key);
        // root
        // ? doesnt work dont know how to fix
        match root {
            Some(mut root) => {
                root = Self::splay(&Some(root), key).expect("No tree");

                match key.cmp(&root.value) {
                    Ordering::Less => {
                        let left = root.pop_left();
                        let new = Node::new(key, left, None);
                        let prev = mem::replace(&mut root, new);
                        root.right = Some(prev);

                        Some(root)
                    }
                    Ordering::Equal => Some(root),
                    Ordering::Greater => {
                        let right = root.pop_right();
                        let new = Node::new(key, None, right);
                        let prev = mem::replace(&mut root, new);
                        root.left = Some(prev);
                        Some(root)
                    }
                }
            }
            None => Some(Node::new(key, None, None)),
        }
    }

    // pub fn find(root: &Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
    //     match root {
    //         Some(root) => match key.cmp(&root.value) {
    //             Ordering::Less => Self::find(&root.left, key),
    //             Ordering::Equal => Some(root.clone()),
    //             Ordering::Greater => Self::find(&root.right, key),
    //         },
    //         None => None,
    //     }
    // }

    pub fn right_rotate(root: &mut Box<Node<i32>>) -> Box<Node<i32>> {
        if let Some(_) = &root.left {
            let mut temp = root.left.as_ref().unwrap().clone();
            root.left = temp.right;
            temp.right = Some(root.clone());
            return temp;
        }
        root.clone()
    }

    pub fn left_rotate(root: &mut Box<Node<i32>>) -> Box<Node<i32>> {
        if let Some(_) = &root.right {
            let mut temp = root.right.as_ref().unwrap().clone();
            root.right = temp.left;
            temp.left = Some(root.clone());
            return temp;
        }
        root.clone()
    }

    pub fn splay(root: &Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
        match root.clone() {
            None => None,
            Some(mut root2) => match key.cmp(&root2.value) {
                Ordering::Less => match root2.left.clone() {
                    Some(mut left) => {
                        match key.cmp(&left.value) {
                            Ordering::Less => {
                                //zig zig
                                left.left = Self::splay(&left.left, key);
                                root2.left = Some(left);
                                root2 = Self::right_rotate(&mut root2);
                            }
                            Ordering::Greater => {
                                // zig zag
                                left.right = Self::splay(&left.right, key);
                                root2.left = Some(left);
                                match root2.left {
                                    Some(mut left) => {
                                        root2.left = Some(Self::left_rotate(&mut left))
                                    }
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
                                root2.right = Some(right);
                                match root2.right {
                                    Some(mut right) => {
                                        root2.right = Some(Self::right_rotate(&mut right))
                                    }
                                    None => todo!(),
                                }
                            }
                            Ordering::Greater => {
                                //zag zag
                                right.right = Self::splay(&right.right, key);
                                root2.right = Some(right);
                                root2 = Self::left_rotate(&mut root2);
                            }
                            Ordering::Equal => (),
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
