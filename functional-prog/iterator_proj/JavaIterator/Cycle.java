package JavaIterator;
class Cycle<T> extends Iter<T> {
  Iter<T> iter;
  MyVector<T> cycled;

  Cycle(Iter<T> iter) {
    this.cycled = MyVector.from_iter(iter);
    this.iter = this.cycled.into_iter();
  }

  public T next() {
    T x = this.iter.next();
    if (x != null) {
      return x;
    }

    this.iter = this.cycled.into_iter();
    return this.iter.next();
  }
}
