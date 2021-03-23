public class Node2<E> {
    private E data;
    private Node2<E> next, prev;

    public Node2(){
        this(null);
    }

    public Node2(E e){
        next = null;
        prev = null;
        data = e;
    }

    public Node2<E> getPrev() {
        return prev;
    }

    public void setPrev(Node2<E> prev) {
        this.prev = prev;
    }

    public Node2<E> getNext() {
        return next;
    }

    public void setNext(Node2<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E e) {
        data = e;
    }
}
