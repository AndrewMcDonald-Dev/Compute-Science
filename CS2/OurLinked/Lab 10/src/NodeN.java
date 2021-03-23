public class NodeN<E> {
    private E data;
    private NodeN<E>[] links;

    public NodeN(){
        this(null, 1);
    }

    public NodeN(E e,int n){
        data = e;
        links=new NodeN[n];
    }

    public E getData() {
        return data;
    }

    public void setData(E e) {
        data = e;
    }

    public NodeN<E>[] getLinks() {
        return links;
    }

    public void setLinks(NodeN<E>[] links) {
        this.links = links;
    }
}
