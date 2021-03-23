import java.util.*;
class Q4
{
  public static void main(String[] args)
  {
    char[] a={'c','b','d','c'};
    System.out.print(arraySwitcher(a));
  }
  public static ArrayList<Character> arraySwitcher(char[] a)
  {
    ArrayList<Character> a2=new ArrayList<Character>();
    for(int i=0;i<a.length;i++){
      a2.add(a[i]);
    }
    return a2;
  }
}