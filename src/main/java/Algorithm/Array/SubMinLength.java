package Algorithm.Array;

/**
 * Created by Defias on 2017/10/19.

 排序的最短子数组长度

 arr = [1, 5, 3, 4, 2, 6, 7]
 return 4  [5, 3, 4, 2]


 */


public class SubMinLength {
    public static void main(String[] args) {
        int[] A = {1, 5, 3, 4, 2, 6, 7, 9, 8, 10};
        System.out.println(getMinLength(A));
    }

    public static int getMinLength(int[] A) {
        if(A==null||A.length==0) {
            return 0;
        }
        int min = A[A.length-1];
        int left = -1;
        for(int i=A.length-2; i>=0; i--) {
            if(A[i]>min) {
                left = i;
            } else {
                min = A[i];
            }
        }

        if(left==-1) {
            return 0;
        }

        int max = A[0];
        int right = -1;
        for(int i=1; i<A.length; i++) {
            if(A[i]<max) {
                right = i;
            } else {
                max = A[i];
            }
        }
        return right-left+1;
    }
}
