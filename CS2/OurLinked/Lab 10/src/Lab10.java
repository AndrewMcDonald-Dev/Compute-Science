public class Lab10 {
    public static void main(String[] args) {
        OurLinkedList<Double> ll=new OurLinkedList<>();
        OurLinkedList<Double> ll2=new OurLinkedList<>();
        ll.add(2.0);
        ll.add(2.0);
        ll.add(2.0);
        ll.add(2.0);
        ll.add(2.0);
        ll2.add(3.0);
        ll2.add(3.0);
        ll2.add(3.0);
        ll2.add(3.0);
        ll2.add(3.0);
        ll.merge(ll2);
        System.out.println(ll);
        System.out.println(ll2);
    }
}
