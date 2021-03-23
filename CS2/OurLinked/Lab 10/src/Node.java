public class Node<E> {
    private E data;
    private Node<E> link;

    public Node() {
        this(null);
    }

    public Node(E e) {
        link = null;
        data = e;
    }

    public void setLink(Node<E> next) {
        this.link = next;
    }

    public Node<E> getLink() {
        return link;
    }

    public void setData(E e) {
        data = e;
    }

    public E getData() {
        return data;
    }
}
