package JavaIterator;
import java.util.Vector;
import java.util.Optional;

public abstract class Iter<T> {
	public abstract Optional<T> next();

	Optional<T> getNext() {
		return this.next();
	}

	<B> B fold(B init, FoldFunc<B, T> func) {
		B accumulator = init;
		Optional<T> x = this.next();
		while (x.isPresent()){
			accumulator = func.operation(accumulator, x.get());
			x = this.next();
		}
		return accumulator;

	}

	Take<T, Iter<T>> take(int n) throws Exception {
		return new Take<T, Iter<T>>(this, n);
	}

	Optional<T> nth(int n) {
		this.advance_by(n);
		return this.next();
	}

	void advance_by(int n) {
		for (int i = 0; i < n; i++) {
			if (!this.next().isPresent()) {
        return;
			}
		}
	}

	Optional<T> find(FindFunc<T> func) {
		Boolean bool;
		Optional<T> x = this.next();
		while (x.isPresent()) {
			bool = func.operation(x.get());
			if (bool) {
				return x;
			}
			x = this.next();
		}
		return Optional.empty();
	}

	boolean all(AllAnyFunc<T> func) {
		Boolean bool;
		Optional<T> x = this.next();
		while (x.isPresent()) {
			bool = func.operation(x.get());
			if (!bool) {
				return false;
			}
		}

		return true;
	}

	boolean any(AllAnyFunc<T> func) {
		Boolean bool;
		Optional<T> x = this.next();
		while (x.isPresent()) {
			bool = func.operation(x.get());
			if (!bool) {
				return true;
			}
		}

		return true;
	}

	int count() {
		return this.fold(0, (count, _x) -> count + 1);
	}

	void for_each(ForEachFunc<T> func) {
		Optional<T> x = this.next();
		while (x.isPresent()) {
			func.operation(x.get());
			x = this.next();
		}
	}

	Chunk<T> chunk(int size) throws Exception {
		return new Chunk<T>(this, size);
	}

	Chain<T> chain(Iter<T> iter2) {
		return new Chain<T>(this, iter2);
	}

	<I> Ordered cmp_by(IntoIter<I> other, CmpFunc<T, I> func) {
		Iter<I> other2 = other.into_iter();
		Optional<T> x = this.next();
		Optional<I> y = other2.next();
		while (x.isPresent() || y.isPresent()) {
			Ordered ord = func.operation(x.orElse(null), y.orElse(null));
			if (ord != Ordered.EQUAL) {
				return ord;
			}
			x = this.next();
			y = other2.next();
		}

		return Ordered.EQUAL;

	}

	<I extends Comparable<T>> Ordered cmp(IntoIter<I> other) {
		return this.cmp_by(other, (x, y) -> {
			int ord = y.compareTo(x);
			if (ord == 0) {
				return Ordered.EQUAL;
			} else if (ord < 0) {
				return Ordered.GREATER;
			}

			return Ordered.LESSER;

		});
	}

	Enumerate<T> enumerate() {
		return new Enumerate<T>(this);
	}

	<I> boolean eq_by(IntoIter<I> other, CmpFunc<T, I> func) {
		return this.cmp_by(other, func) == Ordered.EQUAL;
	}

	<I extends Comparable<T>> Boolean eq(IntoIter<I> other) {
		return this.cmp(other) == Ordered.EQUAL;
	}

	<I extends Comparable<T>> Boolean lt(IntoIter<I> other) {
		return this.cmp(other) == Ordered.LESSER;
	}

	<I extends Comparable<T>> Boolean gt(IntoIter<I> other) {
		return this.cmp(other) == Ordered.GREATER;
	}

	<I extends Comparable<T>> Boolean le(IntoIter<I> other) {
		return !this.gt(other);
	}

	<I extends Comparable<T>> Boolean ge(IntoIter<I> other) {
		return !this.lt(other);
	}

	<I extends Comparable<T>> Boolean ne(IntoIter<I> other) {
		return !this.eq(other);
	}

	Filter<T> filter(FindFunc<T> predicate) {
		return new Filter<T>(this, predicate);
	}

	<B> Optional<B> find_map(FindMapFunc<B, T> predicate) {
		Optional<T> x = this.next();
		Optional<B> item = Optional.empty();
		while (true) {
			if (!x.isPresent()) {
				return item;
			}
			item = predicate.operation(x.get());
			if (item.isPresent()) {
				break;
			}
			x = this.next();
		}
		return item;
	}

	<B> FilterMap<B, T> filter_map(FindMapFunc<B, T> predicate) {
		return new FilterMap<B, T>(this, predicate);
	}

	Fuse<T> fuse() {
		return new Fuse<T>(this);
	}

	Cycle<T> cycle() {
		return new Cycle<T>(this);
	}

	Inspect<T> inspect(InspectFunc<T> predicate) {
		return new Inspect<T>(this, predicate);
	}

	Intersperse<T> intersperse(T separator) {
		return new Intersperse<T>(this, separator);
	}

	IntersperseWith<T> intersperse_with(IntersperseFunc<T> separator) {
		return new IntersperseWith<T>(this, separator);
	}

  Optional<T> last() {
    return this.fold(Optional.empty(), (_acc, x) -> Optional.of(x));
  }

  Peekable<T> peekable() {
    return new Peekable<T>(this);
  }

  Tuple<Vector<T>, Vector<T>> partition(PartitionFunc<T> f) {
    Vector<T> accept = new Vector<T>(); 
    Vector<T> deny = new Vector<T>(); 

    Optional<T> x = this.next();
    while(x.isPresent()) {
      if (f.operation(x.get())) {
        accept.add(x.get());
      } else {
        deny.add(x.get());
      }
      x = this.next();
    }

    return new Tuple<Vector<T>, Vector<T>>(accept, deny);
  }

  Integer position(PositionFunc<T> f) {
    int start = 0;
    Optional<T> x = this.next();
    while(x.isPresent() && !f.operation(x.get())) {
      x = this.next();
      start++;
    } 

    if (!x.isPresent()) {
      return null;
    } else {
      return start;
    }
  }

  T reduce(FoldFunc<T, T> f) {
    Optional<T> first = this.next();
    return this.fold(first.get(), f);
  }

  Skip<T> skip(int n) {
    return new Skip<T>(this, n);
  }
}

interface PositionFunc<T> {
  boolean operation(T element);
}

interface PartitionFunc<T> {
  boolean operation(T element);
}

interface FindMapFunc<B, T> {
	Optional<B> operation(T element);
}

interface ForEachFunc<T> {
	public abstract void operation(T element);
}

interface CmpFunc<X, Y> {
	Ordered operation(X item, Y other);
}

interface AllAnyFunc<T> {
	boolean operation(T element);
}

interface FindFunc<T> {
	boolean operation(T element);

}

interface FoldFunc<B, T> {
	B operation(B acc, T x);
}

// Unfurtunately static abstract methods are impossible to make in Java so the
// dreams of having .collect<Type>() to convert an iterator into a given type
// are ruined.
// I will have to just settle to make my own static method for every class I
// want FromIter<T> without being able to implement. Meaning the type system
// will not keep me in check.
// interface FromIter<T> {
// public static abstract <A extends Iter<T>> FromIter from_iter(A iter);
// }
