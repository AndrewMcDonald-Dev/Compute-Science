class Q8
{
	public static void main(String[] args)
	{
		int[] a={56,9,17,6,2,4,1,199,256,95};
		int[] b=new int[5];
		int[] c=new int[5];
		int count=0;
		int count2=0;
		for(int i=0;i<a.length;i++){
			if(a[i]%2==0){
				b[count]=a[i];
				count++;
			}else{
				c[count2]=a[i];
				count2++;
			}
		}
		for(int f=0;f<b.length;f++){
			System.out.print(b[f]+" ");
			System.out.print(c[f]+" ");
		}
	}
}