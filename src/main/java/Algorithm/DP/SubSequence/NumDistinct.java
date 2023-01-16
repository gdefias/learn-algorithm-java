package Algorithm.DP.SubSequence;

/**
 * @author: Felix
 * @date: 2023/1/13
 * @description: 不同的子序列

https://leetcode.cn/problems/distinct-subsequences/description/?orderBy=most_votes

给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个
子序列，而 "AEC" 不是）
题目数据保证答案符合 32 位带符号整数范围。

输入：s = "rabbbit", t = "rabbit"
输出：3

输入：s = "babgbag", t = "bag"
输出：5

提示：
0 <= s.length, t.length <= 1000
s 和 t 由英文字母组成
 */
public class NumDistinct {

    public static void main(String[] args) {
        NumDistinct O = new NumDistinct();
        String s = "babgbag";
        String t = "bag";

        System.out.println(O.numDistinct(s, t));
    }


    //DP 动态规划
    //dp[i][j] 表示在 s[i:] 的子序列中 t[j:] 出现的个数
    //s[i:]表示s从下标i到末尾的子字符串
    public int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();

        if(len1 < len2) {
            return 0;
        }

        int[][] dp = new int[len1+1][len2+1];

        //边界
        for(int i=0; i<=len1; i++) {
            dp[i][len2] = 1;
        }

        for(int j=0; j<len2; j++) {
            dp[len1][j] = 0;
        }

        //递推
        for(int i=len1-1; i>=0; i--) {
            for(int j=len2-1; j>=0; j--) {
                if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j];  //相同的字符可以匹配上也可以不匹配上
                } else {
                    dp[i][j] = dp[i+1][j];
                }


            }
        }

        return dp[0][0];
    }
}
