package Questions.DP;
/**
 * Created with IntelliJ IDEA.
 * Description: 丑数
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/chou-shu-lcof/

 编写一个程序，找出第 n 个丑数。
 丑数就是质因数只包含 2, 3, 5 的正整数。

 输入: n = 10
 输出: 12
 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。

 1 是丑数。
 n 不超过1690。
 */
public class NthUglyNumber {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(1500));
    }


    //动态规划（三指针）
    public static int nthUglyNumber(int n) {
        int p1 = 1;
        int p2 = 1;
        int p3 = 1;
        int[] uglyNums = new int[n+1];
        uglyNums[1] = 1;  //第一个丑数是1

        for(int i=2; i<=n; i++) {
            uglyNums[i] = Math.min(Math.min(2*uglyNums[p1], 3*uglyNums[p2]), 5*uglyNums[p3]);

            if(uglyNums[i] == 2*uglyNums[p1]) {
                p1++;
            }

            if(uglyNums[i] == 3*uglyNums[p2]) {
                p2++;
            }

            if(uglyNums[i] == 5*uglyNums[p3]) {
                p3++;
            }
        }
        return uglyNums[n];
    }
}
