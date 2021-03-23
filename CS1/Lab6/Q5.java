class Q5
{
	public static void main(String[] args)
	{
		int[] a={0,2,23,14,56,3};
		arrayBack(a);
	}
	public static void arrayBack(int[] a)
	{
		for(int i=0;i<a.length;i++){
			System.out.print(a[a.length-i-1]+" ");
		}
	}
}