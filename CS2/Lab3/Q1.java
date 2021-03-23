import java.util.*;
class Q1
{
  public static void main(String[] args)throws MyExceptions{
    Scanner kb=new Scanner(System.in);

    System.out.println("Enter Number of Sides: ");
    int in4=kb.nextInt();
    
    //System.out.println("Enter color: ");
    RGB s=new RGB();
    
    System.out.println("Enter striped: ");
    boolean b=kb.nextBoolean();
    
    boolean triangleCheck=false;
    int attemptCount=0;
    while(!triangleCheck){
          
      System.out.println("Enter side1 length: ");
      int in1=kb.nextInt();
      System.out.println("Enter side2 length: ");
      int in2=kb.nextInt();
      System.out.println("Enter side3 length: ");
      int in3=kb.nextInt();
      if(in1+in2<in3||in2+in3<in1||in3+in1<in2){
        attemptCount++;
        if(attemptCount>=3){
          throw new MyExceptions("You have tried too many attempts.");
        }
        System.out.println("These values can not make a triangle try again");
      }else{
        triangleCheck=true;
        Triangle ah=new Triangle(in1,in2,in3,in4,s,b);
        System.out.println(ah);
      }
    
    }
  }
}

class MyExceptions extends Exception
{
  public MyExceptions(){
    super("Something went wrong.");
  }
  public MyExceptions(String s) { 
    super(s); 
  }
}
class RGB
{
  private int R,G,B;
 
  public RGB()
  {
    this(0,0,0);
  }
  public RGB(int R,int G,int B)
  {
    this.R=R;
    this.G=G;
    this.B=B;
  }
 
  public int[] getColor()
  {
    return new int[]{R,G,B};
  }
  public void setColor(int[] c)
  {
    R=c[0];
    G=c[1];
    B=c[2];
  }
 
  public String toString()
  {
    return "("+R+","+G+","+B+")";
  }
 
  public boolean equals(RGB r)
  {
    int[] c=r.getColor();
    return (this.R==c[0]) && (this.G==c[1]) && (this.B==c[2]);
  }
 
  public RGB invert()
  {
    return new RGB(255-R,255-G,255-B);
  }
}

class Shape
{
  //Attributes
  private int numOfSides;
  private RGB color;
  private boolean striped;
 
  //Constructors
  public Shape(int s,RGB c,boolean str)
  {
    numOfSides=s;
    color=c;
    striped=str;
  }
  public Shape()
  {
    numOfSides=4;
    color= new RGB();
    striped=false;
  }
 
  //get methods
  public int getNumOfSides()
  {
    return numOfSides;
  }
  public RGB getColor()
  {
    return color;
  }
  public boolean getStriped()
  {
    return striped;
  }
  
  //set methods
  public void setNumOfSides(int n)
  {
    numOfSides=n;
  }
  public void setColor(RGB c)
  {
    color=c;
  }
  public void setStriped(boolean b)
  {
    striped=b;
  }
  
  //Methods
 
  public String toString()
  {
    return "numOfSides: "+numOfSides+"\ncolor: "+color+"\nstriped: "+striped;
  }
  public boolean equals(Shape s)
  {
    if(this.numOfSides==s.getNumOfSides() && this.color.equals(s.getColor()) && this.striped==s.getStriped())
      return true;
    return false;
  }
}
class Triangle extends Shape
{
  //Attributes
  private double side1;
  private double side2;
  private double side3;
  
  //Constructors
  public Triangle(){
    side1=1.0;
    side2=1.0;
    side3=1.0;
  }
  public Triangle(double s1,double s2,double s3){
    side1=s1;
    side2=s2;
    side3=s3;
  }
  public Triangle(double s1,double s2,double s3,int n,RGB c,boolean s){
    super(n,c,s);
    side1=s1;
    side2=s2;
    side3=s3;
  }
  
  //Methods
  public double perimeter(){
    int sum=0;
    sum+=side1;
    sum+=side2;
    sum+=side3;
    return sum;
  }
  public double area(){
    double s=perimeter()/2;
    double area= (Math.sqrt(s*(s-side1)*(s-side2)*(s-side3)));
    return area;
  }
  public boolean equals(Triangle t){
    if(super.equals(t)){
      if(this.side1==t.getSide1() && this.side2==t.getSide2() && this.side3==t.getSide3()){
        return true;
      }
    }
    return false;
  }
  public String toString(){
    return "numOfSides: "+super.getNumOfSides()+"\ncolor: "+getColor()+"\nStriped: "+super.getStriped()+
      "\nTriangle \n           side1: "+side1+"\n           side2: "+side2+"\n           side3: "+side3
    +"\n           area: "+area()+"\n           perimeter: "+perimeter();
  }
  
  //get methods
  public double getSide1(){
    return side1;
  }
  public double getSide2(){
    return side2;
  }
  public double getSide3(){
    return side3;
  }
  
  //set methods
  public void setSide1(double n){
    side1=n;
  }
  public void setSide2(double n){
    side2=n;
  }
  public void setSide3(double n){
    side3=n;
  }
}

