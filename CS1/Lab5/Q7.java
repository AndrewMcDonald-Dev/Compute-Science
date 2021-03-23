class Q7
{
	public static void main(String[] arg)
	{
		double[] grades={98.6,78.2,56.9};
		double[] newGrades=new double[grades.length+1];
		for(int i=0;i<grades.length;i++){
			newGrades[i]=grades[i];
		}
		newGrades[grades.length]=88.8;
	}
}