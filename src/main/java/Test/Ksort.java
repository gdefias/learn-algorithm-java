package Test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10
 *
 * 快排
 */
public class Ksort {
    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,444,5,23,54,73,3,2,12,46};
        ksort(A);

        for(int n: A) {
            System.out.println(n);
        }
    }

    public static void ksort(int[] A) {
        if(A==null || A.length==0) {
            return;
        }
        int left = 0;
        int right = A.length-1;

        helper(A, left, right);
    }

    public static void helper(int[] A, int left, int right) {
        if(left>=right) {
            return;
        }

        int provitw = partiton(A, left, right);

        helper(A, left, provitw-1);
        helper(A, provitw+1, right);
    }

    public static int partiton(int[] A, int left, int right) {
        int provit = A[left];

        while(left<right) {
            while(left<right && A[right]>provit) {
                right--;
            }
            A[left] = A[right];


            while(left<right && A[left]<=provit) {
                left++;
            }
            A[right] = A[left];
        }

        A[left] = provit;
        return left;
    }
}
