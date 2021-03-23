import java.util.Scanner;
class Q10
{
  public static void main(String[] args)
  {
    Scanner kb=new Scanner(System.in);
    System.out.print("How many tosses? ");
    int flips=kb.nextInt();
    int[] flipped=new int[2];
    for(int i=0;i<flips;i++){
      int s=(int)(Math.random()*2);
      if(s==0){
        flipped[0]++;
      }else{
        flipped[1]++;
      }
    }
    System.out.print("Tosses:"+flips+"   Heads:"+flipped[0]+"   Tails:"+flipped[1]);
  }
}