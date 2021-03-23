import java.util.Scanner;
class Q8
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("Enter Amount: ");
    int s=kb.nextInt();
    System.out.print("Enter Lower Bound: ");
    int s1=kb.nextInt();
    System.out.print("Enter Upper Bound: ");
    int s2=kb.nextInt();
    randomGenerator(s,s1,s2);
  }
  public static void randomGenerator(int amount,int lowerBound,int upperBound)
  {
    for(int i=0;i<amount;i++){
      System.out.print(((Math.random()*(upperBound-lowerBound))+lowerBound)+" ");
    }
  }
}