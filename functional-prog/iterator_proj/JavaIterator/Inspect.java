package JavaIterator;
class Inspect<T> extends Iter<T> {
  Iter<T> iter;
  InspectFunc<T> predicate;

  Inspect(Iter<T> iter, InspectFunc<T> predicate) {
    this.iter = iter;
    this.predicate = predicate;
  }

  public T next() {
    T x = this.iter.next();
    predicate.operation(x);
    return x;
  }

}

interface InspectFunc<T> {
  void operation(T element);
}
