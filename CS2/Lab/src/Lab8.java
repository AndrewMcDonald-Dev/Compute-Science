public class Lab8 {
    public static void main(String[] args) {
        ex1(4);
        //first parameter must me 1 to work best
        ex2(1,4);
        System.out.println();
        ex3(25);
        System.out.println();
        //has to start with 1
        triangle(1,5);

        int[] arr={1,2,3,4,5,17,27,110};
        System.out.println("The index of the integer is: " + binarySearch(arr, 4));
    }
    public static int ex1(int n){
        if(n==0){
            return n;
        }
        if(n>0){
            for(int i=0;i<n;i++) {
                System.out.print("* ");
            }
            System.out.println();
            return ex1(n-1);
        }
        return n;
    }
    public static int ex2(int first,int last){
        if(first<0&&first<last)
            return ex2(first+1,last);
        if(first>last)
            return first;
        if(first==last) {
            for(int j=0;j<last;j++) {
                System.out.print("* ");
            }
            return first;
        }else {
            for (int i = 0; i < first; i++) {
                System.out.print("* ");
            }
            System.out.println();
            return ex2(first + 1, last);
        }
    }
    public static int ex3(int n){
        if(n==0||n==1){
            System.out.print(n);
            return n;
        }
        if(n>1){
            for(int i=n;i>0;i--){
                System.out.print(i+" ");
            }
            System.out.println();
            return ex3(n/2);
        }
        return n;
    }
    public static void triangle(int k,int j) {
        printStars(k);
        if(k<j){
            triangle(k+1,j);
            printStars(k);
        }
    }
    public static void printStars(int m){
        if(m == 1)
            System.out.println("*");
        else{
            System.out.print("*");
            printStars(m-1);
        }
    }
    public static int binarySearch(int[] arr,int x){
        int y=0;
        int index=arr.length -1;
        while(y<=index){
            int z=y+ (index-1)/2;
            if(arr[z]==x)
                return z;
            if(arr[z]<x)
                y=z+1;
            else
                index=z-1;
        }
        return -1;
    }
}
