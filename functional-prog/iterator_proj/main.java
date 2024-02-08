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

    //Chunk example with Fibo
    Fibo fib5 = new Fibo();
    fib5.take(19).chunk(2).for_each((x) -> {
      System.out.println(x);
    });


    //Chain example with Range;
    Range chain_range_1 = new Range(2,5);
    Range chain_range_2 = new Range(5,9);
    chain_range_1.into_iter().chain(chain_range_2.into_iter()).for_each((x) -> {
      System.out.println(x);
    });


    //cmp_by example with Range
    Range cmp_range_1 = new Range(2,5);
    Range cmp_range_2 = new Range(5,9);
    cmp_range_1.into_iter().cmp_by(cmp_range_2, (x, y) -> {
      int ord  = x.compareTo(y);
      if (ord == 0) {
        return Ordered.EQUAL;
      } else if (ord < 0) {
        return Ordered.LESSER;
      }

      return Ordered.GREATER;

    });


    //Big fibo
    Fibo fib6 = new Fibo();
    System.out.println(fib6.nth(10));
	}
}
