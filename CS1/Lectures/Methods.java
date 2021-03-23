class Methods
{
	public static void main(String[] args)
	{
		f(0);
		System.out.println("done with method");
		//this is for storing a value from another function
		int s=sum(2,3);
		//this is for utilizing the store value from the function
		System.out.println("a + b = "+s);
		//this is for calling the function without storing the data and jsut printing it
		System.out.println("sum of 5 and 6 is "+sum(5,6));
	}
	public static void f(int x)
	{
		x=x*2+3;
		System.out.println("x = "+x);
	}
	public static int sum(int a,int b)
	{
		return a+b;
	}
}