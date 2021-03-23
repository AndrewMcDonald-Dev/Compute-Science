class Q18
{
	public static void main(String[] args)
	{
		double[] a=new double[5];
		a[0]=58.92;
		a[1]=89.9;
		a[2]=90.6;
		a[3]=100;
		a[4]=79.89;
		double sum=0;
		for(int i=0;i<5;i++){
			sum=a[i]+sum;
		}
		sum=sum/a.length;
		System.out.print(sum);
	}
}