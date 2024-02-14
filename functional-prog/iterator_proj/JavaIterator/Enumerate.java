package JavaIterator;
public class Enumerate<T> extends Iter<Tuple<Integer, T>> {
  Iter<T> iter;
  int count = 0;

  Enumerate(Iter<T> iter){
    this.iter = iter; 
  }

  public Tuple<Integer, T> next(){
    T x = this.iter.next();
    if (x == null) {
      return null;
    }
    Integer i = this.count;
    this.count++;
    return new Tuple<Integer, T>(i, x);
  }

  Range size_hint() {
    return this.iter.size_hint();
  }

  Tuple<Integer, T> nth(int n) throws Exception {
    T x = this.iter.nth(n);
    if(x == null){
      return null;
    }
    Integer i = this.count + n;
    this.count = i = 1;
    return new Tuple<Integer, T>(i, x);

  }

  int count() {
    return this.iter.count();
  }

  void advance_by(int n) throws Exception {
    this.count += n;
    this.iter.advance_by(n);
  }

}

