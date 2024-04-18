package JavaIterator;

import java.util.Optional;

public class MyVectorIter<T> extends Iter<T> {
	MyVector<T> arr;
	int index = 0;

	MyVectorIter(MyVector<T> arr) {
		this.arr = arr;
	}

	public Optional<T> next() {
		if (index >= arr.getLength()) {
			return Optional.empty();
		}
		return Optional.ofNullable(arr.get(index++));
	}
}

