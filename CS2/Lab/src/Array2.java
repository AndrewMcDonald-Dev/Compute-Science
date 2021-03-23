public class Array2 {
    public static void main(String[] args) {
        int[][] r ={ { 0, -1, 2, -3, 4 }, { 5, 0, 7, 8, -9 }, { 10, -11, 12, 0, 14 } };
        int[] result=count(r);
        System.out.println("Amount of positives: " + result[0]);
        System.out.println("Amount of zeros: " + result[1]);
        System.out.println("Product of the negatives: " + result[2]);
    }
    public static int[] count(int[][] a){
        int posCount=0;
        int zeroCount=0;
        int negProduct=1;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(a[i][j]==0){
                    zeroCount++;
                }else if(a[i][j]>0){
                    posCount++;
                }else{
                    negProduct*=a[i][j];
                }
            }
        }
        return new int[]{posCount,zeroCount,negProduct};
    }
}
