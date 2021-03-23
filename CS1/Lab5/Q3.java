class Q3
{
	public static void main(String[] args)
	{
		int[] a={1,2,3,18,9,5,8,13,21,34,55,89};
		int count=0;
		for(int i=0;i<a.length;i++){
			if(a[i]%3==0){
				count++;
			}
		}
		System.out.print(count);
	}
}