class Q4
{
	public static void main(String[] args)
	{
		oddSum(3,15);
	}
	public static void oddSum(int Start,int End)
	{
		int sum=0;
		for(int i=Start;i<=End;i++){
			if(i%2!=0){
				sum=sum+i;
			}
		}
		System.out.print(sum);
	}
}