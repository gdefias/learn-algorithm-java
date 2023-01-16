package Algorithm.DP;

/**
 * @author: Felix
 * @date: 2023/1/10
 * @description: 背包问题III  完全背包问题

    给定 n 种物品, 每种物品都有无限个. 第 i 个物品的体积为 A[i], 价值为 V[i].
    再给定一个容量为 m 的背包. 问可以装入背包的最大价值是多少?

    不能将一个物品分成小块.
    放入背包的物品的总大小不能超过 m.

    输入: A = [2, 3, 5, 7], V = [1, 5, 2, 4], m = 10
    输出: 15
    解释: 装入三个物品 1 (A[1] = 3, V[1] = 5), 总价值 15.

    输入: A = [1, 2, 3], V = [1, 2, 3], m = 5
    输出: 5
    解释: 策略不唯一. 比如, 装入五个物品 0 (A[0] = 1, V[0] = 1).

 */
public class BackPackIII {

    //DP
    //时间复杂度：O(mn)  空间复杂度：O(mn)
    public int backPackIII(int[] a, int[] v, int m) {
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
                dp[0][j] = v[0] * (j/a[0]);
            }
        }

        //递推
        for(int i=1; i<a.length; i++) {
            for(int j=0; j<=m; j++) {
                if(j >= a[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-a[i]]+v[i]); //可重复取dp[i][j-a[i]]+v[i]
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[a.length-1][m];
    }

    //DP 空间优化
    //时间复杂度：O(mn)  空间复杂度：O(m)
    public int backPackIII_(int[] a, int[] v, int m) {
        if(a.length<=0 || m<=0) {
            return 0;
        }

        int[] dp = new int[m+1]; //容量j的背包，可获得的最大价值


        //递推
        for(int i=0; i<a.length; i++) {
            for(int j=0; j<=m; j++) {
                if(j >= a[i]) {
                    dp[j] = Math.max(dp[j], dp[j-a[i]]+v[i]);
                }
            }
        }

        return dp[m];
    }
}
