package Algorithm.Array;

/**
 * Created by Defias on 2020/07.
 * Description: 数组中数字出现的次数 II

 https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/

 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字

 输入：nums = [3,4,3,3]
 输出：4

 1 <= nums.length <= 10000
 1 <= nums[i] < 2^31
 */
public class SingleNumber2 {

    public int singleNumber(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        //记录累计各个位上1的个数
        int[] bitns = new int[33];
        int cur;
        for(int i=0; i<nums.length; i++) {
            cur = nums[i];
            for(int j=32; j>0; j--) {
                if((cur&1)==1) {
                    bitns[j]++;
                }
                cur >>>= 1;
            }
        }

        for(int i=1; i<=32; i++) {
            bitns[i] %= 3;
        }

        int res = 0;
        int n = 1;
        for(int i=32; i>0; i--) {
            if(bitns[i]==1) {
                res = res | n;
            }
            n <<= 1;
        }

        return res;
    }
}
