package JavaIterator;

import java.util.Optional;

class Peekable<T> extends Iter<T> {
  Iter<T> iter;
  Optional<T> peeked;

  Peekable(Iter<T> iter) {
    this.iter = iter;
    this.peeked = Optional.empty();
  }

  public Optional<T> next() {
    if (!this.peeked.isPresent()) {
      return this.iter.next();
    } else {
      Optional<T> temp = this.peeked;
      this.peeked = Optional.empty();
      return temp;
    }
  }

  int count() {
    if (this.peeked.isPresent()) {
      return 1 + this.iter.count();
    } else {
      return this.iter.count();
    }
  }

  Optional<T> nth(int n) {
    if (this.peeked.isPresent()) {
      return this.iter.nth(n - 1);
    } else {
      return this.iter.nth(n);
    }
  }

  Optional<T> last() {
    Optional<T> x = this.iter.last();
    if (x.isPresent()) {
      return x;
    } else {
      return peeked;
    }
  }

	<B> B fold(B init, FoldFunc<B, T> func) {
		B accumulator = init;
    if (this.peeked.isPresent()) {
      accumulator = func.operation(init, this.peeked.get());
    }
    return this.iter.fold(accumulator, func);
	}

  Optional<T> peek() {
    if(!this.peeked.isPresent()){
      this.peeked = this.iter.next();
    }
    return this.peeked;
  }
}
