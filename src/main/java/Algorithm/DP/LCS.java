package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description: 最长公共子序列

 https://leetcode-cn.com/problems/longest-common-subsequence/

 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度
 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 若这两个字符串没有公共子序列，则返回 0。

 输入：text1 = "abcde", text2 = "ace"
 输出：3
 解释：最长公共子序列是 "ace"，它的长度为 3。

 1 <= text1.length <= 1000
 1 <= text2.length <= 1000
 输入的字符串只含有小写英文字符
 */

public class LCS {

    public static void main(String[] args) {
        LCS O = new LCS();
        System.out.println(O.longestCommonSubsequence2("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));
    }

    //普通的递归，存在大量的重复计算
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        return helper(text1, len1, text2, len2);
    }

    public int helper(String text1, int len1, String text2, int len2) {
        if(len1<=0 || len2<=0) {
            return 0;
        }

        if(text1.charAt(len1-1) == text2.charAt(len2-1)) {
            return helper(text1, len1-1, text2, len2-1)+1;
        } else {
            int l1 = helper(text1, len1-1, text2, len2);
            int l2 = helper(text1, len1, text2, len2-1);
            return Math.max(l1, l2);
        }
    }

    // DP
    // 使用二维数组dp[i][j]： text1的前i个字符与text2的前j个字符的最长公共子序列的长度
    public int longestCommonSubsequence1(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1];

        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 0;

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[len1][len2];
    }


  // DP空间优化
  // dp[i][j]只依赖三项：左上角dp[i-1][j-1]  正上方dp[i-1][j]   左边dp[i][j-1]
  // 准备几个变量：
  // leftup: 表示是当前dp[j](dp[i][j])左上角的值，相当于dp[i-1][j-1],初始化的时候为0
  // uprow: 表示是当前dp[j](dp[i][j])正上方的值，相当于dp[i-1][j]
  // dp[j-1]: 表示是当前dp[j](dp[i][j])左边的值，相当于dp[i][j-1]
  // 每一轮结束后，last的值都向前滚动一个，变成正上方的值，也就是tmp
  // 使用一维滚动数组dp[j]
  public int longestCommonSubsequence2(String text1, String text2) {
      int len1 = text1.length();
      int len2 = text2.length();
      int[] dp = new int[len2+1];
      int uprow = 0;
      int leftup = 0;
      for (int i = 1; i <= len1; i++) {  //逐行
          leftup = 0;
          for (int j = 1; j <= len2; j++) {  //逐列
              uprow = dp[j];  //滚动取值，dp[j]的新一轮还没被赋值之前将老值取出来（也就是上一行的值）
              if(text1.charAt(i-1) == text2.charAt(j-1)) {
                  dp[j] = leftup + 1;
              } else {
                  dp[j] = Math.max(dp[j-1], uprow);
              }
              leftup = uprow;  //当前列结束即将进入下一列之前，更新last，上一行的值变为左上角的值
          }
      }
      return dp[len2];
  }
}
