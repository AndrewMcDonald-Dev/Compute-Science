class Q9
{
	public static void main(String[] args)
	{
		boolean p = true;
		boolean q = true;
		System.out.println("p     q     p&&q");
		System.out.println("_____________________");
		System.out.println(p + "  " + q + "  " + (p&&q));
		q = false;
		System.out.println(p + "  " + q + " " + (p&&q));
		p = false;
		q = true;
		System.out.println(p + " " + q + "  " + (p&&q));
		q = false;
		System.out.println(p + " " + q + " " + (p&&q));
	}
}