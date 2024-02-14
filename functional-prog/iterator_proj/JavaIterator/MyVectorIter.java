package JavaIterator;
public class MyVectorIter<T> extends Iter<T> {
	MyVector<T> arr;
	int index = 0;

	MyVectorIter(MyVector<T> arr) {
		this.arr = arr;
	}

	public T next() {
		if (index >= arr.getLength()) {
			return null;
		}
		return arr.get(index++);
	}
}

