import java.util.Scanner;
class inputArrayPrinter
{
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		int[] a= new int[1];
		int y=0;
		int count=0;
		while(y!=-1){
			System.out.print("Enter int: ");
			y=kb.nextInt();
			if(y!=-1){
				if(count>(a.length-1)){
					a=increaseArray(a);
				}
				a[count]=y;
				count++;
			}else{
				System.out.print("Array: ");
			}
		}
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
	public static int[] increaseArray(int[] oldArray)
	{
		int[] a= new int[oldArray.length+1];
		for(int i=0;i<oldArray.length;i++){
			a[i]=oldArray[i];
		}
		return a;
	}
}