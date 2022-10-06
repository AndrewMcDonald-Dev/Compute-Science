public class Node<E> {
	public E data;
	public Node<E> left;
	public Node<E> right;

	public Node(E val, Node<E> left, Node<E> right) {
		this.data = val;
		this.left = left;
		this.right = right;
	}
}
