package JavaIterator;

public class Take<T, I extends Iter<T>> extends Iter<T> {
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

