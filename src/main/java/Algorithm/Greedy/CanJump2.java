package Algorithm.Greedy;

/**
 * @author: Felix
 * @date: 2023/1/3
 * @description: 跳跃游戏 II

   https://leetcode.cn/problems/jump-game-ii/description/

    给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
    每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:

    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

    输入: nums = [2,3,1,1,4]
    输出: 2

    输入: nums = [2,3,0,1,4]
    输出: 2
 */
public class CanJump2 {

    //贪心
    public int jump(int[] nums) {
        if(nums.length <= 1) {
            return 0;
        }

        int ans = 0;
        int target = nums.length-1;
        int p = 0;
        while(true) {
            int maxNext = p + nums[p];
            ans++;
            if(maxNext >= target) {
                break;
            }

            int next = maxNext;
            int maxIndex = maxNext+nums[maxNext];
            for(int i=next-1; i>p; i--) {
                if(i+nums[i] > maxIndex) {
                    next = i;
                    maxIndex = i+nums[i];
                }
            }



            p = next;
        }

        return ans;
    }
}
