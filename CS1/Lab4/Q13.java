class Q13
{
	public static void main(String[] args)
	{
		int num=5497116;
		int x=0;
		int sum=0;
		while(num>0){
			x=num%10;
			sum=x+sum;
			num=num/10;
		}
		System.out.print(sum);
	}
}