class Q17
{
	public static void main(String[] args)
	{
		char[] alpha=new char[26];
		char let='A';
		for(int i=0;i<alpha.length;i++){
			alpha[i]=let;
			System.out.print(alpha[i]+" ");
			let++;
		}
	}
}