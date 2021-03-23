class Q5
{
  public static void main(String[] args)
  {
    String s="asd22222$2as4";
    digitCounter(s);
  }
  public static void digitCounter(String s)
  {
    char l;
    int count=0;
    int count2=0;
    for(int i=0;i<s.length();i++){
      l=s.charAt(i);
      if(Character.isLetterOrDigit(l)){
        if(Character.isDigit(l)){
          count++;
        }
      }else{
        count2++;
      }
    }
    System.out.println("The amount of digits is "+count);
    System.out.println("The amount of symbols is "+count2);
  }
}