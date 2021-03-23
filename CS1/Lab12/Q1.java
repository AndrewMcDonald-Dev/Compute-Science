import java.util.*;
class Q1
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("How many rows: ");
    int r=kb.nextInt();
    System.out.print("How many in the 1st row: ");
    int n=kb.nextInt();
    System.out.print("What character: ");
    char c=kb.next().charAt(0);
    charPrinter(r,n,c);
    
  }
  public static void charPrinter(int row, int num, char c)
  {
    for(int i=0;i<row;i++){
      for(int j=num;j>0;j--){
        System.out.print(c);
      }
      num--;
      System.out.println();
    }
  }
}