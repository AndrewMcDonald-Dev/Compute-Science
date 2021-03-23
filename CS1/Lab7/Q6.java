import java.util.Scanner;
class Q6
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("Enter String: ");
    String s=kb.nextLine();
    System.out.print(paliCheck(s));
  }
  public static String paliCheck(String s)
  {
    for(int i=0;i<s.length();i++){
      if(s.charAt(i)==s.charAt(s.length()-1-i)){
        return "This is a palindrone!";
      }else{
        if(s.charAt(i)!=s.charAt(s.length()-1-i)){
          return "This is not a palindrone!";
        }
      }
    }
    return "Failure";
  }
}