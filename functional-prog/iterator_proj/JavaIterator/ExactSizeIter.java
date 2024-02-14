package JavaIterator;
public abstract class ExactSizeIter<T> extends Iter<T> {
	int len() throws Exception {
		Range range = this.size_hint();
		if (range.getLower() != range.getUpper()) {
			throw new Exception(
					"This iterator failed to confine to ExactSizeIter standard. /nCheck size_hint() for proper implementation.");
		}

		return range.getLower();
	}

	boolean is_empty() throws Exception {
		return this.len() == 0;
	}
}

