package JavaIterator;
public abstract class DoubleEndedIter<T> extends Iter<T> {
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

  Chunk_back<T> chunk_back(int size) throws Exception {
    return new Chunk_back<T>(this, size);
  }
}
