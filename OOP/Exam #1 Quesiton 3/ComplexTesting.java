import java.util.*;
//class testing Ops interface operations and compareTo with ComplexNumbers.
public class ComplexTesting
//testing Ops interface operations and compareTo with Complex
//At the very end, the Comparator interface is implemented "anonymously"
//to permit the sorting of complex numbers by reverse order of the real parts.
{
	public static void main(String[] args)
	{
		ComplexNumbers z = new ComplexNumbers(3,4);
		ComplexNumbers w = new ComplexNumbers(2,-3);
		ComplexNumbers u = z.mult(w);
		System.out.print("z * w = ");
		u.complexPrint();

		ComplexNumbers x = new ComplexNumbers(2,4);
		ComplexNumbers y = new ComplexNumbers(-3,0);
		ComplexNumbers r = new ComplexNumbers(0,14);

		ArrayList<ComplexNumbers> complexList = new ArrayList<ComplexNumbers>();
		complexList.add(z);
		complexList.add(u);
		complexList.add(w);
		complexList.add(x);
		complexList.add(y);
		complexList.add(r);
		System.out.println();
		System.out.println("original list of complex numbers");
		for(ComplexNumbers c: complexList)
		{
			c.complexPrint();
		}
		Collections.sort(complexList);//the default comparison is with Comparable,
 	//based on compareTo.
		System.out.println();
		System.out.println( "sorted list of complex numbers");
		for(ComplexNumbers c: complexList)
		{
			c.complexPrint();
		}
		// ComplexNumbersByReverseReal comp = new ComplexNumbersByReverseReal();
		ComplexNumbersByProduct comp = new ComplexNumbersByProduct();

		Collections.sort(complexList, comp);
		System.out.println();
		System.out.println( "sorted list of complex numbers");
		for(ComplexNumbers c: complexList)
		{
			c.complexPrint();
		}

 	//////////////////////implementing Comparator anonymously
 	//////////////////////with increasing real part as primary,
 	/////////////////////decreasing imaginary part as secondary
		Collections.sort(complexList, new Comparator<ComplexNumbers>()
		{
			public int compare(ComplexNumbers z, ComplexNumbers w)
			{
				return (int)((z.getIm()==w.getIm())?
				Math.signum(z.getRe() + w.getRe()):
				Math.signum((z.getIm() - w.getIm())));
				// double reZ = z.getRe();
				// double imZ = z.getIm();
				// double reW = w.getRe();
				// double imW = w.getIm();
				// if( (reZ < reW) || ((reZ == reW) && (imZ > imW)))
				// {
				// 	return -1;
				// }
				// else if ((reW == reZ) && (imW == reZ))
				// {
				// 	return 0;
				// }
				// else
				// {
				// return 1;
				// }
			}
		});
		System.out.println();
		System.out.println( "another sort of same list of complex numbers");
		for(ComplexNumbers c: complexList)
		{
			c.complexPrint();
		}
	}
}
