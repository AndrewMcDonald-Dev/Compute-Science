import java.util.*;
class Q2
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.println("How many multiples of ten do you want to test?");
    int num=kb.nextInt();
    num=(int)(Math.pow(10,num));
    System.out.println("#darts   #hits   #miss    #hits\\#darts  Math.PI");
    System.out.println("-----------------------------------------");
    for(int i=10;i<=num;i*=10){
      int h=getHits(i);
      double ratio=(double)h/i;
      System.out.printf("%-9d%-9d%-9d%f%-5s%f\n",i,h,i-h,4*ratio," ",Math.PI);
    }
  }
  public static double[] getPoint()
  {
    double[] point=new double[2];
    point[0]=Math.random();
    point[1]=Math.random();
    return point;
  }
  public static double originDistance(double x,double y)
  {
    return Math.sqrt(Math.pow(x-0,2)+Math.pow(y-0,2));
  }
  public static int getHits(int n)
  {
    int hits=0;
    for(int i=0;i<n;i++)
    {
      double[] p=getPoint();
      double distance=originDistance(p[0],p[1]);
      if(distance<1)
      {
        hits++;
      }
    }
    return hits;
  }
}