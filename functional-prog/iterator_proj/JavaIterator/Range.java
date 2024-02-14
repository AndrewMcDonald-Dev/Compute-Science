package JavaIterator;

public class Range implements IntoIter<Integer> {
	int lower;
	int upper;

	Range(int lower, int upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public int getLower() {
		return lower;
	}

	public int getUpper() {
		return upper;
	}

  public RangeIter into_iter() {
    return new RangeIter(this); 
  } 

}
