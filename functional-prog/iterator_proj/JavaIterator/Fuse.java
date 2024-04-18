package JavaIterator;

import java.util.Optional;

class Fuse<T> extends Iter<T> {
  Iter<T> iter;
  boolean nulled;

  Fuse(Iter<T> iter) {
    this.iter = iter;
  }

  public Optional<T> next() {
    if (nulled) {
      return Optional.empty();
    }
    Optional<T> x = this.iter.next();
    if (!x.isPresent()) {
      nulled = true;
      return Optional.empty();
    }
    return x;
  }
}
