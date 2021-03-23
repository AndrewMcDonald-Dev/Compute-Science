class Q1
{
  public static void main(String[] args)
  {
    String[] items={"car","coffee","book","apple","tshirt"};
    double[] prices={25000,3.15,15.99,1.25,25.67};
    double[] test={25000,3.15,15.99,1.25,25.67};
    double[] test2={25000,3.15,15.99,1.25,25.67};
    int[] indexs=new int[prices.length];
    indexs=selectionSort(prices);
    System.out.println("Selection Sort");
    for(int i=0;i<prices.length;i++){
      System.out.print(items[indexs[i]]+"  ");
      System.out.print(test[indexs[i]]+"  ");      
      System.out.println();
    }
    int[] helpMe=new int[prices.length];
    helpMe=insertionSort(test2);
    System.out.println();
    System.out.println("Insertion Sort");
    for(int i=0;i<prices.length;i++){
      System.out.print(items[helpMe[i]]+"  ");
      System.out.print(test[helpMe[i]]+"  ");      
      System.out.println();
    }
  }
  public static int[] selectionSort(double[] a)
  {
    int[] indexs=new int[a.length];
    for(int i=0;i<a.length-1;i++){
      int mI=findMin(a,i);
      indexs[i]=mI;
      double temp=a[i];
      a[i]=a[mI];
      a[mI]=temp;
    }
    return indexs;
  }
  public static int findMin(double[] a,int k)
  {
    double min=a[k];
    int minI=k;
    for(int i=k+1;i<a.length;i++){
      if(a[i]<min){
        min=a[i];
        minI=i;
      }
    }
    return minI;
  }
  public static int[] insertionSort(double[] a)
  {
    int[] indexs=new int[a.length];
    for(int i=1;i<a.length;i++){
      double x=a[i];
      int j=i-1;
      while(j>=0&&a[j]>x){
        a[j+1]=a[j];
        indexs[j]=j;
        j--;
      }
      a[j+1]=x;
      indexs[j+1]=i;
    }
    return indexs;
  }
}