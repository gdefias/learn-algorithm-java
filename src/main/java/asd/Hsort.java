package asd;

/**
 * @author: Defias
 * @date: 2020/11/28
 * @description:
 */
public class Hsort {
    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,444,5,23,54,73,3,2,12,46};
        hsort(A, A.length-1);

        for(int n: A) {
            System.out.println(n);
        }

        short s1 = 1;
        s1 = (short) (s1 + 1);

    }

    public static void hsort(int[] A, int last) {
        if(A==null || A.length<=1) {
            return;
        }
        buildmaxheap(A);
        for(int i=last; i>=1; i--) {
            swap(A, 0, i);
            last--;
            rebuildmaxheap(A, 0, last);
        }

    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void buildmaxheap(int[] A) {
        int provit = (A.length-1)/2;

        for(int i=provit; i>=0; i--) {
            rebuildmaxheap(A, i, A.length-1);
        }
    }

    public static void rebuildmaxheap(int[] A, int provit, int last) {
        int largest = provit;
        int leftchild = provit*2+1;
        int rightchild = leftchild+1;

        if(leftchild<last && A[leftchild]>A[largest]) {
            largest = leftchild;
        }

        if(rightchild<last && A[rightchild]>A[largest]) {
            largest = rightchild;
        }

        if(largest!=provit) {
            swap(A, provit, largest);
            rebuildmaxheap(A, largest, last);
        }
    }
}
