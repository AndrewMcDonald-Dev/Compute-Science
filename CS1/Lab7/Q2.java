import java.util.Scanner; 
class Q2
{
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter String: ");
		String s=kb.nextLine();
		System.out.print("Enter Character: ");
		char c=kb.next().charAt(0);
		charCounter(s,c);
	}
	public static void charCounter(String base,char character)
	{
		int count=0;
		for(int i=0;i<base.length();i++){
			if(base.charAt(i)==character){
				count++;
			}
		}
		System.out.println(base+" has "+count+" "+character+"'s");
	}
}