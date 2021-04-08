/**
 * 
 */
class test {

    public static int c = 0;

    public static void main(String[] args) {
        int count = 0;
        for (int n = 5; n <= 100; n += 5) {
            count = 0;
            for (int h = 1; h <= 10; h++) {
                int m = (int) (Math.random() * n + 1);
                count += r(n, m);
            }
            count /= 10;

            System.out.println(n + ", " + count + ", " + count / Math.pow(2, n) + ", "  + count/n);
        }

    }

    public static int RecBin(int n, int m) {
		c++;
        if (m == 0 || m == n){
            return 1;
		}
        int a = RecBin(n - 1, m - 1);
        int b = RecBin(n - 1, m);
        return a + b;
    }

    public static int r(int n, int m) {
        c = 0;
        RecBin(n, m);
        return c;
    }

}

