import java.util.Scanner;
class Q9
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    double total=0;
    int num=0;
    int count=0;
    while(num!=-1){
      System.out.print("Enter Integer: ");
      num=kb.nextInt();
      if(num!=-1){
        total=total+num;
        count++;
      }
    }
    System.out.println("Average: "+total/count);
  }
}