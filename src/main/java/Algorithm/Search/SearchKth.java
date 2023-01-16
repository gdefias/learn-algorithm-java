package Algorithm.Search;
import Algorithm.Sort.Compare.HeapSort;

import static Lib.Base.printArray;

import Algorithm.Sort.Compare.HeapSort2;
import Algorithm.Sort.Compare.QuickSort3;
/**
 * Created by Defias on 2020/06.
 * Description: 数组中的第K个最大元素

 https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

 寻找N元素数组中第K大(或第K小)的元素

 数组第K大的元素 即数组降序排序后的第K个（最大的）元素，而不是第K个不同的元素
                也即数组升序排序后的第N-K个（最小的）元素

 */

public class SearchKth {
    public static void main(String[] args) {
//        int[] A = {1, 23, 34, 9, 32, 59, 1, 2, 3, 64, 5, 6, 6, 10, 0, 0, 26, 26, 85, 9, 31, 1, 64};
        int[] A = {3,5,3,1,2,5,5,5,6};
        printArray(A);
        System.out.println(kthLargestElement3(A, 4));
        printArray(A);
    }

  // 方法1：快速排序思想    平均情况时间复杂度O(N)，最坏情况O(N^2)  空间复杂度O(1)
  public static int kthLargestElement(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k<1 || k>nums.length) {
            return 0;
        }

        return quickselect(nums, 0, nums.length-1, nums.length-k);
    }

    public static int quickselect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int privot = QuickSort3.partition(nums, left, right);
        if (privot == k) {
            return nums[privot];
        } else if (privot < k) {
            return quickselect(nums, privot+1, right, k);
        }  else {
            return quickselect(nums, left, privot-1, k);
        }
    }

    //方法2：建立含K个元素的小顶堆并不断调整  时间复杂度O(NlogK)   空间复杂度O(k)
    public static int kthLargestElement2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k<1 || k>nums.length) {
            return 0;
        }

        int[] kminheap = new int[k];
        for(int i=0; i<k; i++) {  //前k个元素
            kminheap[i] = nums[i];
        }

//        HeapSort2.buildMinHeap(kminheap);
        HeapSort2.buildMinHeap2(kminheap);

        for(int i=k; i<nums.length; ++i) {  //剩余元素
            if(nums[i] > kminheap[0]) {  //比堆顶元素大说明堆顶的元素不可能是第K大
                kminheap[0] = nums[i];    //替代堆顶元素
                HeapSort2.rebuildMinHeap(kminheap, 0);     //重新调整
            }
        }

        return kminheap[0];  //小顶堆堆顶元素即是第k大的元素
    }

    //方法3：建立所有元素的大顶堆，并不断调整取K次堆顶元素   时间复杂度O((N+K)logN)
    public static int kthLargestElement3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k<1 || k>nums.length) {
            return 0;
        }

        int[] kmaxheap = new int[nums.length];
        for(int i=0; i<nums.length; i++) {  //所有元素
            kmaxheap[i] = nums[i];
        }

//        HeapSort.buildMaxHeap(kmaxheap);
        HeapSort.buildMaxHeap2(kmaxheap);
        //建立大顶堆后，堆顶元素是所有元素中最大的元素

        int last = kmaxheap.length-1;
        for(int i=1; i<=k-1; i++) {  //经过k-1次调整后，堆顶元素是所有元素中第k大的元素
            kmaxheap[0] = kmaxheap[last];
            last--;
            HeapSort.rebuildMaxHeap(kmaxheap, 0, last);
        }

        return kmaxheap[0];
    }
}


