class Q10
{
	public static void main(String[] args)
	{
		int prev=0;
		int next=1;
		for(int i=0;i<20;i++){
			System.out.print(prev+" ");
			int fib=prev+next;
			prev=next;
			next=fib;
		}
	}
}