import java.util.*;
class Q3
{
  public static void main(String[] args)
  {
    ArrayList<String> a=new ArrayList<String>();
    a.add("aaa");
    a.add("bbb");
    a.add("ccc");
    aLSPrinter(a);
  }
  public static void aLSPrinter(ArrayList<String> a)
  {
    ArrayList<String> a2=new ArrayList<String>();
    for(int i=0;i<a.size();i++){
      a2.add(a.get(a.size()-i-1));
    }
    System.out.println(a2);
  }
}