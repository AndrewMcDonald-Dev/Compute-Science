package JavaIterator;

import java.util.Optional;

class Cycle<T> extends Iter<T> {
  Iter<T> iter;
  MyVector<T> cycled;

  Cycle(Iter<T> iter) {
    this.cycled = MyVector.from_iter(iter);
    this.iter = this.cycled.into_iter();
  }

  public Optional<T> next() {
    Optional<T> x = this.iter.next();
    if (x.isPresent()) {
      return x;
    }

    this.iter = this.cycled.into_iter();
    return this.iter.next();
  }
}
