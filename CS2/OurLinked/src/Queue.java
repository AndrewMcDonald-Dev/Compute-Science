public class Queue<E> {
    private OurLinkedList<E> list;

    public Queue(){
        list = new OurLinkedList<>();
    }

    public void add(E e){
        list.add(e);
    }

    public E remove(){
        return list.remove();
    }

    public E peek(){
        return list.get(0).getData();
    }

    public boolean isEmpty(){
        return list.get(0) == null;
    }
}
