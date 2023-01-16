package Algorithm.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2020/06.
 * Description: 最大子数组和 / 连续子数组的最大和

 https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 https://leetcode.cn/problems/maximum-subarray/description/

 给你一个整数数组nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 子数组 是数组中的一个连续部分。

 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6

 输入：nums = [1]
 输出：1

 输入：nums = [5,4,-1,7,8]
 输出：23

 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。

 提示：
 1 <= arr.length <= 10^5
 -100 <= arr[i] <= 100


 分治   贪心   动态规划
 */


public class MaxSubArray {

    public static void main(String[] args) {
//        int[] A = {13, -3, -25, 20, -3, -16, -23, 0, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int[] A = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] A = {-122, -23, -1, -2};
        int result = maxSubArray2_1(A);
        System.out.print(result);
    }


    //方法1：分治
    //时间复杂度：O(nlogn) 递归的深度是对数级别的，每一层需要遍历一遍数组（或者数组的一半、四分之一）
    //空间复杂度：O(logn) 需要常数个变量用于选取最大值，需要使用的空间取决于递归栈的深度
    public static int maxSubArray(int[] A) {
        if(A==null || A.length==0) {
            return 0;
        }

        int low = 0;
        int high = A.length-1;
        return maxSubArray(A, low, high);
    }

    //求连续子数组的最大和
    public static int maxSubArray(int[] A, int low, int high) {
        if(low == high) {  //子数组只含1个元素的基本情况
            return  A[low];
        }

        //最大子数组要么在中点的左边，要么在中点的右边，要么跨越中点两边

        int mid = low+(high-low)/2;
        int leftsum = maxSubArray(A, low, mid);
        int rightsum = maxSubArray(A, mid+1, high);

        //跨越中点两边  nums[mid]与nums[mid + 1]一定会被选取
        int midsum = maxCrossSubArray(A, low, mid, high);

        //哪个和最大即为最终结果
        int result = Math.max(leftsum, rightsum);
        result = Math.max(result, midsum);
        return result;
    }

    //跨越中点的连续子数组的最大和
    public static int maxCrossSubArray(int[] A,int low, int mid, int high) {
        //中点左右两边的最大和
        int leftsum = Integer.MIN_VALUE;
        int rightsum = Integer.MIN_VALUE;
        int sum = 0;  //累加和

        //寻找中点左边的最大和  贪心
        //左半边包含 nums[mid] 元素，最多可以到什么地方
        //走到最边界，看看最值是什么
        //计算以 mid 结尾的最大的子数组的和
        for(int i=mid; i>=low; --i) {
            sum += A[i];
            if(sum >= leftsum) {
                leftsum = sum;
            }
        }

        //寻找中点右边的最大和  贪心
        //右半边包含 nums[mid+1] 元素，最多可以到什么地方
        //走到最边界，看看最值是什么
        //计算以 mid+1 开头的最大的子数组的和
        sum = 0;
        for (int i=mid+1; i<=high; ++i) {
            sum += A[i];
            if(sum >= rightsum) {
                rightsum = sum;
            }
        }

        //返回跨越中点的连续子数组的最大和
        return  leftsum+rightsum;
    }


    //方法2：贪心  从左向右迭代，一个个数累加到sum，如果sum<0，重新开始找子序串
    //时间复杂度：O(n)
    public static int maxSubArray2(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
//            if(sum > max) {
//                max = sum;
//            }

            //如果sum < 0，重新开始找子序串
            if(sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    //贪心  从左向右迭代，一个个数累加到sum，累加前先检查之前的sum是否大于0，大于0才累加，否则从当前元素重新开始
    //时间复杂度：O(n)
    public static int maxSubArray2_1(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }
        int maxsum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            if(sum>0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            maxsum = Math.max(maxsum, sum);
        }
        return maxsum;
    }



    //方法3：DP 动态规划
    //时间复杂度O(N) 空间复杂度O(N)
    //dp[i]：表示nums中以nums[i]结尾的最大子序和
    //dp[i] = max(dp[i-1]+nums[i], nums[i])
    public static int maxSubArray3(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];

        for(int i=1; i<nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    //DP 空间优化
    //时间复杂度O(N) 空间复杂度O(1)
    //思路：由于dp[i]只与dp[i−1]和nums[i]有关系，因此可以将原数组nums用作dp列表，即直接在nums上修改即可
    public static int maxSubArray3_1(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int res = nums[0];
        for(int i=1; i<nums.length; i++) {
            nums[i] = Math.max(nums[i-1]+nums[i], nums[i]);
            //nums[i] = Math.max(nums[i-1], 0) + nums[i]; //连续子数组的最大和最小也是0
            res = Math.max(res, nums[i]);
        }

        return res;
    }

}
