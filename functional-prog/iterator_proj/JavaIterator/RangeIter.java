package JavaIterator;

import java.util.Optional;

public class RangeIter extends Iter<Integer> {
  Range range;
  int size;
  int n;

  RangeIter(Range range) {
    this.range = range;
    this.size = range.getUpper() - range.getLower();
    this.n = range.getLower();
  }

  public Optional<Integer> next() {
    if(size + range.getLower() < n) {
      return Optional.empty();
    }

    return Optional.of(n++);
  }
}
