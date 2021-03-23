class Q12
{
	public static void main(String[] args)
	{
		int weight = 195;
		int height = 76;
		int x = weight*703;
		int y = height*height;
		int BMI = x/y;
		if(BMI<18.5){
			System.out.print(BMI + ": Underwieght");
		}else if(BMI<=25){
			System.out.print(BMI + ": Normal");
		}else if(BMI<=30){
			System.out.print(BMI + ": Overweight");
		}else{
			System.out.println(BMI + ": Obese");
		}
	}
}