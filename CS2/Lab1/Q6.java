class Q6
{
  public static void main(String[] args)
  {
    String s= "1Aweso1e sauce1";
    int count=0;
    for(int i=0;i<s.length();i++){
      if(Character.isDigit(s.charAt(i))){
        count++;
      }
    }
    System.out.println("The amount of characters in "+s+" is "+count);
  }
}