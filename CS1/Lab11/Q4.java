import java.util.*;
import java.io.*;
class Q4
{
  public static void main(String[] args)throws IOException
  {
    Scanner in=new Scanner(new File("in.csv"));
    ArrayList<String> id=new ArrayList<String>();
    ArrayList<String> name=new ArrayList<String>();
    ArrayList<Double> grade=new ArrayList<Double>();
    while(in.hasNextLine()){
      String line=in.nextLine();
      String[] items=line.split(",");
      id.add(items[0]);
      name.add(items[2]+" "+items[1]);
      grade.add(Double.parseDouble(items[3]));
    }
    System.out.println(id);
    System.out.println(name);
    System.out.println(grade);
    int high=highestAc(grade);
    System.out.println(id.get(high) + " " + name.get(high) + " " + grade.get(high));
    in.close();
  }
  public static int highestAc(ArrayList<Double> r)
  {
    double highest=r.get(0);
    int highestIndex=0;
    for(int i=1;i<r.size();i++){
      double curValue=r.get(i);
      if(curValue>highest){
        highest=curValue;
        highestIndex=i;
      }
    }
    return highestIndex;
  }
}