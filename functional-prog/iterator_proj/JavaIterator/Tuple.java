package JavaIterator; 
public class Tuple<T, K> {
  T item1;
  K item2;

  Tuple(T item1, K item2){
    this.item1 = item1;
    this.item2 = item2;
  }

  T get0() {
    return this.item1;
  }

  K get1() {
    return this.item2;
  }
}

