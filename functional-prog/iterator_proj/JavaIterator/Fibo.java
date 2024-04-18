 package JavaIterator;


import java.math.BigInteger;
import java.util.Optional;
public class Fibo extends Iter<BigInteger> {
	BigInteger cur = BigInteger.ZERO;
	BigInteger next = BigInteger.ONE;

	public Optional<BigInteger> next() {
		BigInteger current = this.cur;

		this.cur = this.next;
		this.next = current.add(this.next);

		return Optional.of(current);
	}
}

