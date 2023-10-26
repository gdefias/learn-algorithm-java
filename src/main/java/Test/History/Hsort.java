package Test.History;

import static Lib.Base.swap;

/**
 * @author: Defias
 * @date: 2020/11/28
 * @description:
 */
public class Hsort {
    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,444,5,23,54,73,3,2,12,46};
        hsort(A);
        for(int n: A) {
            System.out.println(n);
        }
    }

    public static void hsort(int[] A) {
        buildmaxheap(A);

        for(int i=A.length-1; i>=1; --i) {
            swap(A, 0, i);
            rebuildmaxheap(A, 0, i-1);
        }
    }

    public static void buildmaxheap(int[] A) {
        for(int i=1; i<A.length; i++) {
            int child = i;
            int parent = getParent(child);

            while(A[child] > A[parent]) {
                swap(A, child, parent);
                child = parent;
                if(child <= 0) {
                    break;
                }
                parent = getParent(child);
            }
        }
    }

    public static void rebuildmaxheap(int[] A, int parent, int last) {
        if(parent == last) {
            return;
        }

        int leftchild = parent*2+1;
        int rightchild = leftchild+1;

        int largest = parent;
        if(leftchild<=last && A[leftchild]>A[largest]) {
            largest = leftchild;
        }

        if(rightchild<=last && A[rightchild]>A[largest]) {
            largest = rightchild;
        }

        if(largest!=parent) {
            swap(A, parent, largest);
            rebuildmaxheap(A, largest, last);
        }
    }


    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static int getParent(int child) {
        int parent = (child-1)/2;
        return parent;
    }
}
