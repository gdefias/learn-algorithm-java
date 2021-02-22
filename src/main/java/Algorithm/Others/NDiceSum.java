package Questions.Others;

/**
 * Created with IntelliJ IDEA.
 * Description: n个骰子的点数
 * User: Defias
 * Date: 2018-10
 *
 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 输入: 1
 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]

 输入: 2
 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]

 1 <= n <= 11
 */
public class NDiceSum {

    public static void main(String[] args) {

    }
    public double[] twoSum(int n) {
        //掷i枚筛子点数和s的次数
        int[][] dp = new int[n+1][6*n+1];

        //初始情况掷1枚筛子
        for(int s=1; s<=6; s++) {
            dp[1][s] = 1;
        }

        //从掷2枚筛子到掷n枚筛子
        for(int i=2; i<=n; i++) {
            for(int s=i; s<=6*i; s++) {
                for(int d=1; d<=6; d++) {
                    if(s-d<i-1) {
                        break;
                    }
                    dp[i][s] += dp[i-1][s-d];
                }
            }
        }

        double ncount = Math.pow(6, n);
        double[] res = new double[5*n+1];
        for(int s=n; s<=6*n; s++) {
            res[s-n] = dp[n][s]/ncount;
        }
        return res;
    }
}
