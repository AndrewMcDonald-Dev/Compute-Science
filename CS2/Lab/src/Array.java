public class Array {
    public static void main(String[] args) {
        int[][] r ={ { 0, 1, 2, 3, 4 }, { 5, 6, 7, 8, 9 }, { 10, 11, 12, 13, 14 } };
        scalarMulti(r,2);
        for(int i=0;i<r.length;i++) {
            for (int j = 0; j < r[0].length; j++) {
                System.out.print(r[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int[][] scalarMulti(int[][] a,int n){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                a[i][j]*=n;
            }
        }
        return a;
    }

}
