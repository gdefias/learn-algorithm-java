package Algorithm.Others;

/**
 * Created by Defias on 2020/07.
 * Description: 剪绳子
 *
 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 例如，当绳子的长度是8时，把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

 输入: 2
 输出: 1
 解释: 2 = 1 + 1, 1 × 1 = 1

 输入: 10
 输出: 36
 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36

 2 <= n <= 58
 */
public class CuttingRope {

    public int cuttingRope(int n) {
        if(n<=0) {
            return 0;
        }

        if(n<=3) {
            return n-1;
        }

        int sum = 1;
        while(n>4) {
            sum *= 3;
            n = n - 3;
        }

        return sum * n;
    }
}
