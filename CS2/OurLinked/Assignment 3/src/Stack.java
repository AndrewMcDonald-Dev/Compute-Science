public class Stack<E> {
    private OurLinkedList<E> stack;

    public Stack() {
        stack=new OurLinkedList<>();
    }

    public void push(E e) {
        stack.addFront(e);
    }

    public E pop() {
        return stack.remove();
    }

    public boolean isEmpty() {
        return stack.get(0) == null;
    }

    public E peek() {
        return stack.get(0).getData();
    }
}
