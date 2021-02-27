package Algorithm.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Defias
 * @date: 2020/12/1
 * @description: 三数之和
 *
 * https://leetcode-cn.com/problems/3sum/
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组
 * 注意：答案中不可以包含重复的三元组
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */

public class ThreeSum {

    public static void main(String[] args) {
        int[] A = new int[] {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(A));
    }

    //排序 + 双指针二分法
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null || nums.length<3) {
            return res;
        }
        Arrays.sort(nums);     //排序

        for(int k=0; k<=nums.length-3; k++) {
            int cur = nums[k];
            if(cur>0) {     //剪枝
                return res;
            }
            if(k>0 && (nums[k]==nums[k-1])) {    //去重
                continue;
            }

            //二分法
            int left = k+1;
            int right = nums.length-1;
            int target = 0 - cur;
            while(left<right) {
                if(nums[left] + nums[right] == target) {
                    res.add(new ArrayList<>(Arrays.asList(cur, nums[left], nums[right])));

                    //去重
                    while(left<right && nums[left]==nums[left+1]) {
                        left++;
                    }

                    while(left<right && nums[right]==nums[right-1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if(nums[left] + nums[right] > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
