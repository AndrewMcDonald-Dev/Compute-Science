/**
 * This is my program for figuring out the average runtime of A(n)
 * 
 * Throughout the code is comments on the reason I choose to make certain decisions
 * My analysis of the results is on the next page
 */
class test {
    public static void main(String[] args) {
        for (int n = 5; n <= 100; n += 5) { 
            long count = 0; //This is the variable I use to derive the number of calls the program makes
            for (int h = 1; h <= 10; h++) {
                int m = (int) (Math.random() * n + 1); //This is the my method of assigning a randown integer
                count += r(n, m); //Represents the numerator asked for in part 2
            }
            count /= 10; // Represents the denominator asked for in part 2

            System.out.println(n + ", " + count + ", " + count / Math.pow(2, n)); 
            //Above is the print line asked for in part 3
        }

    }

    public static long RecBin(int n, int m) {
        //This given function calculates the amount of endpoints in the recursive tree
        if (m == 0 || m == n){
            return 1;
		}
        //Decided to make these variables long because after n=30 there is an interger overflow
        long a = RecBin(n - 1, m - 1); 
        long b = RecBin(n - 1, m);
        return a + b;
    }
    
    public static long r(int n, int m) {
        //This fabricated function calculates the amount of calls in the recursive tree
        if ( m == 0 || m == n){
            return 1;
        }
        //Decided to make these variables long because after n=30 there is an interger overflow
        long a = r(n - 1, m - 1);
        long b = r(n - 1, m);
        return a + b + 1; //This is the key different adding +1 to every return here gets the correct number
    }
}
