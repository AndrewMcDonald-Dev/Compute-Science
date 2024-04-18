package JavaIterator;

import java.util.Optional;

public class Skip<T> extends Iter<T> {
  Iter<T> iter;
  int n;

  Skip(Iter<T> iter, int n) {
    this.iter = iter;
    this.n = n;
  } 

  public Optional<T> next() {
      if (this.n > 0) {
        int new_n = this.n;
        this.n = 0;
        return this.iter.nth(new_n);
      } else {
        return this.iter.next();
      }
  }

  Optional<T> nth(int n) {
    if (this.n > 0) {
      int new_n = this.n;
      this.n = 0;
      int new_new_n = n + this.n;
      return this.iter.nth(new_new_n);
    } else {
      return this.iter.nth(n);
    }
  }

  int count() {
    if (this.n > 0) {
      if (!this.iter.nth(this.n - 1).isPresent()) {
        return 0;
      }
    }
    return this.iter.count();
  }

  Optional<T> last() {
    if (this.n > 0) {
      this.iter.nth(this.n - 1);
    }
    return this.iter.last();
  }

  
	<B> B fold(B init, FoldFunc<B, T> func) {
    if (this.n > 0) {
      if (!this.iter.nth(this.n - 1).isPresent()) {
        return init;
      }
    }
    return this.iter.fold(init, func);
	}
}
