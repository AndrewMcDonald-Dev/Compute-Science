class Q2
{
	public static void main(String[] args)
	{
		int[] a={1,2,3,5,8,13,21,34,55,89};
		int sum=0;
		int avg=1;
		for(int i=0;i<a.length;i++){
			sum=sum+a[i];
		}
		avg=sum/a.length;
		System.out.print(sum);
		System.out.print(avg);
	}
}