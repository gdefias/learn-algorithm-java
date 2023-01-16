package Algorithm.DP.SubSequence;

/**
 * @author: Defias
 * @date: 2021/3/12
 * @description: 最长递增子序列

    给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

    https://leetcode-cn.com/problems/longest-increasing-subsequence/

    输入：nums = [10,9,2,5,3,7,101,18]
    输出：4
    解释：最长递增子序列是 [2,3,7,101]，因此长度为 4

    输入：nums = [0,1,0,3,2,3]
    输出：4

    输入：nums = [7,7,7,7,7,7,7]
    输出：1

    提示：
    1 <= nums.length <= 2500
    -104 <= nums[i] <= 104


    进阶：
    你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = new int[] {4,10,4,3,8,9};
        System.out.println(lengthOfLIS2(nums));
    }


    //方法1：DP
    //dp[j] 代表 nums[0…j] 中以 nums[j] 结尾的最长上升子序列
    //时间复杂度：O(n^2)  空间复杂度：O(n)
    public static int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxlen = 1;
        for(int i=1; i<nums.length; i++) {
            dp[i] = 1;  //至少自己单独一个元素可以构成一个子序列 长度为1
            for(int j=0; j<i; j++) {  //依次寻找前面比自己小的元素
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }

        return maxlen;
    }



    //方法2：贪心 + 二分
    //贪心思想：如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小
    public static int lengthOfLIS2(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        //数组d  d[i]: 表示长度为i+1的最长上升子序列的末尾元素的最小值 单调递增
        int[] d = new int[nums.length];
        int len = 0;
        d[len] = nums[0];

        for(int i=1; i<nums.length; i++) {
            //每加进来一个元素，都可能使最长上升子序列长度变长，也可能维持不变但会改变某个最长上升子序列末尾最小元素
            if(nums[i]>d[len]) { //序列长度变长
                len++;
                d[len] = nums[i];
            } else { //改变d中某个位置的元素
                int index = binSearch(d, len, nums[i]); //在d中寻找大于或等于nums[i]的最小元素的位置
                d[index] = nums[i];  //nums[i]元素替换改位置的值
            }
        }
        return len+1;
    }


    //二分查找
    public static int binSearch(int[] d, int end, int target) {
        int i = 0;
        int j = end;

        while(i<=j) {
            int mid = i+(j-i)/2;
            if(d[mid]>=target) {
                if(mid==0 || d[mid-1]<target) {
                    return mid;
                } else {
                    j = mid-1;
                }
            } else {
                if(d[mid+1]>target) {
                    return mid+1;
                } else {
                    i = mid+1;
                }
            }
        }

        return 0;
    }
}
