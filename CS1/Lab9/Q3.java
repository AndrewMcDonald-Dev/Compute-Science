import java.util.*;
class Q3
{
 public static void main(String[] args)
 {
  Scanner kb = new Scanner(System.in);
  ArrayList<Integer> a= new ArrayList<Integer>();
  int y=0;
  int count=0;
  while(y!=-1){
    System.out.print("Enter int: ");
    y=kb.nextInt();
    if(y!=-1){
      a.add(y);
      count++;
    }else{
      System.out.print("Array: ");
    }
  }
  System.out.println(a);
  maxFinder(a);
  kb.close();
 }
 public static void maxFinder(ArrayList<Integer> a){
   Integer max=a.get(0);
   for(int i=1;i<a.size();i++){
     if(a.get(i)>max){
       max=a.get(i);
     }
   }
   System.out.println("The maximum number you entered is "+max);
 }
}