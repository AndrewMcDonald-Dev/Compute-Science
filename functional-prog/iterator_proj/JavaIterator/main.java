package JavaIterator;

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

		// Range Example
		Range range = new Range(2, 5);
		System.out.println(MyVector.from_iter(range.into_iter()));

		// Chunk example with Fibo
		Fibo fib5 = new Fibo();
		fib5.take(19).chunk(2).for_each((x) -> {
			System.out.println(x);
		});

		// Chain example with Range;
		Range chain_range_1 = new Range(2, 5);
		Range chain_range_2 = new Range(5, 9);
		chain_range_1.into_iter().chain(chain_range_2.into_iter()).for_each((x) -> {
			System.out.println(x);
		});

		// cmp_by example with Range
		Range cmp_range_1 = new Range(2, 5);
		Range cmp_range_2 = new Range(5, 9);
		System.out.println(cmp_range_2.into_iter().cmp_by(cmp_range_1, (x, y) -> {
			int ord = x.compareTo(y);
			if (ord == 0) {
				return Ordered.EQUAL;
			} else if (ord < 0) {
				return Ordered.LESSER;
			}

			return Ordered.GREATER;

		}));

		// cmp example with Range
		System.out.println(cmp_range_1.into_iter().cmp(cmp_range_2));
		System.out.println(cmp_range_1.into_iter().cmp(cmp_range_1));

		// Enumerate example with MyVector
		arr.into_iter().enumerate().for_each((x) -> {
			System.out.println("(" + x.get0() + "," + x.get1() + ")");
		});

		// Comparison Operator functions testing
		System.out.println(cmp_range_1.into_iter().eq(cmp_range_1));
		System.out.println(cmp_range_1.into_iter().eq(cmp_range_2));
		System.out.println(cmp_range_1.into_iter().lt(cmp_range_2));
		System.out.println(cmp_range_1.into_iter().gt(cmp_range_2));
		System.out.println(cmp_range_1.into_iter().le(cmp_range_2));
		System.out.println(cmp_range_1.into_iter().ge(cmp_range_2));
		System.out.println(cmp_range_1.into_iter().ne(cmp_range_2));
		System.out.println(cmp_range_1.into_iter().ne(cmp_range_1));

		// Filter example with Range
		System.out.println(cmp_range_1.into_iter().filter((x) -> x % 2 == 0).count());
		cmp_range_1.into_iter().filter((x) -> x % 2 == 0).for_each((x) -> {
			System.out.println(x);
		});

		// Find map example with MyVector
		Vector<String> string_vec = new Vector<String>();
		string_vec.add("test");
		string_vec.add("1");
		string_vec.add("14");
		string_vec.add("test");
		MyVector<String> myString_vec = new MyVector<String>(string_vec);
		// Have to strongly type to help java figure it out.
		Integer temp = myString_vec.into_iter().find_map((x) -> {
			try {
				return Integer.parseInt(x);
			} catch (NumberFormatException _e) {
				return null;
			}
		});
		System.out.println(temp);

		// Filter map example with MyVector
		System.out.println(myString_vec.into_iter().filter_map((x) -> {
			try {
				return Integer.parseInt(x);
			} catch (NumberFormatException _e) {
				return null;
			}
		}).fold(0, (acc, x) -> acc + x));

		// Fuse example with MyVector
		string_vec.add(null);
		string_vec.add("Not printable");
		Iter<String> myStringIter = myString_vec.into_iter().fuse();
		System.out.println(myStringIter.next());
		System.out.println(myStringIter.next());
		System.out.println(myStringIter.next());
		System.out.println(myStringIter.next());
		System.out.println(myStringIter.next());
		System.out.println(myStringIter.next());

		// Cycle example with Range
		System.out.println(cmp_range_1.into_iter().cycle().nth(99));

		// Inspect example with Fibo
		Fibo fib6 = new Fibo();
		fib6
				.take(5)
				.inspect((x) -> {
					System.out.println("About to filter " + x);
				})
				.filter((x) -> x.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
				.inspect((x) -> {
					System.out.println("Element made it through " + x);
				})
				.count();

		// Intersperse example with MyVector
		myString_vec.into_iter().fuse().intersperse("Sep").for_each((x) -> {
			System.out.println(x);
		});

    //Intersperse_with example with 2 MyVector
    Iter<String> myTempIter = myString_vec.into_iter();
		myString_vec.into_iter().fuse().intersperse_with(() -> myTempIter.next()).for_each((x) -> {
			System.out.println(x);
		});

    //Last example with range
    System.out.println(cmp_range_1.into_iter().last());
    //Last example with MyVector
    System.out.println(myString_vec.into_iter().last());

    //Peekable examples with Fib

    
	}
}
