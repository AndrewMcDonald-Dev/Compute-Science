package JavaIterator;
public abstract class Iter<T> {
	public abstract T next();

	T getNext() {
		return this.next();
	}

	<B> B fold(B init, FoldFunc<B, T> func) {
		B accumulator = init;
		T x = this.next();
		while (x != null) {
			accumulator = func.operation(accumulator, x);
			x = this.next();
		}
		return accumulator;

	}

	Take<T, Iter<T>> take(int n) throws Exception {
		return new Take<T, Iter<T>>(this, n);
	}

	T nth(int n) throws Exception {
		this.advance_by(n);
		return this.next();
	}

	void advance_by(int n) throws Exception {
		for (int i = 0; i < n; i++) {
			if (this.next() == null) {
				throw new Exception("Cannot advance iterator by given n.");
			}
		}
	}

	T find(FindFunc<T> func) {
		Boolean bool;
		T x = this.next();
		while (x != null) {
			bool = func.operation(x);
			if (bool) {
				return x;
			}
			x = this.next();
		}
		return null;
	}

	boolean all(AllAnyFunc<T> func) {
		Boolean bool;
		T x = this.next();
		while (x != null) {
			bool = func.operation(x);
			if (!bool) {
				return false;
			}
		}

		return true;
	}

	boolean any(AllAnyFunc<T> func) {
		Boolean bool;
		T x = this.next();
		while (x != null) {
			bool = func.operation(x);
			if (!bool) {
				return true;
			}
		}

		return true;
	}

	Range size_hint() {
		return new Range(0, -1);
	}

	int count() {
		return this.fold(0, (count, _x) -> count + 1);
	}

	void for_each(ForEachFunc<T> func) {
		T x = this.next();
		while (x != null) {
			func.operation(x);
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
    T x = this.next();
    I y = other2.next();
    while (x != null || y!= null){
      Ordered ord = func.operation(x, y);
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
      int ord  = y.compareTo(x);
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

  <B> B find_map(FindMapFunc<B, T> predicate) {
    T x = this.next();
    B item = null;
    while (true) {
      if(x == null){
        return null;
      }
      item = predicate.operation(x);
      if(item != null) {
        break;
      }
      x = this.next();
    }
    return item;
  }
}

interface FindMapFunc<B, T> {
  B operation(T element);
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

// Unfurtunately static abstract methods are impossible to make in Java so the dreams of having .collect<Type>() to convert an iterator into a given type are ruined.
// I will have to just settle to make my own static method for every class I want FromIter<T> without being able to implement. Meaning the type system will not keep me in check.
//  interface FromIter<T> {
//   public static abstract <A extends Iter<T>> FromIter from_iter(A iter);
// }
