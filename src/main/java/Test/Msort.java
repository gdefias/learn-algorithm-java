package Test;

public class Msort {
    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,444,5,23,54,73,3,2,12,46};
        msort(A);

        for(int a: A) {
            System.out.println(a);
        }
    }

    public static void msort(int[] A) {
        if(A==null || A.length<=0) {
            return;
        }

        int left = 0;
        int right = A.length-1;
        int[] tmp = new int[A.length];

        helper(A, tmp, left, right);
    }

    public static void helper(int[] A, int[] tmp, int left, int right) {
        if(left>=right) {
            return;
        }

        int mid = left + (right-left)/2;
        helper(A, tmp, left, mid);
        helper(A, tmp, mid+1, right);

        merge(A, tmp, left, mid, right);
    }

    public static void merge(int[] A, int[] tmp, int left, int mid, int right) {
        int i = left;
        int j = mid+1;

        for(int k=left; k<=right; k++) {
            if(i<=mid && (j>right || A[i]<A[j])) {
                tmp[k] = A[i];
                i++;
            } else {
                tmp[k] = A[j];
                j++;
            }
        }

        for(int k=left; k<=right; k++) {
            A[k] = tmp[k];
        }
    }
}
