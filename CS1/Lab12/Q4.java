import java.util.*;
class Q4
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("Enter amount: ");
    int num=kb.nextInt();
    fibbo(num);
  }
  public static void fibbo(int num)
  {
    int prev=1;
    int next=1;
    for(int i=0;i<num;i++){
      System.out.print(prev+" ");
      int fib=prev+next;
      prev=next;
      next=fib;
    }
  }
}