package Algorithm.Search;

/**
 * Created by Defias on 2017/10/7.
 *
 * 二分查找：查找最后一次出现
 * 时间复杂度：O(lgn)
 *
 * 给定一个排序的整数数组（升序且可能包含重复的数）和一个要查找的整数target，
 * 用O(lgn)的时间查找到target最后一次出现的下标（从0开始），如果target不存在于数组中，返回-1
 *
 */


public class BinarySearchLast {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 22, 22, 22, 22, 234};
        System.out.println(binarySearch3(nums, 22));
    }

    //方法1： while条件严格比较
    public static int binarySearch(int[] A, int target) {
        if(A==null || A.length ==0 || target < A[0] || target > A[A.length-1]) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        while(start < end) {
            int mid = end-(end-start) / 2;  //取偏右的中间值
            if(A[mid] < target) {
                start = mid+1;
            } else if(A[mid] > target) {
                end = mid-1;
            } else {
                start = mid;
            }
        }

        if(A[start] == target) {
            return start;
        }

        return -1;
    }

    //方法2：while条件非严格比较
    public static int binarySearch2(int[] A, int target) {
        if(A==null || A.length ==0 || target < A[0] || target > A[A.length-1]) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(A[mid] > target) {
                end = mid-1;
            } else if(A[mid] < target) {
                start = mid+1;
            } else {
                if(mid==A.length-1 || A[mid+1]!=target) {  //相等直接判断右边是否还有相同的值，没有说明当前就是最后一个
                    return mid;
                } else {
                    start = mid+1;  //否则说明右边还有，所以往右继续二分找
                }
            }
        }
        return -1;
    }

    //方法3： 对方法2进行了分支合并优化
    public static int binarySearch3(int[] A, int target) {
        if(A==null || A.length ==0 || target < A[0] || target > A[A.length-1]) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(A[mid] > target) {
                end = mid-1;
            } else {
                if(mid==A.length-1 || A[mid+1]>target) {
                    return mid;
                } else {
                    start = mid+1;
                }
            }
        }
        return -1;
    }

    //方法4
    public static int binarySearch4(int[] A, int target) {
        if(A==null || A.length ==0 || target < A[0] || target > A[A.length-1]) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(A[mid] <= target) {
                start = mid+1;   //相等继续往右二分找（因为要找第最后一次出现的），即使右边再也找不到了，最终end也会刚好指向本次mid
            } else {
                end = mid-1;
            }
        }

        //有可能没有找到
        if(end<0 || A[end]!=target) {
            return -1;
        }

        return end;
    }
}
