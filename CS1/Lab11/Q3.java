import java.util.*;
class Q3
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.println("Enter the amount of rows: ");
    int rows=kb.nextInt();
    System.out.println("Enter the amount of asterisks: ");
    int num=kb.nextInt();
    for(int i=0;i<rows;i++){
      System.out.print("#");
      for(int j=0;j<num;j++){
        System.out.print("*");
      }
      System.out.print("#");
      System.out.println();
    }
  }
}