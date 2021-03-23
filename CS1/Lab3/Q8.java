class Q8
{
	public static void main(String[] args)
	{
		int x = 313297;
		int sec = x%60;
		x = x/60;
		int min = x%60;
		x = x/60;
		int hour = x%24;
		x = x/24;
		int day = x;
		System.out.println(day + ":" + hour + ":" + min + ":" + sec);
	}
}