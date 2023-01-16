package Algorithm.Array;
import java.util.*;

/**
 * @author: Defias
 * @date: 2020/12/18
 * @description: 和为K的子数组

 https://leetcode-cn.com/problems/subarray-sum-equals-k/

 给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个数。

 示例 1 :
 输入:nums = [1,1,1], k = 2
 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。

 说明 :
 数组的长度为 [1, 20,000]。
 数组中元素的范围是 [-1000, 1000] ，且整数k的范围是[-1e7, 1e7]。
 */
public class SubArraySumK {
    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1};
        int k = 2;
        int result = subarraySum2(nums, k);
        System.out.println(result);
    }

    //枚举  （相当于一个滑动窗口  左边和右边依次枚举）
    //时间复杂度：O(n^2)  空间复杂度：O(1)
    public static int subarraySum(int[] nums, int k) {

        int start;  //窗口左边
        int end; //窗口右边
        int count = 0; //统计结果数量

        for(start=0; start<nums.length; start++) {
            int sum = 0;
            for(end=start; end<nums.length; end++) {
                sum += nums[end];
                if(sum==k) {
                    count++;
                }
            }
        }
        return count;
    }

    //前缀和 + 哈希表优化
    //时间复杂度：O(n)，其中 n 为数组的长度。我们遍历数组的时间复杂度为 O(n)，中间利用哈希表查询删除的复杂度均为O(1)，因此总时间复杂度为O(n)
    //空间复杂度：O(n)，其中 n 为数组的长度。哈希表在最坏情况下可能有 n 个不同的键值，因此需要 O(n) 的空间复杂度
    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(sum, 1);

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum-k)) {
                count += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
