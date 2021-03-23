import java.util.*;
import java.io.*;
class Q7
{
  public static void main(String[] args)throws IOException
  {
    System.out.println("First        Last           M  Email               Age");
    Scanner data=new Scanner(new File("data.csv"));
    PrintWriter pw=new PrintWriter(new File("out.csv"));
    while(data.hasNextLine()){
      String line=data.nextLine();
      String[] att=line.split(",");
      System.out.printf("%-13s%-15s%-3s%-20s%s%n", fixS(att[0]), fixS(att[1]), att[2].toUpperCase(), genEmail(att), att[3]);
      pw.println(fixS(att[0])+","+fixS(att[1])+","+att[2].toUpperCase()+","+genEmail(att)+","+att[3]);
    }
    pw.close();
    data.close();
  }
  public static String fixS(String s){
    String s1=s.substring(0,1).toUpperCase()+s.substring(1).toLowerCase();
    return s1;
  }
  public static String genEmail(String[] att){
    String email="";
    if(att[1].length()<=5){
      email+=att[1].toLowerCase();
    }else{
      email+=att[1].substring(0,6).toLowerCase();
    }
    email+=att[0].substring(0,1).toLowerCase();
    email+="@gmail.com";
    return email;
  }
}