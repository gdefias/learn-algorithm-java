package Algorithm.DP;

/**
 * @author: Felix
 * @date: 2023/1/10
 * @description: 背包问题I

    在 n 个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为Ai
    （每个物品只能选择一次且物品大小均为正整数）

    数组 = [3,4,8,5]
    backpack size = 10
    输出：9

    数组 = [2,3,5,7]
    backpack size = 12
    输出：12
 */
public class BackPack {

    //方法1：DP 直接考虑最多能装多满
    //时间复杂度：O(mn)  空间复杂度：O(mn)
    public int backPack(int m, int[] a) {
        if (m <= 0) {
            return 0;
        }

        int[][] dp = new int[a.length][m+1];  //前i个物品j容量最多能装的大小

        //边界
        for(int i=0; i<a.length; i++) {
            dp[i][0] = 0;
        }

        for(int j=1; j<=m; j++) {
            if(j >= a[0]) {
                dp[0][j] = a[0];
            }
        }

        //递推
        for(int i=1; i<a.length; i++) {
            for(int j=1; j<=m; j++) {
                if(j>=a[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-a[i]]+a[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[a.length-1][m];
    }


    //方法1的空间优化 一维滚动数组
    //时间复杂度：O(mn)  空间复杂度：O(m)
    //逆序的原因： dp为滚动数组，转换方程dp[j] = Math.max(dp[j], dp[j-a[i]]+a[i])可知，逆序时，计算dp[j]时dp[j-a[i]]还未计算，
    //dp[j-a[i]]就相当于dp[i-1][j-a[i]]，符合优化前的二维转换方程，而如果是顺序，计算dp[j]时dp[j-a[i]]已经计算了， dp[j-a[i]]
    //相当于dp[i][j-a[i]]，不符合优化前的二维转换方程
    public int backPack_(int m, int[] a) {
        if (m <= 0) {
            return 0;
        }

        int[] dp = new int[m+1];  //j容量最多能装的大小

        //递推
        for(int i=0; i<a.length; i++) {
            for(int j=m; j>=a[i]; j--) {  //为啥一定要逆序？
                dp[j] = Math.max(dp[j], dp[j-a[i]]+a[i]);
            }
        }

        return dp[m];
    }



    //方法2：DP 计算不同容量（1 ~ m）是否能装满，能装满的小于或等于m的容量中最大的那一个即是最多能装多满
    //时间复杂度：O(mn)  空间复杂度：O(mn)
    public int backPack2(int m, int[] a) {
        if(m <= 0) {
            return 0;
        }

        boolean[][] dp = new boolean[a.length][m+1];  //前i个物品是否能装满大小为j的背包

        //边界
        for(int i=0; i<a.length; i++) {
            dp[i][0] = true;
        }

        for(int j=1; j<=m; j++) {
            if(j == a[0]) {
                dp[0][j] = true;
            }
        }

        //递推
        for(int i=1; i<a.length; i++) {
            for(int j=1; j<=m; j++) {
                if(j >= a[i]) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-a[i]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        for(int j=m; j>=0; j--) {
            if(dp[a.length-1][j]) {
                return j;
            }
        }

        return 0;
    }


    //方法2的空间优化 一维滚动数组
    //时间复杂度：O(mn)  空间复杂度：O(m)
    public int backPack2_(int m, int[] a) {
        if(m <= 0) {
            return 0;
        }

        boolean[] dp = new boolean[m+1];  //是否能装满大小为j的背包

        //边界
        dp[0] = true;

        //递推
        for(int i=0; i<a.length; i++) {
            for(int j=m; j>=a[i]; j--) {  //为啥一定要逆序？
                dp[j] = dp[j] || dp[j-a[i]];
            }
        }

        for(int j=m; j>=0; j--) {
            if(dp[j]) {
                return j;
            }
        }

        return 0;
    }
}
