import java.math.BigInteger;
import java.util.Vector;

public class main {
	public static void main(String[] args) throws Exception {

    // nth test on Fibo 
		Fibo fib = new Fibo();
		BigInteger result = fib.nth(4);
		System.out.println(result);

    // find test on Fibo
		Fibo fib2 = new Fibo();
		BigInteger result2 = fib2.find((x) -> x.equals(new BigInteger("5")));
		System.out.println(result2);
		System.out.println(fib2.next());

    // take and count test on Fibo
		Fibo fib3 = new Fibo();
		int result3 = fib3.take(10).count();
		System.out.println(result3);

    // take and fold test on Fibo
		Fibo fib4 = new Fibo();
		BigInteger result4 = fib4.take(4).fold(BigInteger.ZERO, (acc, x) -> acc.add(x));
		System.out.println(result4);

		// MyArrayList example
		Vector<Integer> myNum2 = new Vector<Integer>();
		myNum2.add(1);
		myNum2.add(2);
		myNum2.add(3);
		myNum2.add(4);
		myNum2.add(5);
		MyVector<Integer> arr = new MyVector<Integer>(myNum2);
		System.out.println(MyVector.from_iter(arr.into_iter().take(3)));
		System.out.println(arr);

    //Range Example
    Range range = new Range(2, 5);
    System.out.println(MyVector.from_iter(range.into_iter()));
	}
}
