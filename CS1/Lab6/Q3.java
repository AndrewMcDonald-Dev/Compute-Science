class Q3
{
	public static void main(String[] args)
	{
		System.out.println("The Letter of the Day is "+whatDay(3));
	}
	public static char whatDay(int day)
	{
		switch(day){
			case 1: return 'm';			
			case 2: return 't';
			case 3: return 'w';
			case 4: return 'r';
			case 5: return 'f';
			case 6: return 's';
			default: return 'u';
		}
	}
}