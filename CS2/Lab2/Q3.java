class Q3
{
  public static void main(String[] args)
  {
    int[] ya=new int[20];
    fill(ya);
    sort(ya);
    for(int i=0;i<ya.length;i++){
      System.out.print(ya[i]+" ");
    }
    System.out.println(check(ya, 8));
  }
  public static int[] sort(int[] a)
  {
    for(int i=1;i<a.length;i++){
      int x=a[i];
      int j=i-1;
      while(j>=0&&a[j]>x){
        a[j+1]=a[j];
        j--;
      }
      a[j+1]=x;
    }
    return a;
  }
  public static int[] fill(int[] a)
  {
    for(int i=0;i<a.length;i++){
      a[i]=(int)(Math.random()*(500));
    }
    return a;
  }
  public static boolean check(int[] a, int k)
  {
    for(int i=0;i<a.length;i++){
      if(k==a[i]){
        return true;
      }
    }
    return false;
  }
}