package Algorithm.Search;

import static Lib.Base.printArray;

/**
 * @author: Felix
 * @date: 2022/12/25
 * @description:
 */
public class SearchKth2 {
    public static void main(String[] args) {
//        int[] A = {1, 23, 34, 9, 32, 59, 1, 2, 3, 64, 5, 6, 6, 10, 0, 0, 26, 26, 85, 9, 31, 1, 64};
        int[] A = {3,5,3,1,2,5,5,5,6};
        printArray(A);
        SearchKth2 sk = new SearchKth2();
        System.out.println(sk.findKthLargest(A, 4));
        printArray(A);
    }

    public int findKthLargest(int[] nums, int k) {

        int[] kminheap = new int[k];
        for(int i=0; i<k; i++) {
            kminheap[i] = nums[i];
        }

        buildMinHeap(kminheap);

        for(int i=k; i<nums.length; i++) {
            if(nums[i] > kminheap[0]) {
                kminheap[0] = nums[i];
                rebuildMinHeap(kminheap, 0);
            }
        }

        return kminheap[0];
    }

    public void buildMinHeap(int[] kminheap) {
        for(int i=kminheap.length/2-1; i>=0; i--) {
            rebuildMinHeap(kminheap, i);
        }
    }

    public void  rebuildMinHeap(int[] kminheap, int parent) {
        int last = kminheap.length-1;
        int leftc = parent*2+1;
        int rightc = parent*2+2;

        int smallest = parent;
        if(leftc<=last && kminheap[leftc] < kminheap[smallest]) {
            smallest = leftc;
        }
        if(rightc<=last && kminheap[rightc] < kminheap[smallest]) {
            smallest = rightc;
        }

        if(smallest != parent) {
            swap(kminheap, smallest, parent);
            rebuildMinHeap(kminheap, smallest);
        }
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
