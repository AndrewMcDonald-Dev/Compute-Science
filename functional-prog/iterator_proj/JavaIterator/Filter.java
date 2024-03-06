package JavaIterator;

public class Filter<T> extends Iter<T> {
  Iter<T> iter;
  FindFunc<T> predicate;

  Filter(Iter<T> iter, FindFunc<T> predicate){
    this.iter = iter;
    this.predicate = predicate;
  }

  public T next() {
    return this.iter.find(this.predicate);
  }
}




