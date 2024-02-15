package JavaIterator;
class Intersperse<T> extends Iter<T> {
  Peekable<T> iter;
  T separator;
  boolean needsSep;

  Intersperse(Iter<T> iter, T separator) {
    this.iter = iter.peekable();
    this.separator = separator;
    this.needsSep = false;
  }

  public T next() {
    if (this.needsSep && this.iter.peek() != null) {
      this.needsSep = false;
      return separator;
    } else {
      this.needsSep = true;
      return this.iter.next();
    }
  }
}
