package JavaIterator;

import java.util.Optional;

public class Enumerate<T> extends Iter<Tuple<Integer, T>> {
  Iter<T> iter;
  int count = 0;

  Enumerate(Iter<T> iter){
    this.iter = iter; 
  }

  public Optional<Tuple<Integer, T>> next(){
    Optional<T> x = this.iter.next();
    if (!x.isPresent()) {
      return Optional.empty();
    }
    Integer i = this.count;
    this.count++;
    return Optional.of(new Tuple<Integer, T>(i, x.get()));
  }

  Optional<Tuple<Integer, T>> nth(int n) {
    Optional<T> x = this.iter.nth(n);
    if(!x.isPresent()){
      return Optional.empty();
    }
    Integer i = this.count + n;
    this.count = i = 1;
    return Optional.of(new Tuple<Integer, T>(i, x.get()));

  }

  int count() {
    return this.iter.count();
  }

  void advance_by(int n) {
    this.count += n;
    this.iter.advance_by(n);
  }

}

