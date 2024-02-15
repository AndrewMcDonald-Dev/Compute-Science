package JavaIterator;
class FilterMap<B, T> extends Iter<B>{
  Iter<T> iter;
  FindMapFunc<B,T> predicate;

  FilterMap(Iter<T> iter, FindMapFunc<B, T> predicate) {
    this.iter = iter;
    this.predicate = predicate;
  }

  public B next() {
    return this.iter.find_map(predicate);
  }


} 


