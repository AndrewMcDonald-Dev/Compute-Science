#[derive(Clone)]
pub struct Node<V> {
    pub value: V,
    pub left: Option<Box<Node<V>>>,
    pub right: Option<Box<Node<V>>>,
}

impl<V> Node<V> {
    pub fn new(v: V, left: Option<Box<Node<V>>>, right: Option<Box<Node<V>>>) -> Box<Node<V>> {
        Box::new(Node {
            value: v,
            left,
            right,
        })
    }
    pub fn same<T>(a: *const T, b: *const T) -> bool {
        a == b
    }
}
