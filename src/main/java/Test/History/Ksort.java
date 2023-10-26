package Test.History;

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

        ksort(A, left, right);
    }

    public static void ksort(int[] A, int left, int right) {
       if(left >= right) {
           return;
       }

       int mid = partiton(A, left, right);
       ksort(A, left, mid);
       ksort(A, mid+1, right);
    }

    public static int partiton(int[] A, int left, int right) {
        int pv = A[left];
        int lt = left;

        for(int i=left+1; i<A.length; i++) {
            if(A[i] < pv) {
                lt++;
                swap(A, lt, i);
            }
        }

        swap(A, left, lt);

        return lt;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
