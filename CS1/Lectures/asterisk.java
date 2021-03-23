import java.util.*;
class asterisk
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("Enter how many rows you want: ");
    int rows=kb.nextInt();
    for(int i=0;i<rows;i++){
      for(int a=0;a<i;a++){
        System.out.print(" ");
      }
      for(int j=rows;j>i;j--){
        System.out.print("* ");
      }
      System.out.println();
    }
  }
}