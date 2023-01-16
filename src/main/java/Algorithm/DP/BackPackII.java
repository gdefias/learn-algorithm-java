package Algorithm.DP;

/**
 * @author: Felix
 * @date: 2023/1/10
 * @description: 背包问题II 01背包问题

    有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
    问最多能装入背包的总价值是多大?

    A[i], V[i], n, m 均为整数
    你不能将物品进行切分
    你所挑选的要装入背包的物品的总大小不能超过 m
    每个物品只能取一次
    m <= 1000
    len(A),len(V)<=100

    m = 10
    A = [2, 3, 5, 7]
    V = [1, 5, 2, 4]
    输出：9
    解释：装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9

    m = 10
    A = [2, 3, 8]
    V = [2, 5, 8]
    输出：10
    解释：装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10

    挑战
    O(nm) 空间复杂度可以通过, 你能把空间复杂度优化为O(m)吗？
 */
public class BackPackII {

    //DP
    //时间复杂度：O(mn)  空间复杂度：O(mn)
    public int backPackII(int m, int[] a, int[] v) {
        if(a.length<=0 || m<=0) {
            return 0;
        }

        int[][] dp = new int[a.length][m+1]; //前i件物品放入容量j的背包，可获得的最大价值

        //边界
        for(int i=0; i<a.length; i++) {
            dp[i][0] = 0;
        }

        for(int j=1; j<=m; j++) {
            if(j >= a[0]) {
                dp[0][j] = v[0];
            }
        }

        for(int i=1; i<a.length; i++) {
            for(int j=1; j<=m; j++) {
                if(j >= a[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-a[i]]+v[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[a.length-1][m];
    }


    //DP 空间优化 一维滚动数组
    //时间复杂度：O(mn)  空间复杂度：O(m)
    public int backPackII_(int m, int[] a, int[] v) {
        if(a.length<=0 || m<=0) {
            return 0;
        }

        int[] dp = new int[m+1]; //容量j的背包，可获得的最大价值


        for(int i=0; i<a.length; i++) {
            for(int j=m; j>=a[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-a[i]]+v[i]);
            }
        }

        return dp[m];
    }
}
