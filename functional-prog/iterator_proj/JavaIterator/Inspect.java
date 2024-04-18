package JavaIterator;

import java.util.Optional;


class Inspect<T> extends Iter<T> {
  Iter<T> iter;
  InspectFunc<T> predicate;

  Inspect(Iter<T> iter, InspectFunc<T> predicate) {
    this.iter = iter;
    this.predicate = predicate;
  }

  public Optional<T> next() {
    Optional<T> x = this.iter.next();
    predicate.operation(x.orElse(null));
    return x;
  }

}

interface InspectFunc<T> {
  void operation(T element);
}
