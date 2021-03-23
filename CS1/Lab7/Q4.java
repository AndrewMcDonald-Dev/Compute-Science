import java.util.Scanner;
class Q4
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("Enter String: ");
    String s=kb.nextLine();
    System.out.print("Enter Index: ");
    int s1=kb.nextInt();
    System.out.println(printer(s,s1));
  }
  public static String printer(String s,int index)
  {
    if(index>=0&&index<s.length()){
      return s.substring(index,s.length());
    }else{
      return "Index out of bounds!";
    }
  }
}