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

class MyVector<T> implements IntoIter<T> {
	Vector<T> data;

	MyVector(Vector<T> data) {
		this.data = data;
	}

	public String toString() {
		String out = this.into_iter().fold("[", (acc, x) -> acc + x.toString() + ", ");
		return out.length() != 1 ? out.substring(0, out.length() - 2) + "]" : "[]";
	}

	public Iter<T> into_iter() {
		return new MyVectorIter<T>(this);
	}

	public int getLength() {
		return data.size();
	}

	public T get(int index) {
		return data.get(index);
	}

	public static <I, A extends Iter<I>> MyVector<I> from_iter(A iter) {
		Vector<I> vec = new Vector<I>();
		I x = iter.next();
		while (x != null) {
			vec.add(x);
			x = iter.next();
		}

		return new MyVector<I>(vec);
	}

}

class MyVectorIter<T> extends Iter<T> {
	MyVector<T> arr;
	int index = 0;

	MyVectorIter(MyVector<T> arr) {
		this.arr = arr;
	}

	public T next() {
		if (index >= arr.getLength()) {
			return null;
		}
		return arr.get(index++);
	}
}

abstract class Iter<T> {
	public abstract T next();

	T getNext() {
		return this.next();
	}

	<B> B fold(B init, FoldFunc<B, T> func) {
		B accumulator = init;
		T x = this.next();
		while (x != null) {
			accumulator = func.operation(accumulator, x);
			x = this.next();
		}
		return accumulator;

	}

	Take<T, Iter<T>> take(int n) throws Exception {
		return new Take<T, Iter<T>>(this, n);
	}

	T nth(int n) throws Exception {
		this.advance_by(n);
		return this.next();
	}

	void advance_by(int n) throws Exception {
		for (int i = 0; i < n; i++) {
			if (this.next() == null) {
				throw new Exception("Cannot advance iterator by given n.");
			}
		}
	}

	T find(FindFunc<T> func) {
		Boolean bool;
		T x = this.next();
		while (x != null) {
			bool = func.operation(x);
			if (bool) {
				return x;
			}
			x = this.next();
		}
		return null;
	}

	boolean all(AllAnyFunc<T> func) {
		Boolean bool;
		T x = this.next();
		while (x != null) {
			bool = func.operation(x);
			if (!bool) {
				return false;
			}
		}

		return true;
	}

	boolean any(AllAnyFunc<T> func) {
		Boolean bool;
		T x = this.next();
		while (x != null) {
			bool = func.operation(x);
			if (!bool) {
				return true;
			}
		}

		return true;
	}

	Range size_hint() {
		return new Range(0, -1);
	}

	int count() {
		return this.fold(0, (count, _x) -> count + 1);
	}

	void for_each(ForEachFunc<T> func) {
		T x = this.next();
		while (x != null) {
			func.operation(x);
			x = this.next();
		}
	}

}

interface ForEachFunc<T> {
	public abstract void operation(T element);
}

interface IntoIter<T> {
	public abstract Iter<T> into_iter();
}

// Unfurtunately static abstract methods are impossible to make in Java so the dreams of having .collect<Type>() to convert an iterator into a given type are ruined.
// I will have to just settle to make my own static method for every class I want FromIter<T> without being able to implement. Meaning the type system will not keep me in check.
//  interface FromIter<T> {
//   public static abstract <A extends Iter<T>> FromIter from_iter(A iter);
// }

interface AllAnyFunc<T> {
	boolean operation(T element);
}

abstract class ExactSizeIter<T> extends Iter<T> {
	int len() throws Exception {
		Range range = this.size_hint();
		if (range.getLower() != range.getUpper()) {
			throw new Exception(
					"This iterator failed to confine to ExactSizeIter standard. /nCheck size_hint() for proper implementation.");
		}

		return range.getLower();
	}

	boolean is_empty() throws Exception {
		return this.len() == 0;
	}
}

abstract class DoubleEndedIter<T> extends Iter<T> {
	public abstract T next_back();

	void advance_back_by(int n) throws Exception {
		for (int i = 0; i < n; i++) {
			if (this.next() == null) {
				throw new Exception("Cannot advance_back iterator by given n.");
			}
		}
	}

	T nth_back(int n) throws Exception {
		this.advance_back_by(n);
		return this.next_back();
	}

	<B> B rfold(B init, FoldFunc<B, T> func) {
		B accumulator = init;
		T x = this.next_back();
		while (x != null) {
			accumulator = func.operation(accumulator, x);
			x = this.next_back();
		}
		return accumulator;

	}

	T rfind(FindFunc<T> func) {
		Boolean bool;
		T x = this.next_back();
		while (x != null) {
			bool = func.operation(x);
			if (bool) {
				return x;
			}
			x = this.next_back();
		}
		return null;
	}

}

interface FoldFindMap<T> {
	FoldFunc<T, T> call(FindFunc<T> func);
}

interface FindFunc<T> {
	boolean operation(T element);

}

class Range implements IntoIter<Integer> {
	int lower;
	int upper;

	Range(int lower, int upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public int getLower() {
		return lower;
	}

	public int getUpper() {
		return upper;
	}

  public RangeIter into_iter() {
    return new RangeIter(this); 
  } 

}

class RangeIter extends Iter<Integer> {
  Range range;
  int size;
  int n;

  RangeIter(Range range) {
    this.range = range;
    this.size = range.getUpper() - range.getLower();
    this.n = range.getLower();
  }

  public Integer next() {
    if(size + range.getLower() < n) {
      return null;
    }

    return n++;
  }
}




class Take<T, I extends Iter<T>> extends Iter<T> {
	I iter;
	int n;

	Take(I iter, int n) throws Exception {
		this.iter = iter;
		if (n < 0) {
			throw new Exception("Non-zero n passed to take.");
		}
		this.n = n;
	}

	public T next() {
		if (this.n != 0) {
			this.n -= 1;
			return this.iter.next();
		} else {
			return null;
		}
	}

	T nth(int n) throws Exception {
		if (this.n > n) {
			this.n -= n + 1;

			return this.iter.nth(n);
		} else {
			if (this.n > 0) {
				this.iter.nth(this.n - 1);
				this.n = 0;
			}
			return null;
		}
	}

	Range size_hint() {
		if (this.n == 0) {
			return new Range(0, 0);
		}

		Range range = this.iter.size_hint();

		int lower = Math.min(range.getLower(), this.n);

		int upper = range.getUpper();
		if (upper != -1) {
			if (upper >= this.n) {
				upper = this.n;
			}
		} else {
			upper = this.n;
		}

		return new Range(lower, upper);
	}
}

class Fibo extends Iter<BigInteger> {
	BigInteger cur = BigInteger.ZERO;
	BigInteger next = BigInteger.ONE;

	public BigInteger next() {
		BigInteger current = this.cur;

		this.cur = this.next;
		this.next = current.add(this.next);

		return current;
	}
}

interface FoldFunc<B, T> {
	B operation(B acc, T x);
}
