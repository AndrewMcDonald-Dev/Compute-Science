import java.util.Scanner;

/** complex numbers with addition and multiplication */
public class ComplexNumbers implements Ops<ComplexNumbers> ,Comparable<ComplexNumbers>
{
	Scanner kb = new Scanner(System.in);
	private double re;
	private double im;

/** constructor inputs a complex number as a pair of double values,
the real and imaginary parts*/
	public ComplexNumbers()
	{
		System.out.println("enter real part: ");
		re = kb.nextDouble();
		System.out.println("enter imaginary part: ");
		im = kb.nextDouble();
	}
/** parameterized constructor
	@param x real part
	@param y imaginary part
*/
	public ComplexNumbers(double x, double y)
	{
		re = x;
		im = y;
	}

 /** @return real part */
	public double getRe()
	{
		return re;
	}

/** @param x real part */
	public void setRe(double x)
	{
		re = x;
	}

/** @return imaginary part */
	public double getIm()
	{
		return im;
	}

 /** @param y imaginary part */
	public void setIm(double y)
	{
		im = y;
	}

	public void complexPrint()
	{
		if((getRe() > 0) && (getIm() > 0))
		{
			System.out.println(re + " + " + im + "I");
		}
		else if(getIm() == 0)
		{
			System.out.println(re);
		}
		else if(getRe() == 0)
		{
			System.out.println(im + "I");
		}
		else if(getIm() < 0)
		{
			System.out.println(re + " " + im + "I");
		}
	}

/** Multiplication of complex numbers .this and w
@param w operand 2
 @return product .this * w
*/
	public ComplexNumbers mult(ComplexNumbers w)
	{
		double a = getRe();
		double b = getIm();
		double c = w.getRe();
		double d = w.getIm();
		ComplexNumbers u = new ComplexNumbers(a*c - b*d, a*d + b*c);
		return u;
	}

/** addition of complex numbers .this and w
@param w complex numbr added to .this
@return sum .this + w
*/
	public ComplexNumbers add(ComplexNumbers w)
	{
		double a = getRe();
		double b = getIm();
		double c = w.getRe();
		double d = w.getIm();
		ComplexNumbers u = new ComplexNumbers(a+c, b+d);
		return u;
	}

	public int compareTo(ComplexNumbers w)
	{


		return (int)(Math.signum(((re*re)+(im*im)) - ((w.getRe() * w.getRe()) 
		+ (w.getIm() * w.getRe()))));
		// if(getRe() < w.getRe() ||
		// ((getRe() == w.getRe()) && (getIm() < w.getIm())))
		// {
		// 	return -1;
		// }
		// else if((getRe() == w.getRe()) && (getIm() == w.getIm()))
		// {
		// 	return 0;
		// }
		// else
		// {
		// 	return 1;
		// }
	}

  	public void minComplex(ComplexNumbers w) //display the smaller of .this and w
	{
		if(this.compareTo(w) <= 0)
		{
			complexPrint();
		}
		else
		{
			w.complexPrint();
		}
	}
}