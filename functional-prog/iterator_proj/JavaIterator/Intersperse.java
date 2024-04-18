package JavaIterator;

import java.util.Optional;

class Intersperse<T> extends Iter<T> {
  Peekable<T> iter;
  T separator;
  boolean needsSep;

  Intersperse(Iter<T> iter, T separator) {
    this.iter = iter.peekable();
    this.separator = separator;
    this.needsSep = false;
  }

  public Optional<T> next() {
    if (this.needsSep && this.iter.peek().isPresent()) {
      this.needsSep = false;
      return Optional.of(separator);
    } else {
      this.needsSep = true;
      return this.iter.next();
    }
  }
}
