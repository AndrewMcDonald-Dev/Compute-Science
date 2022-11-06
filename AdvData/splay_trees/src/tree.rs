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

    pub fn delete(root: &mut Option<Box<Node<i32>>>, key: i32) -> Option<Box<Node<i32>>> {
        match root {
            None => None,
            Some(root) => match key.cmp(&root.value) {
                Ordering::Less => {
                    root.left = Self::delete(&mut root.left, key);
                    Some(root.clone())
                }
                Ordering::Equal => {
                    if let None = root.right {
                        return root.left.clone();
                    }
                    if let None = root.left {
                        return root.right.clone();
                    }

                    match &root.right {
                        Some(right) => root.value = Self::min_value(&right),
                        None => panic!("HElp"),
                    }
                    root.right = Self::delete(&mut root.right, root.value);
                    Some(root.clone())
                }
                Ordering::Greater => {
                    root.right = Self::delete(&mut root.right, key);
                    Some(root.clone())
                }
            },
        }
    }

    pub fn min_value(root: &Box<Node<i32>>) -> i32 {
        let mut min_v = root.value;
        let mut root = root.clone();
        while let Some(left) = root.left {
            min_v = left.value;
            root = left;
        }
        min_v
    }

    pub fn splay_insert(mut root: Box<Node<i32>>, key: i32) -> Box<Node<i32>> {
        root = Self::insert(&mut Some(root), key).unwrap();
        root = Self::splay(&Some(root), key).unwrap();
        root
        // ? doesnt work dont know how to fix
        // match key.cmp(&root.value) {
        //     Ordering::Less => {
        //         let left = root.pop_left();
        //         let new = Node::new(key, left, None);
        //         let prev = mem::replace(&mut root, new);
        //         println!("{}", prev.value);
        //         root.right = Some(prev);
        //     }
        //     Ordering::Equal => (),
        //     Ordering::Greater => {
        //         let right = root.pop_right();
        //         let new = Node::new(key, None, right);
        //         let prev = mem::replace(&mut root, new);
        //         root.left = Some(prev);
        //     }
        // }
        // root
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
                        match key.cmp(&left.value) {
                            Ordering::Less => {
                                //zig zig
                                left.left = Self::splay(&left.left, key);
                                root2 = Self::right_rotate(&mut root2);
                            }
                            Ordering::Greater => {
                                // zig zag
                                left.right = Self::splay(&left.right, key);
                                if let Some(_) = &left.right {
                                    root2.left = Some(Self::left_rotate(&mut left));
                                };
                            }
                            Ordering::Equal => (),
                        };

                        match &root2.left {
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
                                    root2.right = Some(Self::right_rotate(&mut right));
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
