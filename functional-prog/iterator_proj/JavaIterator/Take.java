package JavaIterator;

import java.util.Optional;

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

	public Optional<T> next() {
		if (this.n != 0) {
			this.n -= 1;
			return this.iter.next();
		} else {
			return Optional.empty();
		}
	}

	Optional<T> nth(int n) {
		if (this.n > n) {
			this.n -= n + 1;

			return this.iter.nth(n);
		} else {
			if (this.n > 0) {
				this.iter.nth(this.n - 1);
				this.n = 0;
			}
			return Optional.empty();
		}
	}
}

