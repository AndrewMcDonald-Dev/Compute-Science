import java.util.Vector;
public class MyVector<T> implements IntoIter<T> {
	Vector<T> data;

	MyVector(Vector<T> data) {
		this.data = data;
	}

	public String toString() {
		String out = this.into_iter().fold("[", (acc, x) -> acc + x.toString() + ", ");
		return out.length() != 1 ? out.substring(0, out.length() - 2) + "]" : "[]";
	}

	public Iter<T> into_iter() {
		return new MyVectorIter<T>(this);
	}

	public int getLength() {
		return data.size();
	}

	public T get(int index) {
		return data.get(index);
	}

	public static <I, A extends Iter<I>> MyVector<I> from_iter(A iter) {
		Vector<I> vec = new Vector<I>();
		I x = iter.next();
		while (x != null) {
			vec.add(x);
			x = iter.next();
		}

		return new MyVector<I>(vec);
	}

}
