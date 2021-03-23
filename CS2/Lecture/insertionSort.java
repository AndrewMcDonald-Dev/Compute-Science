class insertionSort
{
  public static void main(String[] args)
  {
    int[] test={-1,2,17,123,5,3,-999};
    int[] fin=sort(test);
    for(int i=0;i<test.length;i++){
      System.out.print(fin[i] +" ");
    }
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
}