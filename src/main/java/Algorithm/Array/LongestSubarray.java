package Algorithm.Array;

import java.util.*;

/**
 * @author: Defias
 * @date: 2021/3/15
 * @description: 绝对差不超过限制的最长连续子数组

 https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

 给你一个整数数组nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于limit 。
 如果不存在满足条件的子数组，则返回 0 。

    输入：nums = [8,2,4,7], limit = 4
    输出：2
    解释：所有子数组如下：
    [8] 最大绝对差 |8-8| = 0 <= 4.
    [8,2] 最大绝对差 |8-2| = 6 > 4.
    [8,2,4] 最大绝对差 |8-2| = 6 > 4.
    [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
    [2] 最大绝对差 |2-2| = 0 <= 4.
    [2,4] 最大绝对差 |2-4| = 2 <= 4.
    [2,4,7] 最大绝对差 |2-7| = 5 > 4.
    [4] 最大绝对差 |4-4| = 0 <= 4.
    [4,7] 最大绝对差 |4-7| = 3 <= 4.
    [7] 最大绝对差 |7-7| = 0 <= 4.
    因此，满足题意的最长子数组的长度为 2 。

    输入：nums = [10,1,2,4,7,2], limit = 5
    输出：4
    解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。

    输入：nums = [4,2,2,2,4,4,2,2], limit = 0
    输出：3
 */
public class LongestSubarray {

    public static void main(String[] args) {
        int[] A = new int[] {10,1,2,4,7,2};
        System.out.println(longestSubarray3(A, 5));
    }

    //滑动窗口 + 有序集合
    //枚举每一个位置作为右端点，找到其对应的最靠左的左端点，满足区间中最大值与最小值的差不超过limit
    //最大值和最小值通过有序集合TreeMap来维护
    //时间复杂度：O(nlogn)  空间复杂度：O(n)
    public static int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); //key: 值（升序）  value: 值的个数
        int len = nums.length;
        int left = 0;
        int right = 0;
        int ans = 0;

        while (right < len) {
            map.put(nums[right], map.getOrDefault(nums[right], 0)+1);

            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    //方法2：滑动窗口 + 单调队列
    //与方法1类似，最大值和最小值通过两个单调队列来维护
    //时间复杂度：O(n) 空间复杂度：O(n)
    public static int longestSubarray2(int[] nums, int limit) {
        Deque<Integer> minD = new LinkedList<Integer>();  //(非严格)单调递增  first ... last  first最小
        Deque<Integer> maxD = new LinkedList<Integer>();  //(非严格)单调递减  first ... last  first最大

        int left = 0;
        int right = 0;
        int ans = 0;
        while(right < nums.length) {
            int cur = nums[right];
            while(!maxD.isEmpty() && cur > maxD.peekLast()) {
                maxD.pollLast(); //因为nums[right]即将从last入队，需要确保所有前面的元素都是比nums[right]大的
            }
            while(!minD.isEmpty() && cur < minD.peekLast()) {
                minD.pollLast();
            }
            maxD.offerLast(cur);
            minD.offerLast(cur);

            //滑动窗口
            while(!maxD.isEmpty() && !minD.isEmpty() && maxD.peekFirst()-minD.peekFirst() > limit) {
                //移除窗口最左侧的元素
                if(nums[left] == minD.peekFirst()) {
                    minD.pollFirst();
                }

                if(nums[left] == maxD.peekFirst()) {
                    maxD.pollFirst();
                }
                left++;
            }

            ans = Math.max(ans, right-left+1);  //每获得一个满足条件的窗口比较一次
            right++;
        }
        return ans;
    }


    //方法3：滑动窗口 + 优先队列/堆
    //时间复杂度：O(nlogn)  空间复杂度：O(n)
    public static int longestSubarray3(int[] nums, int limit) {
//        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());
//        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((v1, v2) -> {return v2-v1;});

        int left = 0;
        int right = 0;
        int ans = 0;
        while(right < nums.length) {
            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);

            if (maxQueue.peek() - minQueue.peek() <= limit) {
                ans = Math.max(ans, right - left + 1);
                right++;
                continue;
            }

            maxQueue.remove((Integer) nums[left]);
            minQueue.remove((Integer) nums[left]);
            left++;
            right++;
            //left和right同时都增加时会漏掉一个窗口的判断，但因为是求最长的窗口长度，漏掉的窗口对结果并没影响
        }
        return ans;
    }

}
