import java.util.ArrayList;
class arrayList
{
  public static void main(String[] args)
  {
    ArrayList<Integer> al=new ArrayList<Integer>();//can only use objects and is not a fixed length
    //indexes shift when adding something
    System.out.println(al);
    System.out.println(al.isEmpty());
    al.add(10);
    System.out.println(al);
    al.add(-2);
    System.out.println(al);
    al.add(2,8);//adds 8 to index 2 which stil works
    System.out.println(al);
    int x=al.remove(0);//removes and stores the item at index 0
    System.out.println(al);
    System.out.println("x = "+x);
    al.clear();
    System.out.println(al);
  }
}