class Q11
{
	public static void main(String[] args)
	{
		String s='googoo';
		int length=s.length();
		char first=s.charAt(0);
		char last=s.charAt(s.length()-1);
		for(int i=0;i<s.legnth();i++){
			System.out.println(s.charAt(i));
		}
		for(int i=0;i<s.legnth();i++){
			System.out.println(s.charAt(s.length()-i-1));
		}
		
	}
	
}