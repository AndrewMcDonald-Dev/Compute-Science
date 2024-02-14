package JavaIterator;

public class RangeIter extends ExactSizeIter<Integer> {
  Range range;
  int size;
  int n;

  RangeIter(Range range) {
    this.range = range;
    this.size = range.getUpper() - range.getLower();
    this.n = range.getLower();
  }

  public Integer next() {
    if(size + range.getLower() < n) {
      return null;
    }

    return n++;
  }

  Range size_hint() {
    return new Range(this.size, this.size);
  }
}
