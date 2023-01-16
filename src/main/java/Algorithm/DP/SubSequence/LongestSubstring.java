package Algorithm.DP.SubSequence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Created by Defias on 2020/07.
 * Description: 最长不含重复字符的子字符串

 https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/

 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */

public class LongestSubstring {

    public static void main(String[] args) {
        //String s = "abcdeafgjke";
        String s = "abba";
        System.out.println(lengthOfLongestSubstring2(s));
    }

    /*
    方法1：DP 动态规划
    dp[i]: 字符串的最长不包含重复字符的以第i个字符结尾的子字符串长度

    思路：
    状态定义： 设动态规划列表dp  dp[j]代表 "以字符s[j]为结尾的最长不重复子字符串”的长度。
    转移方程： 固定右边界j ，设字符s[j]左边距离最近的相同字符为s[i] ，即s[i] = s[j]。

    dp[j]={dp[j−1]+1, dp[j−1]<j−i  eg: tyuieb (j=5, i=-1)
          {j−i, dp[j−1]≥j−i       eg: tyuiebcab (i=5, j=8)
    返回值： max(dp) ，即全局的 “最长不重复子字符串” 的长度
    * */
    public static int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len];
        Map<Character, Integer> map = new HashMap<>();

        dp[0] = 1;
        map.put(s.charAt(0), 0);  //字符为key，对应的索引为value

        for(int j=1; j<len; j++) {
            int i = map.getOrDefault(s.charAt(j), -1);  //无重复取-1
            map.put(s.charAt(j), j);  //重复字符后面会覆盖前面的索引值
            if(dp[j-1] < j-i) {
                dp[j] = dp[j-1]+1;
            } else {
                dp[j] = j-i;
            }
        }

        //找最大值
        int max = dp[0];
        for(int j=1; j<len; j++) {
            if(dp[j] > max) {
                max = dp[j];
            }
        }
        return max;
    }

    //方法1空间压缩优化
    public static int lengthOfLongestSubstring_(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0; //记录最大值
        int tmp = 0; //记录以前一个字符结尾的 最长不重复子字符串长度

        for(int j=0; j<s.length(); j++) {
            int i = map.getOrDefault(s.charAt(j), -1); // 获取索引 i
            map.put(s.charAt(j), j); // 更新哈希表
            tmp = tmp < j-i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            max = Math.max(max, tmp); // max(dp[j - 1], dp[j])
        }
        return max;
    }


    //方法2: 双指针 + hashset
    public static int lengthOfLongestSubstring2(String s) {
        if(s.length()<=1) {
            return s.length();
        }

        int len = s.length();
        Set<Character> set = new HashSet<Character>();

        int i = 0;
        int j = 0;
        int ans = 0;
        while(i<len && j<len) {
            if(!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                ans = Math.max(ans, j-i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }

        return ans;
    }


    //方法3：双指针 + 使用hashmap
    public static int lengthOfLongestSubstring3(String s) {
        if(s==null || s.length()==0) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;

        int i = 0;  //左指针
        int j = i;  //右指针
        while(j < s.length()) {
            if(map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j))+1); //i只会往前走，防止类似输入case："abba" 时i又回到后面去了
//                i = map.get(s.charAt(j))+1;  /这样是错误的
            }
            map.put(s.charAt(j), j);

            ans = Math.max(ans, j-i+1);
            j++;
        }

        return ans;
    }


    //方法4：暴力法
    public static int lengthOfLongestSubstring4(String s) {
        if(s.length()<=1) {
            return s.length();
        }

        int len = s.length();
        int ans = 0;
        for(int i=0; i<len; ++i) {
            for(int j=i+1; j<=len; ++j) {
                if(allunique(s, i, j)) {  //遍历所有区间，判断每个区间是否存在重复字符，已更新不重复的最大长度
                    ans = Math.max(ans, j-i);
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean allunique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for(int i=start; i<end; i++) {
            Character ch = s.charAt(i);
            if(set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

}
