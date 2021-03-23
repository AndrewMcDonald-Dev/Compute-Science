class Lecture
{
	public static void main(String[] args)
	{
		double sum=0;
		int count=0;
		for(int i=200;i<=500;i++){
			if(i%2!=0){
				count++;
				sum=i+sum;
			}
		}
		System.out.println("Average = "+(sum/count));
	}
}