package Questions.Search;

/**
 * Created by Defias on 2017/10/7.
 *
 *
 * 二分查找插入位置
 * 时间复杂度：O(lgn)
 *
 * 给定一个排序数组（升序且无重复的数）和一个目标值，
 * 如果在数组中找到目标值则返回索引。如果没有，返回到它将会被按顺序插入的位置
 */

public class BinarySearchInsert {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 5, 20, 22, 23, 234};
        //System.out.println(binarySearchInsert(nums, 2));
        System.out.println(binarySearchInsert(nums, 235));
    }

    //方法1：与普通二分查找唯一的区别就是返回值
    public static int binarySearchInsert(int[] A, int target) {
        if(A==null || A.length==0) {
            return 0;
        }

        int start = 0;
        int end = A.length-1;

        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(A[mid] == target) {
                return mid;
            } else if(A[mid] > target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        //循环退出时end在左边，start在右边，end可能为-1，start可能为A.length
        return start;
    }

    //方法2：对方法1进行了分支合并优化
    public static int binarySearchInsert1(int[] A, int target) {
        if(A==null || A.length==0) {
            return 0;
        }

        int start = 0;
        int end = A.length-1;

        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(A[mid] >= target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
}
