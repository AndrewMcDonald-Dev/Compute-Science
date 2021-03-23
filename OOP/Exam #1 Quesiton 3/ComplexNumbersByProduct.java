import java.util.*;
public class ComplexNumbersByProduct implements Comparator<ComplexNumbers>{
	public int compare(ComplexNumbers z, ComplexNumbers w){
		return (int)(Math.signum((z.getIm() * z.getRe()) - (w.getIm() * w.getRe())));
	}
}
