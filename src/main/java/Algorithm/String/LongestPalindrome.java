package Algorithm.String;

/**
 * @author: Felix
 * @date: 2023/1/6
 * @description: 最长回文子串
    给你一个字符串 s，找到 s 中最长的回文子串。
    如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。

    输入：s = "babad"
    输出："bab"
    解释："aba" 同样是符合题意的答案。

    输入：s = "cbbd"
    输出："bb"

    提示：
    1 <= s.length <= 1000
    s 仅由数字和英文字母组成
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome O = new LongestPalindrome();
        System.out.println(O.longestPalindrome2("babad"));
    }

    //方法1：DP 动态规划
    //时间复杂度：O(n^2) 空间复杂度：O(n^2)
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        for(int i=0; i<len; i++) {
            dp[i][i] = true;
        }

        for(int L=2; L<=len; L++) {
            for(int i=0; i<len; i++) {
               int j = L+i-1;
               if(j>=len) {
                   break;
               }

               if(s.charAt(i) != s.charAt(j)) {
                   dp[i][j] = false;
               } else {
                   if(j-i < 3) {
                       dp[i][j] = true;
                   } else {
                       dp[i][j] = dp[i+1][j-1];
                   }
               }

               if(dp[i][j] && j-i+1>maxLen) {
                   maxLen = j-i+1;
                   begin = i;
               }
            }

        }

        return s.substring(begin, begin+maxLen);
    }

    //方法2：中心扩展法
    //时间复杂度：O(n^2) 空间复杂度：O(1)
    public String longestPalindrome2(String s) {
        int len = s.length();
        if(len < 2) {
            return s;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int lenm = Math.max(len1, len2);
            if (lenm > end - start) {
                start = i - (lenm - 1) / 2;
                end = i + lenm / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
