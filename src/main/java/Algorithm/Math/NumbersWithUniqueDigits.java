package Algorithm.Math;

/**
 * Created by Defias on 2017/10/10.

 统计各位数字都不同的数字个数

 https://leetcode.cn/problems/count-numbers-with-unique-digits/

 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10^n 。

 输入：n = 2
 输出：91
 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。

 输入：n = 0
 输出：1

 提示：
 0 <= n <= 8

 */

public class NumbersWithUniqueDigits {

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(3));
    }

    //方法1：排列组合
    //思路: 含有d（2≤d≤10）位数的各位数字都不同的数字 x 的个数可以由公式9 * A_9^{d-1}。再加上含有小于d位数的各位数字都不同的数字x的
    //个数，即可得到答案
    //时间复杂度：O(n) 空间复杂度：O(1)
    public static int countNumbersWithUniqueDigits(int n) {
        if(n==0) {
            return 1;
        }

        int base = 9;
        int ans = 10;
        for(int i=0; i<n-1; i++) {  // 0 <= n <= 8
            base = base * (9-i);
            ans = ans + base;
        }
        return ans;
    }
}
