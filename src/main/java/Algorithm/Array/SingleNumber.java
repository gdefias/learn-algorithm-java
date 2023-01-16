package Algorithm.Array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2017/9/27.

 https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/

 数组中数字出现的次数

 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 输入：nums = [4,1,4,6]
 输出：[1,6] 或 [6,1]

 输入：nums = [1,2,10,4,1,4,3,3]
 输出：[2,10] 或 [10,2]
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,-10,4,1,4,-3,-3};
        int[] res = singleNumbers(nums);
        System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
    }

    public static int[] singleNumbers(int[] nums) {
        int ret = 0; //所有数相异或的结果（异或：相同为0，不同为1   所有重复的数两两相抵消了）
        for(int i=0; i<nums.length; i++) {
            ret ^= nums[i];
        }

        //找到ret中二进制位为1的最低位（实际ret中任意二进制位为1的位都可以）
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }

        int a = 0;
        int b = 0;

        //每个数与div相与进行分组（不重复的a和b必然分到不同的组，重复的两个数必然分到同一组）   各组进行异或
        for(int i=0; i<nums.length; i++) {
            if((nums[i] & div) !=0 ) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }

        return  new int[] {a, b};
    }
}
