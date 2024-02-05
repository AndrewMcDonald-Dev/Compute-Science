import java.math.BigInteger;

public class main {
	public static void main(String[] args) throws Exception {
		Fibo fib = new Fibo();
		// BigInteger result = fib.take(4).fold(BigInteger.ZERO, (acc, x) ->
		// acc.add(x));
		BigInteger result = fib.nth(4);
		System.out.println(result);
	}
}

abstract class Iter<T> {
	public abstract T next();

	T getNext() {
		return this.next();
	}

	T fold(T init, FoldFunc<T> func) {
		T accumulator = init;
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

interface FoldFunc<T> {
	T operation(T acc, T x);
}