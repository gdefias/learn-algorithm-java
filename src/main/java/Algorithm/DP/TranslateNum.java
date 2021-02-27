package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description: 把数字翻译成字符串

 https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/

 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个
 翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

 输入: 12258
 输出: 5
 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"

 0 <= num < 2^31
 */
public class TranslateNum {

    //DP
    //dp[i] 以Xi结尾的数字的翻译方法数（i位数）
    public int translateNum(int num) {
        String ns = String.valueOf(num);
        int len = ns.length();
        int[] dp = new int[len+1];
        dp[0] = 1;   //边界条件
        dp[1] = 1;  //第1位数结尾的数字的翻译方法数

        for(int i=2; i<=len; i++) {
            String last = ns.substring(i-2, i);
            if(last.compareTo("10")>=0 && last.compareTo("25")<=0) {
                dp[i] = dp[i-1] + dp[i-2];
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[len];
    }

    // DP 空间优化
    public int translateNum2(int num) {
        String ns = String.valueOf(num);
        int len = ns.length();

        int prevv = 1;
        int prev = 1;
        int curr = 1;

        for(int i=2; i<=len; i++) {
            String last = ns.substring(i-2, i);
            if(last.compareTo("10")>=0 && last.compareTo("25")<=0) {
                curr = prev + prevv;
            } else {
                curr = prev;
            }

            prevv = prev;
            prev = curr;
        }

        return curr;
    }



}
