public class Node2Data<E> {
    private E data, data2;
    private Node2Data<E> link;

    public Node2Data(){
        this(null,null);
    }

    public Node2Data(E e) {
        this(e, null);
    }

    public Node2Data(E e,E e2){
        link = null;
        data = e;
        data2 = e2;
    }

    public void setLink(Node2Data<E> link) {
        this.link = link;
    }

    public void setData2(E e) {
        data2 = e;
    }

    public void setData(E e) {
        data = e;
    }

    public E getData() {
        return data;
    }

    public Node2Data<E> getLink() {
        return link;
    }

    public E getData2() {
        return data2;
    }
}
