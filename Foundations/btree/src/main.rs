#[derive(Debug, Clone)]
struct Node {
    idx: usize,
    keys: Vec<i32>,
    parent: Option<usize>,
    children: Vec<usize>,
    degree: usize,
}

impl Node {
    fn new(
        idx: usize,
        keys: Vec<i32>,
        children: Vec<usize>,
        parent: Option<usize>,
        degree: usize,
    ) -> Self {
        Node {
            idx,
            keys,
            children,
            parent,
            degree,
        }
    }
    fn is_full(&self) -> bool {
        self.keys.len() == self.degree
    }
    fn get_key(&self, index: usize) -> &i32 {
        &self.keys[index]
    }
}

//Memory Arena
#[derive(Clone)]
struct BTree {
    nodes: Vec<Node>,
    root: usize,
}

impl BTree {
    fn add(
        &mut self,
        keys: Vec<i32>,
        children: Vec<usize>,
        parent: Option<usize>,
        degree: usize,
    ) -> usize {
        let idx = self.nodes.len();
        self.nodes
            .push(Node::new(idx, keys, children, parent, degree));
        idx
    }

    fn get(&self, index: usize) -> &Node {
        &self.nodes[index]
    }

    fn get_mut(&mut self, index: usize) -> &mut Node {
        &mut self.nodes[index]
    }

    fn insert(&mut self, value: i32) {
        let root_idx = self.root;

        // Find where to insert value
        if self.nodes.len() == 0 {
            self.add(vec![value], vec![], None, 5);
        } else {
            let root = self.get(root_idx);
            if root.is_full() {
                let new_root_idx = self.add(vec![], vec![], None, root.degree);
                {
                    let new_root = self.get_mut(new_root_idx);
                    new_root.children.push(root_idx);
                }
                self.split_child(0, root_idx, new_root_idx);

                let mut i = 0;
                if self.get(new_root_idx).keys[0] < value {
                    i += 1;
                }
                self.insert_non_full(value, self.get(new_root_idx).children[i]);

                self.root = new_root_idx;
            } else {
                self.insert_non_full(value, root_idx);
            }
        }
    }
    fn insert_non_full(&mut self, value: i32, idx: usize) {
        let cur_node = self.get(idx);

        // Initialize index as index of rightmost element
        let mut i = cur_node.keys.len() - 1;

        // If this is a leaf node
        if cur_node.children.len() == 0 {
            let cur_node = self.get_mut(idx);

            // The following loop does two things
            // a) Finds the location of new key to be inserted
            // b) Moves all greater keys to one place ahead
            while cur_node.keys[i] > value {
                cur_node.keys[i + 1] = cur_node.keys[i];
                i -= 1;
            }

            // Insert the new key at found location
            cur_node.keys[i + 1] = value;
        } else
        // If this node is not leaf
        {
            // Find the child which is going to have the new key
            while cur_node.keys[i] > value {
                i -= 1;
            }

            // See if the found child is full
            if self.get(cur_node.children[i + 1]).keys.len() == cur_node.degree {
                // If the child is full, then split it
                self.split_child(i + 1, cur_node.children[i + 1], idx);
                let cur_node = self.get(idx);
                // After split, the middle key of C[i] goes up and
                // C[i] is splitted into two.  See which of the two
                // is going to have the new key
                if cur_node.keys[i + 1] < value {
                    i += 1;
                }
            }
            let cur_node = self.get(idx);
            self.insert_non_full(value, cur_node.children[i + 1]);
        }
    }
    fn split_child(&mut self, idx: usize, child_idx: usize, parent_idx: usize) {
        // Create a new node which is going to store (t-1) keys
        // of y
        let y = self.add(
            vec![],
            vec![],
            Some(child_idx),
            self.nodes[child_idx].degree,
        );
        let y = self.get_mut(y);

        let t = y.degree / 2 + 1;
        // Copy the last (t-1) keys of x to y
        for j in 0..t - 1 {
            y.keys[j] = y.keys[j + t];
        }

        //Copy the
    }
}

fn main() {}
