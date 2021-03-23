import java.util.*;
import java.io.*;
class Q2
{
  public static void main(String[] args)throws IOException
  {
    Scanner in=new Scanner(new File("in.txt"));
    PrintWriter pw=new PrintWriter("output.csv");
    while(in.hasNextLine()){
      String line=in.nextLine();
      String[] words=line.split("#");
      for(int i=0;i<words.length;i++){
        pw.print(words[i]+",");
      }
      pw.println(words[words.length-1]);
    }
    pw.close();
    in.close();
  }
}