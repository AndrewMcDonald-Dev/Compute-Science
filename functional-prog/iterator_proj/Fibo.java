
import java.math.BigInteger;
class Fibo extends Iter<BigInteger> {
	BigInteger cur = BigInteger.ZERO;
	BigInteger next = BigInteger.ONE;

	public BigInteger next() {
		BigInteger current = this.cur;

		this.cur = this.next;
		this.next = current.add(this.next);

		return current;
	}
}

