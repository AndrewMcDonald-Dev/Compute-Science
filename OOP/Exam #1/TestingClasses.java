public class TestingClasses {
	public static void main(String[] args) {
		
		XX x = new AA();
		AA y = new AA(300, 400);
		BB z = new BB();
		AA r = new BB();
		AA s = (AA) new BB();
		BB w = new BB(300, 400);

		x.f();
		y.k();

		z.f();
		z.methodAA();
		r.methodAA();
		s.methodAA();

		System.out.println(y.get_h());
		System.out.println(z.get_h());
		System.out.println(w.get_h());
	}
}
