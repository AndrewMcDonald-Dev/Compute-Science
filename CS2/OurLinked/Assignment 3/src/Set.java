public class Set<E> {
    private OurLinkedList<E> elements;

    public boolean isEmpty(){
        if(elements.isEmpty())
            return true;
        return false;
    }

    public void add(E x){
        if(!elements.contains(x))
            elements.add(x);
    }

    public Set<E> union(Set<E> s2){
        for(int i=0;i<s2.getElements().size();i++){
            this.elements.add(s2.getElements().get(i).getData());
        }
        return this;
    }

    public OurLinkedList<E> getElements() {
        return elements;
    }

    public void setElements(OurLinkedList<E> elements) {
        this.elements = elements;
    }
}
