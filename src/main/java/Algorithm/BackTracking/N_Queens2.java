package Algorithm.BackTracking;

/**
 * Created by Defias on 2017/10/14.

 N皇后 II

 n皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上

 https://leetcode-cn.com/problems/n-queens-ii/

 给定一个整数 n，返回 n 皇后不同的解决方案的数量
 */

public class N_Queens2 {
    public static int sum = 0;

    public static void main(String[] args) {
        System.out.println(solveNQueens1(8));
    }


    //方法1：只输出摆法的数量
    public static int solveNQueens1(int n) {
        int[] A = new int[n];
        solveNQueens1dfs(A, 0, n);
        return sum;
    }

    public static void solveNQueens1dfs(int[] A, int row, int N) {
        if(row==N) {
            sum++;
            return;
        }

        for(int i=0; i<N; i++) {
            A[row] = i;
            if(isValidate1(A, row, i)) {
                solveNQueens1dfs(A, row+1, N);
            }
        }
    }

    public static boolean isValidate1(int[] A, int row, int col) {
        for(int i=0; i<row; i++) {
            int j = A[i];
            if(j==col || (Math.abs(i-row) == Math.abs(j-col))) {
                return false;
            }
        }
        return true;
    }
}
