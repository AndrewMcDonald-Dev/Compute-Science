class Q1
{
  public static void main(String[] args)
  {
    String s="hello";
    System.out.print(remover(s,"el"));
  }
  public static String remover(String base, String removed)
  {
    if(base.contains(removed)){
      int index=base.indexOf(removed);
      String beg=base.substring(0,index);
      String end=base.substring(index+removed.length());
      return beg+end;
    }
    return "ahhh";
  }
}