
class RangeIter extends Iter<Integer> {
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
}
