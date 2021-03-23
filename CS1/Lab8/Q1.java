import java.util.*;
import java.io.*;
class Q1
{
  public static void main(String[] args)throws IOException
  {
    Scanner in=new Scanner(new File("input.txt"));
    int num=in.nextInt();
    String[] names=new String[num];
    int[] wages=new int[num];
    int count=0;
    /*while(in.hasNextLine()){
      names[count]=in.next();
      wages[count]=in.nextInt();
      count++;
      in.nextLine();
    }*/
    for(int i=0;i<wages.length;i++) //read in the data
    {
      names[i]=in.next();
      wages[i]=in.nextInt();
    }
    int index=0;
    for(int i=1;i<wages.length;i++){
      if(wages[i]<wages[index]){
        index=i;
      }
    }
    System.out.print("The minimum salary is "+wages[index]+" and the person is "+names[index]);
    in.close();
  }
}