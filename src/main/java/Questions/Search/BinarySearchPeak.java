package Questions.Search;

/**
 * Created by Jeff on 2016/5/2.
 *
 * 寻找峰值
 *
 * 给出一个整数数组(size为n)，其具有以下特点：
 * 相邻位置的数字是不同的
 * A[0] < A[1] 并且 A[n - 2] > A[n - 1]
 * 假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置
 *
 * 给出数组[1, 2, 1, 3, 4, 5, 7, 6]返回1, 即数值2所在位置, 或者6, 即数值7所在位置
 *
 */

public class BinarySearchPeak {

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,5,6,22,234};

        //System.out.println(searchInsert(nums, 4));

        //int[] xznums = new int[] {4, 5, 6, 7, 0, 1, 2};
        //System.out.println(findMin(xznums));
        //System.out.println(searchRotation(xznums, 1));
        //
        //int[] array = new int[] {1, 2, 1, 3, 4, 5, 7, 6};
        //System.out.println(findPeak(array));


    }

    public static int findPeak(int[] A) {
        if(A == null || A.length == 0) {
            return -1;
        }
        int start = 1;
        int end = A.length-2;

        while(start < end - 1) {
            int mid = start + (end - start) / 2;
            if(A[mid] > A[mid-1] && A[mid] > A[mid+1]) {
                return mid;
            } else if(A[mid] < A[mid-1] && A[mid] > A[mid+1]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if(A[start] > A[start-1] && A[start] > A[start+1]) {
            return start;
        }

        if(A[end] > A[end-1] && A[end] > A[end+1]) {
            return end;
        }

        return -1;
    }

}
