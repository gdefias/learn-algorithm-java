package Algorithm.DP.SubSequence;

import java.util.Arrays;

/**
 * Created by Defias on 2020/07.
 * Description: 最长公共子序列

 https://leetcode-cn.com/problems/longest-common-subsequence/

 给定两个字符串text1 和text2，返回这两个字符串的最长公共子序列的长度
 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

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
    public int[][] dp;

    public static void main(String[] args) {
        LCS O = new LCS();
        String text1 = "pmjghexybyrgzczy"; //pmjghexybyrgzczy
        String text2 = "hafcdqbgncrcbihkd";  //hafcdqbgncrcbihkd
        System.out.println(O.longestCommonSubsequence4(text1, text2));
    }

    //方法1：普通递归 存在大量的重复计算
    //时间复杂度: m=n时接近O(2^n)
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

    //方法2：DP  自顶向下  备忘录  迭代
    //备忘录使用二维数组dp[i][j]表示： text1的前i个字符与text2的前j个字符的最长公共子序列的长度
    //时间复杂度: O(m*n)  空间复杂度: O(m*n)
    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        dp = new int[len1+1][len2+1];
        for(int i=0; i<=len1; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 0;

        return helper2(text1, len1, text2, len2);
    }

    public int helper2(String text1, int len1, String text2, int len2) {
        if(len1<=0 || len2<=0) {
            return 0;
        }

        if(dp[len1][len2] != -1) {
            return dp[len1][len2];
        }

        if(text1.charAt(len1-1) == text2.charAt(len2-1)) {
            dp[len1][len2] = helper2(text1, len1-1, text2, len2-1)+1;
        } else {
            int l1 = helper2(text1, len1-1, text2, len2);
            int l2 = helper2(text1, len1, text2, len2-1);
            dp[len1][len2] =  Math.max(l1, l2);
        }

        return dp[len1][len2];
    }


    //方法3：DP  自底向上 备忘录  迭代
    //备忘录使用二维数组dp[i][j]表示： text1的前i个字符与text2的前j个字符的最长公共子序列的长度
    //时间复杂度: O(m*n)  空间复杂度: O(m*n)
    public int longestCommonSubsequence3(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1+1][len2+1];
//        dp[0][0] = 0;   //默认值本身就是0
//        dp[0][1] = 0;
//        dp[1][0] = 0;

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


    /*
    方法4：DP  自底向上 备忘录空间优化（二维数组优化为一维滚动数组）  迭代

    https://leetcode.cn/problems/longest-common-subsequence/solutions/697027/er-wei-shu-zu-dphe-yi-wei-shu-zu-dpde-sh-w96r/?q=%E7%A9%BA%E9%97%B4%E4%BC%98%E5%8C%96&orderBy=most_relevant

    优化思路：
    dp[i][j]只依赖三项： 正上方dp[i-1][j]  左上角dp[i-1][j-1]  左边dp[i][j-1]
    每次计算新的一行的时候, 用到的都是上一行或者本行之前算过的数据, 所以可以优化到一维数组（一维滚动数组dp[j]）
    比如计算dp[3][2]的时候, 用到的可能有他的左上角, 正上方, 和左侧数据. 左侧数据是在同一行, 上方数据还未被覆盖, 还是上一行的老数据,
    所以都可以直接用, 这里唯一注意就是左上方的数据, 因为在计算前一列的时候会被覆盖, 需要在被覆盖之前保存下旧值

    时间复杂度: O(m*n)  空间复杂度: O(1)
    * */
    public int longestCommonSubsequence4(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[] dp = new int[len2+1];  //默认值本身就是0
        int leftup = 0;  //左上角的值

        for(int i=1; i<=len1; i++) {
            leftup = 0;  //每计算完一行后 leftup的值从0开始
            for(int j=1; j<=len2; j++) {
                int tmp = dp[j]; // 记录未被覆盖之前的dp[j], 它会在计算j+1的时候作为upLeft用到
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[j] = leftup + 1;
                } else {
                    dp[j] = Math.max(dp[j-1], dp[j]); //滚动取值，dp[j]的新一轮还没被赋值之前将老值取出来（也就是上一行的值）
                }
                leftup = tmp;  //当前列结束即将进入下一列之前，更新leftup，上一列的值变为左上角的值
            }
        }

        return dp[len2];
    }
}
