import java.util.*;
import java.io.*;
class Q6
{
  public static void main(String[] args)throws IOException
  {
    PrintWriter pw=new PrintWriter("data.csv");
    Scanner kb=new Scanner(System.in);
    System.out.println("How many times do you want to roll the dice? ");
    long n=kb.nextLong();
    for(int j=0;j<n/50;j++){
      for(int i=0;i<50;i++){
        int num=(int)(Math.random()*6+1);
        pw.print(num);
        if(i<49){
          pw.print(",");
        }
      }
      pw.println();
    }
    pw.close();
    kb.close();
  }
}