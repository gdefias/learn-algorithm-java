package Questions.Greedy;

/**
 * @author: Defias
 * @date: 2020/12/24
 * @description: 跳跃游戏
 *
 * https://leetcode-cn.com/problems/jump-game/
 */

public class CanJump {
    public static void main(String[] args) {
        int[] A = new int[] {0};
        System.out.println(canJump(A));
    }

    public static boolean canJump(int[] nums) {

        int maxsize = 0;
        int target = nums.length-1;
        for(int i=0; i<nums.length; i++) {
            if(i>maxsize) {
                return false;
            }

            maxsize = Math.max(maxsize, i+nums[i]);
            if(maxsize>=target) {
                return true;
            }
        }

        return false;
    }
}
