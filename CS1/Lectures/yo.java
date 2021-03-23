class yo
{
	public static void main(String[] args)
	{
		System.out.print(power(2,4));
		String s="hello";
		char c=s.charAt(0);
		char b=s.charAt(s.length()-1);
	}
	public static int power(int num,int power)
	{
		int product=num;
		for(int i=0;i<power-1;i++){
			product=product*num;
		}
		return product;
	}
}