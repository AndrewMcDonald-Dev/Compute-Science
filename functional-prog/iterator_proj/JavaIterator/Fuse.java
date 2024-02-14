package JavaIterator;
class Fuse<T> extends Iter<T> {
  Iter<T> iter;
  boolean nulled;

  Fuse(Iter<T> iter) {
    this.iter = iter;
  }

  public T next() {
    if (nulled) {
      return null;
    }
    T x = this.iter.next();
    if (x == null) {
      nulled = true;
      return null;
    }
    return x;
  }
}
