package Questions.Search;

/**
 * Created by Defias on 2020/07.
 * Description: 在排序数组中查找数字 I

 https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/

 统计一个数字在排序数组中出现的次数

 输入: nums = [5,7,7,8,8,10], target = 8
 输出: 2

 输入: nums = [5,7,7,8,8,10], target = 6
 输出: 0
  

 0 <= 数组长度 <= 50000
 */

public class searchRange {
    public static void main(String[] args) {
        int[] nums = new int[] {5,7,7,8,8,10};
        //int[] nums = new int[]  {3, 3, 3, 3, 4, 5, 7, 7};

        System.out.println(Solution2(nums,8));
        System.out.println(Solution2(nums, 6));
    }


    //方法1：两次二分查找分别找相邻两元素的同一边界(target和target+1，即使target+1不在数组中也没关系)
    public static int Solution(int[] A, int k) {
        if (A==null || A.length==0 || k<A[0] || k>A[A.length-1])
            return 0;

        int left1 = searchLeftSide(A, k);
        if(left1>=A.length || A[left1]!=k) {
            return 0;
        }

        int left2 = searchLeftSide(A, k+1);

        return left2-left1;
    }


    //方法2：两次二分查找分别找左右边界
    public static int Solution2(int[] A, int k) {
        if(A==null || A.length==0 || k<A[0] || k>A[A.length-1])
            return 0;

        int left = searchLeftSide(A, k);
        if(left>=A.length || A[left]!=k) {
            return 0;
        }

        int right = searchRightSide(A, k);

        return right-left+1;
    }


    //找最后一次出现的，且找不到就返回应该被插入的位置，j有可能为-1
    public static int searchRightSide(int[] nums, int target) {
        int i=0, j = nums.length-1;
        while(i<=j) {
            int m = i+(j-i)/2;
            if(nums[m] <= target)
                i = m+1;
            else
                j = m-1;
        }
        return j;  //target的右边界索引，含j的位置，即：最右边的target的索引
        //return i;  //target的右边界索引，不含i的位置，即：最右边的target的右边界相邻位置的索引
    }

    //找第一次出现的，且找不到就返回应该被插入的位置，i有可能为nums.length
    public static int searchLeftSide(int[] nums, int target) {
        int i=0, j = nums.length-1;
        while(i<=j) {
            int m = i+(j-i)/2;
            if(nums[m] >= target)
                j = m-1;
            else
                i = m+1;
        }
        return i;  //target的左边界索引，含i的位置，即：最左边的target的索引
        //return j;  //target的左边界索引，不含j的位置，即：最左边的target的左边界相邻位置的索引
    }
}
