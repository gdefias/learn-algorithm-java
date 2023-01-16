package Algorithm.DP.SubSequence;

/**
 * @author: Felix
 * @date: 2023/1/12
 * @description: 两个字符串的删除操作

https://leetcode.cn/problems/delete-operation-for-two-strings/description/?orderBy=most_votes

给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
每步 可以删除任意一个字符串中的一个字符。

输入: word1 = "sea", word2 = "eat"
输出: 2
解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"


输入：word1 = "leetcode", word2 = "etco"
输出：4
 */
public class MinDistance {


    //DP 动态规划 就是最长公共子序列LCS问题
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1+1][len2+1];

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int maxlen = dp[len1][len2];
        return (len1-maxlen)+(len2-maxlen);
    }
}
