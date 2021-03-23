import java.util.*;
class Q2
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    String ah="Zebra";
    String ahhhh="Octo";
    System.out.println(merger(ah,ahhhh));
    System.out.print("Enter String: ");
    String oh=kb.next();
    System.out.print("Enter character: ");
    char ohhh=kb.next().charAt(0);
    System.out.print("Enter number: ");
    int ohhhhh=kb.nextInt();
    repeater(oh,ohhh,ohhhhh);
  }
  public static String merger(String s,String s1)
  {
    String s2="";
    for(int i=1;i<s.length();i+=2){
      s2=s2+s.substring(i,i+1);
    }
    String s3="";
    for(int i=1;i<s1.length();i+=2){
      s3=s3+s1.substring(i,i+1);
    }
    return s2.toUpperCase()+s3.toUpperCase();
  }
  public static void repeater(String s,char c,int num)
  {
    String s1="";
    int i=0;
    while(i<s.length()){
      if(s.charAt(i)==c){
        s1=s1+s.substring(0,s.indexOf(c)+1);
        s=s.substring(s.indexOf(c)+1,s.length());
        i=0;
        for(int j=1;j<num;j++){
          s1=s1+c;
        }
      }else{
        i++;
      }
    }
    System.out.println(s1+s);
  }
}
