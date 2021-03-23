import java.util.*;
class Q1
{
  public static void main(String[] args)
  {
    ArrayList<Double> al=new ArrayList<Double>();
    for(int i=0;i<7;i++){
      al.add(Math.random()*10);
    }
    System.out.println(al);
    System.out.println(aLSum(al));
  }
  public static double aLSum(ArrayList<Double> a)
  {
    double sum=0.0;
    for(int i=0;i<a.size();i++){
      sum=a.get(i)+sum;
    }
    return sum;
  }
}