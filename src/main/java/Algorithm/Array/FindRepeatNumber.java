package Algorithm.Array;

import Lib.Base;

/**
 * Created by Defias on 2020/07.
 * Description: 数组中重复的数字: 找出数组中重复的数字

 https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/

 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次
 请找出数组中任意一个重复的数字

 输入：[2, 3, 1, 0, 2, 5, 3]
 输出：2 或 3

 2 <= n <= 100000
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
//        int[] A = new int[] {2, 3, 1, 0, 2, 5, 3};
        int[] A = new int[] {1, 3, 2, 4, 0, 5, 6};
        System.out.println(findRepeatNumber4(A));
    }

    //方法1：使用集合set
    //方法2：先排序再查找

    //方法3：计数
    public static int findRepeatNumber3(int[] nums) {
        int[] count = new int[nums.length];

        int i;
        for(i=0; (i<nums.length)&&(count[nums[i]]==0); i++) {
            count[nums[i]]++;
        }

        if(count[nums[i]]>0) {
            return nums[i];
        } else {
            return -1;
        }
    }

    //方法4：原地交换  索引和值一对多 {1, 3, 2, 4, 0, 5, 6}
    public static int findRepeatNumber4(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }

            if(nums[nums[i]] == nums[i]) {
                return nums[i];
            }

            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

}
