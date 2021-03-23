public class TestColor
{
  public static void main(String[] args)
  {
    Color c1=new Color(123,231,254);
    System.out.println(c1);
    System.out.println(c1.getColor());
  }  
}
class Color
{
  private int r;
  private int g;
  private int b;
  
  public Color(int a,int z,int c)
  {
    if(a<=255&&a>=0){
      r=a;
    }else{
      System.exit(1);
    }
    if(z<=255&&z>=0){
      g=z;
    }else{
      System.exit(1);
    }
    if(c<=255&&c>=0){
      b=c;
    }else{
      System.exit(1);
    }
  }
  public String toString(){
    return "Red: "+r+"  Green: "+g+"  Blue: "+b;
  }
  public String getColor(){
    return r+" "+g+" "+b;
  }
}