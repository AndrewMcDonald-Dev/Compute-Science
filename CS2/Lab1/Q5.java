class Q5
{
  public static void main(String[] args)
  {
    int[] yay=numGen(3);
    for(int i=0;i<yay.length;i++){
      System.out.println(yay[i]);
    }
  }
  public static int[] numGen(int n)
  {
    int[] ranNum=new int[200];
    for(int i=0;i<ranNum.length;i++){
      ranNum[i]=(int)((Math.random()*(2*n))+n);
    }
    return ranNum;
  }
}