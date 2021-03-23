public class AA implements XX, YY{
	private int h, w;
	public AA(){
		h=100;
		w=200;
	}
	public AA(int hh, int ww){
		h = hh;
		w = ww;
	}
	public int get_h(){
		return h;
	}
	// public int get_w(){
	// 	return w;
	// }

	public void set_h(int hh){
		h = hh;
	}
	public void set_w(int ww){
		w = ww;
	}

	public void methodAA(){
		System.out.println("method AA output");
	}

	public void f(){
		System.out.println("f version AA output");
	}

	public void g(){
		System.out.println("g version AA output");
	}

	public void k(){
		System.out.println("k version AA output");
	}
}
