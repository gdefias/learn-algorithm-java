package Interview.code1;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * @author: Defias
 * @date: 2020/12/18
 * @description:
 */
public class question1 {

    public static void main(String[] args) {
        int[] A = new int[] {1,2,4,21,22,31,424,5553};
        int[] B = new int[] {56,24,2,4,1,4};

        //int[] C = test1(A, B);
        test2(A);
        System.out.println(Arrays.stream(A).boxed().collect(Collectors.toList()));
    }

    public static int[] test1(int[] A, int[] B) {
        mergesort(A);
        mergesort(B);
        int[] C = new int[A.length+B.length];

        int index = 0;
        for(int i=0; i<A.length; i++) {
            C[index++] = A[i];
        }
        int mid = index-1;

        for(int i=0; i<B.length; i++) {
            C[index++] = B[i];
        }

        int[] tmpc = new int[C.length];
        merge(C, tmpc, 0, mid, C.length-1);

        return C;
    }

    public static void mergesort(int[] A) {
        if(A==null || A.length==1) {
            return;
        }
        helper(A,0, A.length-1);
    }

    public static void helper(int[] A, int left, int right) {
        if(left>=right) {
            return;
        }

        int[] tmp = new int[A.length];
        int mid = left+(right-left)/2;
        helper(A, left, mid);
        helper(A,mid+1, right);
        merge(A, tmp, left, mid, right);
    }

    public static void merge(int[] A, int[] tmp, int left, int mid,  int right) {
        int i = left;
        int j = mid+1;
        for(int k=left; k<=right; k++) {
            if(i<=mid && (j>right || A[i]<A[j]) ) {
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



    public static void test2(int[] A) {
        if(A==null || A.length==0) {
            return;
        }

        helpers(A, 0, A.length-1);
    }

    public static void helpers(int[] A, int left, int right) {
        if(left>=right) {
            return;
        }

        int provit = partion(A, left, right);
        helpers(A, left, provit-1);
        helpers(A, provit+1, right);
    }

    public static int partion(int[] A, int left, int right) {
        int provitv = A[left];

        while(right>left) {
            while(right>left && A[right]>provitv) {
                right--;
            }
            A[left] = A[right];

            while(left<right && A[left]<=provitv) {
                left++;
            }
            A[right] = A[left];
        }
        A[left] = provitv;
       return left;
    }


    public static int binquery(int[] A, int num) {
        if(A==null || A.length==0) {
            return -1;
        }
        Arrays.sort(A);
        int left = 0;
        int right = A.length-1;

        while(left<=right) {
            int mid = left+(right-left)/2;
            if(num == A[mid]) {
                return mid;
            } else if(num > A[mid]) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }
}
