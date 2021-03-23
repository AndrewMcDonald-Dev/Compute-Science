//this is the answer to Q2, Q3, and Q4.
import java.util.*;
class Q2
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("Enter number: ");
    long n=kb.nextLong();
    System.out.println(n+" has "+amount(n)+" digits.");
    System.out.println("sum = "+sum(n));
  }
  public static long amount(long n)
  {
    int count=0; 
    while(n>0){
      n=n/10;
      count++;
    }
    return count;
  }
  public static long sum(long n)
  {
    int sum=0;
    int digit=0;
    while(n>0){
      sum+=n%10;
      n/=10;
    }
    return sum;
  }
}
