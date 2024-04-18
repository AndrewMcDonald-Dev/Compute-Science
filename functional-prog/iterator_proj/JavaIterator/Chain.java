package JavaIterator;

import java.util.Optional;

public class Chain<T> extends Iter<T> {
  Iter<T> a;
  Iter<T> b;
  boolean swap = false;
  Chain(Iter<T> a, Iter<T> b) {
    this.a = a;
    this.b = b;
  }

  public Optional<T> next() {
    if (!swap) {
      Optional<T> x = this.a.next();
      if (!x.isPresent()) {
        this.swap = true;
      } else {
        return x;
      }
    }
    return this.b.next();
  }
}
