package JavaIterator;
public class Chain<T> extends Iter<T> {
  Iter<T> a;
  Iter<T> b;
  boolean swap = false;
  Chain(Iter<T> a, Iter<T> b) {
    this.a = a;
    this.b = b;
  }

  public T next() {
    if (!swap) {
      T x = this.a.next();
      if (x == null) {
        this.swap = true;
      } else {
        return x;
      }
    }

    T x2 = this.b.next();
    return x2;
  }
}
