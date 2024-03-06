package JavaIterator;
class Peekable<T> extends Iter<T> {
  Iter<T> iter;
  T peeked;

  Peekable(Iter<T> iter) {
    this.iter = iter;
    this.peeked = null;
  }

  public T next() {
    if (this.peeked == null) {
      return this.iter.next();
    } else {
      T temp = this.peeked;
      this.peeked = null;
      return temp;
    }
  }

  int count() {
    if (this.peeked != null) {
      return 1 + this.iter.count();
    } else {
      return this.iter.count();
    }
  }

  T nth(int n) {
    if (this.peeked != null) {
      return this.iter.nth(n - 1);
    } else {
      return this.iter.nth(n);
    }
  }

  T last() {
    T x = this.iter.last();
    if (x != null) {
      return x;
    } else {
      return peeked;
    }
  }

	<B> B fold(B init, FoldFunc<B, T> func) {
		B accumulator = init;
    if (this.peeked != null) {
      accumulator = func.operation(init, this.peeked);
    }
    return this.iter.fold(accumulator, func);
	}

  T peek() {
    if(this.peeked == null){
      this.peeked = this.iter.next();
    }
    return this.peeked;
  }
}
