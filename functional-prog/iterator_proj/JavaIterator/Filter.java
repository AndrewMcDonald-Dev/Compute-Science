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

  int count() {
    return this.fold(0, (count, _x) -> count + 1);   
  }


	<B> B fold(B init, FoldFunc<B, T> func) {
		B accumulator = init;
		T x = this.next();
		while (x != null) {
			accumulator = func.operation(accumulator, x);
			x = this.next();
		}
		return accumulator;

	}
}




