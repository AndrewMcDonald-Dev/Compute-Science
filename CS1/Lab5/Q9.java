class Q9
{
	public static void main(String args[])
	{
		int[] a={12,3,-8,100,-2};
		int[] b={4,-9,30,2};
		int[] c=new int[a.length+b.length];
		int count=0,num=0;
		if(a.length>b.length){
			num=a.length;
		}else{
			num=b.length;
		}
		for(int i=0;i<num;i++){
			c[count]=a[i];
			count++;
			if(i<b.length){
				c[count]=b[i];
				count ++;
			}
		}
		System.out.print("Array a:  ");
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}		
		System.out.print("\nArray b:  ");
		for(int i=0;i<b.length;i++){
			System.out.print(b[i]+" ");
		}
		System.out.print("\nArray c:  ");
		for(int i=0;i<c.length;i++){
			System.out.print(c[i]+" ");
		}
	}
}