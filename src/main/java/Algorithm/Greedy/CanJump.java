package Algorithm.Greedy;

/**
 * @author: Defias
 * @date: 2020/12/24
 * @description: 跳跃游戏

   https://leetcode-cn.com/problems/jump-game/

    给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
    数组中的每个元素代表你在该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个下标。

    输入：nums = [2,3,1,1,4]
    输出：true
    解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

    输入：nums = [3,2,1,0,4]
    输出：false
    解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */

public class CanJump {
    public static void main(String[] args) {
        int[] A = new int[] {1,2};
        System.out.println(canJump(A));
    }

    //贪心
    //时间复杂度：O(n)，其中n为数组的大小。只需要访问nums数组一遍，共n个位置
    //空间复杂度：O(1)，不需要额外的空间开销
    public static boolean canJump(int[] nums) {
        int maxsize = 0;  //依次遍历数组中的每一个位置，并实时维护最远可以到达的位置
        int target = nums.length-1;  //目标终点
        for(int i=0; i<nums.length; i++) {
            if(i>maxsize) {  //当前位置i如果不在之前已经可以到达的最远位置范围内 则不可能到达终点
                return false;
            }

            maxsize = Math.max(maxsize, i+nums[i]);
            if(maxsize >= target) {  //每次更新最远可以到达的位置时，马上就检查是否可以到达终点
                return true;
            }
        }

        return false;
    }
}
