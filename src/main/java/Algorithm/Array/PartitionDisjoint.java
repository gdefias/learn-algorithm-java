package Algorithm.Array;

/**
 * @author: Felix
 * @date: 2023/1/1
 * @description: 分割数组
 *
 * https://leetcode.cn/problems/partition-array-into-disjoint-intervals/description/
 *
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 * left 中的每个元素都小于或等于 right 中的每个元素。
 * left 和 right 都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回 left 的 长度 。
 *
 * 用例可以保证存在这样的划分方法。
 *
 * 输入：nums = [5,0,3,8,6]     minR：[0,0,3,6,6]
 * 输出：3
 * 解释：left = [5,0,3]，right = [8,6]
 *
 *
 * 输入：nums = [1,1,1,0,6,12]
 * 输出：4
 * 解释：left = [1,1,1,0]，right = [6,12]
 *
 *
 * 提示：
 * 2 <= nums.length <= 105
 * 0 <= nums[i] <= 106
 * 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
 */
public class PartitionDisjoint {


    //两次遍历
    public int partitionDisjoint(int[] nums) {
        int len = nums.length;
        int[] minR = new int[len];
        int res = 0;

        minR[len-1] = nums[len-1];  //记录各下标右侧最小值
        for(int i=len-2; i>=0; i--) {
            if(nums[i] <= minR[i+1]) {
                minR[i] = nums[i];
            } else {
                minR[i] = minR[i+1];
            }
        }

        int maxL = 0;  //左侧的最大值
        for(int i=0; i<len-1; i++) {
            maxL = Math.max(nums[i], maxL);
            res++;
            if(maxL <= minR[i+1]) { //左侧的最大值小于等于右侧最小值时 首次符合条件即是最短的结果
                return res;
            }
        }

        return len;
    }
}
