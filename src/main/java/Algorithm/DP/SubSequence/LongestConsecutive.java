package Algorithm.DP.SubSequence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Created by Defias on 2017/10/7.

 最长连续序列
 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度

 https://leetcode-cn.com/problems/longest-consecutive-sequence/

 题解
 https://leetcode.cn/problems/longest-consecutive-sequence/solutions/276931/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
 https://leetcode.cn/problems/longest-consecutive-sequence/solutions/453780/javaliang-chong-fang-fa-luo-ji-qing-xi-yi-kan-jiu-/?topicTags=dynamic-programming

 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

 输入：nums = [100,4,200,1,3,2]
 输出：4
 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 输出：9

 0 <= nums.length <= 10^4
 -10^9 <= nums[i] <= 10^9

 */

public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = new int[] {100,4,200,1,3,2};
        System.out.println(longestConsecutive2(nums));
    }

    //方法1：哈希表 + 左右两边扩展
    //时间复杂度：O(n) 空间复杂度：O(n)
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            set.add(nums[i]);
        }

        int longest = 0;
        for (int i=0; i<nums.length; i++) {
            int cur = nums[i];
            set.remove(cur);

            int down = cur - 1;
            while(set.contains(down)) {
                set.remove(down);
                down--;
            }
            int up = cur + 1;
            while(set.contains(up)) {
                set.remove(up);
                up++;
            }
            longest = Math.max(longest, up-down-1);
        }
        return longest;
    }


    //方法2：哈希表 + 暴力枚举 + 判断是否跳过优化
    //思路：枚举数组中的每个数x，考虑以其为起点，不断尝试匹配 x+1,x+2,⋯ 是否存在，假设最长匹配到了x+y 那么以 x为起点的最长连续序列即为
    //x,x+1,x+2,⋯,x+y，其长度为 y+1 ，不断枚举并更新答案即可
    //要枚举的数 x 一定是在数组中不存在前驱数 x−1 的，不然按照上面的分析我们会从 x−1 开始尝试匹配，因此我们每次在哈希表中检查是否
    //存在 x−1 即能判断是否需要跳过了
    //时间复杂度：O(n) 空间复杂度：O(n)
    public static int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }


    //方法3：哈希表 + 动态规划DP
    //思路：拿当前数字去找与其左右相连的数字集合看看能否组成一个更大的集合，并更新两端的最长值，过程中遇到哈希表中已存在的值就跳过，
    //并且维护一个最大值用于返回
    //时间复杂度：O(n) 空间复杂度：O(n)
    public static int longestConsecutive3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(nums[i]))
                continue;
            int left = map.getOrDefault(nums[i] - 1, 0);
            int right = map.getOrDefault(nums[i] + 1, 0);
            int len = left + right + 1;  //当元素的left和right都为0时，就是本身长度1
            max = Math.max(max, len);
            map.put(nums[i], len);
            map.put(nums[i] - left, len);
            map.put(nums[i] + right, len);
        }
        return max;
    }


    //方法4：并查集思想
    //思路：将相邻的数字合并起来，然后再遍历一遍记录最大值即可
    public static int longestConsecutive4(int[] nums) {
        if (nums.length == 0)
            return 0;

        UnionFind u = new UnionFind(nums);
        for (int num : nums) {
            u.union(num, num + 1);
        }

        int max = 1;
        for (int num : nums) {
            max = Math.max(max,u.find(num) - num + 1);
        }

        return max;
    }

    public static class UnionFind {
        Map<Integer, Integer> parents;

        public UnionFind(int[] arr) {
            parents = new HashMap<>();
            for (int i : arr) {
                parents.put(i, i);
            }
        }

        public Integer find(int x) {
            if (!parents.containsKey(x)) return null;
            int t = parents.get(x);
            if(x != t) parents.put(x, find(t));
            return parents.get(x);
        }

        public boolean union(int x, int y) {
            Integer rootX = find(x), rootY = find(y);
            if (rootX == null || rootY == null) return false;
            if(rootX.equals(rootY)) return false;
            parents.put(rootX, rootY);
            return true;
        }
    }

}
