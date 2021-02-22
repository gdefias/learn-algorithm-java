package Questions.DP;
/**
 * Created by Defias on 2020/07.
 * Description: 编辑距离

 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

 https://leetcode-cn.com/problems/edit-distance/

 你可以对一个单词进行如下三种操作：

 插入一个字符
 删除一个字符
 替换一个字符


 输入：word1 = "intention", word2 = "execution"
 输出：5
 解释：
 intention -> inention (删除 't')
 inention -> enention (将 'i' 替换为 'e')
 enention -> exention (将 'n' 替换为 'x')
 exention -> exection (将 'n' 替换为 'c')
 exection -> execution (插入 'u')

 0 <= word1.length, word2.length <= 500
 word1 和 word2 由小写英文字母组成
 */

public class MinDistance {

    public static void main(String[] args) {
        MinDistance O = new MinDistance();
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(O.minDistance2(word1, word2));
    }

    //DP
    //dp[i][j]: 长度为i的单词word1转换为长度为j的单词word2所使用的最少操作数
    public int minDistance(String word1, String word2) {
        if(word1==null || word2==null || (word1 == word2)) {
            return 0;
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];

        dp[0][0] = 0;
        for(int j=1; j<=len2; j++) {
            dp[0][j] = j;
        }

        for(int i=1; i<=len1; i++) {
            dp[i][0] = i;
        }

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    //dp[i][j-1]: 插入   dp[i-1][j]+1：删除    dp[i-1][j-1]:替换
                    dp[i][j] = Math.min(Math.min(dp[i][j-1]+1, dp[i-1][j]+1), dp[i-1][j-1]+1);
                }
            }
        }

        return dp[len1][len2];
    }


    //DP 空间优化
    public int minDistance2(String word1, String word2) {
        if(word1==null || word2==null || (word1 == word2)) {
            return 0;
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2+1];

        for(int j=0; j<=len2; j++) {
            dp[j] = j;
        }


        for(int i=1; i<=len1; i++) {
            int temp = dp[0];
            dp[0] = i;
            for(int j=1; j<=len2; j++) {
                //pre相当于之前的dp[i-1][j-1]
                int pre = temp;
                temp = dp[j];  //相当于dp[i-1][j]

                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[j] = pre;
                } else {
                    //dp[j-1]: 插入   dp[j]+1：删除    dp[j-1]:替换
                    dp[j] = Math.min(Math.min(dp[j-1]+1, temp+1), pre+1);
                }
            }
        }
        return dp[len2];
    }
}
