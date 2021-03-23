class Q13{
	public static void main(String[] args)
	{
		int num = 123;
		int ones = num%10;
		num = num/10;
		num = num/10;
		int hundreds = num%10;
		if(ones==hundreds){
			System.out.println("This is a palindrone.");
		}else{
			System.out.println("This is not a Palindrone.");
		}
	}
}