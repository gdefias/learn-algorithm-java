package Algorithm.Array;

/**
 * @author: Felix
 * @date: 2022/12/29
 * @description: 删除有序数组中的重复项 II

  https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/

    给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。

    不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

    示例 1：
    输入：nums = [1,1,1,2,2,3]
    输出：5, nums = [1,1,2,2,3]
    解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
         不需要考虑数组中超出新长度后面的元素。

    示例 2：
    输入：nums = [0,0,0,1,1,1,1,2,3,3]
    输出：7, nums = [0,0,1,1,2,3,3]
    解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
         不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicate2 {
    public static void main(String[] arg) {
        int[] nums = new int[] {0,0,0,1,1,1,1,2,3,3};
        int res = removeDuplicates(nums);
        System.out.println(res);
        for(int n: nums) {
            System.out.print(n+" ");
        }
    }

    // 双指针
    public static int removeDuplicates(int[] nums) {
        if(nums==null || nums.length<1) {
            return 0;
        }

        int ans = 0;
        int left = -1;
        int right = 0;
        while(right <= nums.length-1) {
            int rn = 1;
            while(right < nums.length-1 && (nums[right] == nums[right+1])) {
                right++;
                rn++;
            }

            if(rn>=2) {
                left++;
                ans++;
                nums[left] = nums[right];

                left++;
                ans++;
                nums[left] = nums[right];
            } else {
                left++;
                nums[left] = nums[right];
                ans++;
            }
            right++;
        }

        return ans;
    }
}
