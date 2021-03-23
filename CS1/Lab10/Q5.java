import java.util.*;
class Q5
{
  public static void main(String[] args)
  {
    sum();
  }
  public static void sum()
  {
    Scanner kb=new Scanner(System.in);
    ArrayList<Integer> inputs=new ArrayList<Integer>();
    int sum=0;
    int num=0;
    while(sum<300){
      System.out.println("Enter Int: ");
      num=kb.nextInt();
      sum=sum+num;
      inputs.add(num);
    }
    System.out.println(inputs);
  }
}