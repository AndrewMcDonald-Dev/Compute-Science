import java.util.*;
import java.io.*;
class Q6read
{
  public static void main(String[] args)throws IOException
  {
    Scanner in=new Scanner(new File("data.csv"));
    long[] rolled=new long[6];
    while(in.hasNextLine()){
      String line=in.nextLine();
      String[] nums=line.split(",");
      for(int i=0;i<nums.length;i++){
        int result=Integer.parseInt(nums[i]);
        for(int j=0;j<6;j++){
          if(result==j+1){
            rolled[j]++;
          }
        }
      }                                 
    }
    double count=rolled[0]+rolled[1]+rolled[2]+rolled[3]+rolled[4]+rolled[5];
    System.out.println("1= "+rolled[0]+" 2= "+rolled[1]+" 3= "+rolled[2]+" 4= "+rolled[3]+" 5= "+rolled[4]+" 6= "+rolled[5]);
    for(int i=0;i<rolled.length;i++){
      double percent=rolled[i]/count;
      System.out.print("%"+(i+1)+"= "+percent+"  ");
    }   
  }
}