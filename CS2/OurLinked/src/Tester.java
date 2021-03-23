import java.util.*;
public class Tester {
    public static void main(String[] args) {
		/*Stack1<Integer> s=new Stack1<Integer>();
		System.out.println(s);
		System.out.println(s.isEmpty());

		s.push(5);
		s.push(3);

		System.out.println(s);
		System.out.println(s.isEmpty());

		int x=s.pop();
		System.out.println(s);
		System.out.println("x = "+x);*/

		OurLinkedList<String> ohyeah = new OurLinkedList<>();
		ohyeah.add("wowie");
		ohyeah.add("w");
		ohyeah.add("yo");
		ohyeah.add("genius");
		System.out.println(ohyeah);
		System.out.println(ohyeah.reverse());
		String[] s={"Sarah","John","Mary","Zack"};
		Double[][] ph= {{45.5,68.25,78.6,81.2,84.1} , {89.2,91.5,76.3,88.9,93.0} , {95.6,98.2,87.8,91.2,99.9} ,{ 55.67,67.2,20.6,34.34,0.0}};
		/*Queue<Double> q= averages(s,ph);
		for(int i=0;i<4;i++){
			System.out.println(q.remove());
		}

		System.out.println(doNotHave3("zookeeper",'e'));*/

    }
    public static void fill(OurLinkedList<Double> ll,int n){
    	for(int i=0;i<n;i++)
    		ll.add(Math.random());
	}

	private static void recPrint(int n){
    	if(n<0)
			System.out.println();
    	else if (n == 0) {
			System.out.print(n+" ");
    	} else {
    		int y = n - 1;
			recPrint(y);
			int z = n*2;
			System.out.print(z+" ");
    	}
    }

    public static Queue<Double> averages(String[] s, Double[][] d){
    	Queue<Double> q=new Queue<>();
    	double sum=0;
    	for(int i=0;i<d.length;i++){
    		for(int j=0;j<d[i].length;j++) {
				sum += d[i][j];
			}
    		q.add(sum/d[i].length);
    		sum=0;
		}
    	return q;
	}
	//Not quite sure what to do with the String[] because it really isn't specified but, I did everything else

	public static boolean doNotHave3(String s, char c){
    	Stack<Character> stk=new Stack<>();
    	for(int i=0;i<s.length();i++){
    		if(s.charAt(i)==c){
    			stk.push(s.charAt(i));
			}
		}
    	while(!stk.isEmpty()){
    		for(int i=0;i<3;i++){
				if(stk.isEmpty())
					return true;
    			stk.pop();
    		}
		}
    	return false;
	}
}
