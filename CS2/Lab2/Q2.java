class Q2
{
  public static void main(String[] args)
  {
    String[] list={"Taylor","Chris","God","CoolBoy","AlsoGod"};
    insertionSort(list);
    for(int i=0;i<list.length;i++){
      System.out.print(list[i] + "  ");
    }
  }
  public static String[] insertionSort(String[] a)
  {
    for(int i=1;i<a.length;i++){
      String x=a[i];
      int j=i-1;
      while(j>=0&&a[j].compareTo(x)>0){
        a[j+1]=a[j];
        j--;
      }
      a[j+1]=x;
    }
    return a;
  }
  public static  String[] selectionSort(String[] a)
  {
    for(int i=0;i<a.length-1;i++){
      int mI=findMin(a,i);
      String temp=a[i];
      a[i]=a[mI];
      a[mI]=temp;
    }
    return a;
  }
  public static int findMin(String[] a,int k)
  {
    String min=a[k];
    int minI=k;
    for(int i=k+1;i<a.length;i++){
      if(a[i].compareTo(min)>0){
        min=a[i];
        minI=i;
      }
    }
    return minI;
  }
}