import java.util.*;
class Q1
{
  public static void main(String[] args)
  {
    System.out.println("Think of a number from 1-100");
    System.out.println("Enter y if I guess your number, enter l and h for lower and higher.");  
    guessing(1,100);
  }
  public static void guessing(int lower,int upper)
  {
    Scanner kb=new Scanner(System.in);
    int guess=0;
    int count=0;
    String s="";
    while(true){
      guess=(upper+lower)/2;
      System.out.println("Is your number "+guess);
      count++;
      s=kb.next();
      if(s.charAt(0)=='y'){
        System.out.println("Your number is "+guess);
        System.out.println("It took me "+count+" guesses");
        break;
      }else if(s.charAt(0)=='l'){
        upper=guess-1;
      }else{
        lower=guess+1;
      }
      if(lower==upper){
        System.out.println("Your number is "+lower);
        System.out.println("It took me "+count+" guesses");
        break;
      }  
    }
  }
}