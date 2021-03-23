import java.util.*;
class Q8
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.println("Enter Name: ");
    String s=kb.nextLine();
    digitCounter(s);
  }
  public static void digitCounter(String s)
  {
    char l;
    for(int i=0;i<s.length();i++){
      l=s.charAt(i);
      if(Character.isLetter(l)){
      }else{
        System.out.println("Invalid Name!");
        break;
      }
    }
    System.out.println("Good name!");
  }
}