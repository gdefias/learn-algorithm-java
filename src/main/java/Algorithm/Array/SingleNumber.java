package Questions.Array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2017/9/27.
 *
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/

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
        int xors = xorn(nums);
        int low1 = xors & (-xors);

        //分组异或
        int xor1=0;
        int xor2=0;
        for(int i=0; i<nums.length; i++) {
            if((nums[i]&low1)!=0) {
                xor1 ^= nums[i];
            } else {
                xor2 ^= nums[i];
            }
        }

        return  new int[] {xor1, xor2};
    }

    public static int xorn(int[] nums) {
        int res = nums[0];
        for(int i=1; i<nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

}
