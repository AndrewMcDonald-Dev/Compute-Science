class Q5
{
	public static void main(String[] args)
	{
		int[] a={1,2,3,5,8,13,21,34,55,89};
		int[] b=new int[a.length];
		for(int i=0;i<a.length;i++){
			b[i]=a[a.length-1-i];
			System.out.print(b[i]+" ");
		}
	}
}