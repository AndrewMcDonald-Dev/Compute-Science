
import java.util.Vector;
class Chunk<T> extends DoubleEndedIter<Vector<T>> {
  Iter<T> iter;
  int size_of_chunk;

  Chunk(Iter<T> iter, int size) throws Exception {
    if (size <= 0) {
      throw new Exception("Initiated chunk with bad size.");
    }
    this.iter = iter;
    this.size_of_chunk = size;

  }

  public Vector<T> next() {
    Vector<T> vec = new Vector<T>();
    int local_size = size_of_chunk;
    while(local_size-- != 0) {
      T x = this.iter.next_back();
      if (x != null) {
        vec.add(x);
      }
    }

    if (vec.isEmpty()) {
      return null;
    }
    return vec;
  }

  
}
