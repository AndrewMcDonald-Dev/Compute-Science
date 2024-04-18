package JavaIterator;

import java.util.Optional;

class IntersperseWith<T> extends Iter<T> {
  Peekable<T> iter;
  boolean needsSep;
  IntersperseFunc<T> separator;

  IntersperseWith(Iter<T> iter, IntersperseFunc<T> separator) {
    this.iter = iter.peekable();
    this.separator = separator;
    this.needsSep = false;
  }

  public Optional<T> next() {
    if (this.needsSep && this.iter.peek().isPresent()) {
      this.needsSep = false;
      return Optional.of(separator.operation());
    } else {
      this.needsSep = true;
      return this.iter.next();
    }
  }
}

interface IntersperseFunc<T> {
  T operation();
} 
