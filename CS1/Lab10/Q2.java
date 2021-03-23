class Q2
{
  public static void main(String[] args)
  {
    String s="happyy";
    String s1="goddam";
    System.out.print(addS(s,s1));
  }
  public static String addS(String s,String s1)
  {
    String s2="";
    for(int i=0;i<s.length();i++){
      if(i%2==0){
        s2=s2+s.substring(i,i+1);
      }else{
        s2=s2+s1.substring(i,i+1);
      }
    }
    return s2;
  }
}