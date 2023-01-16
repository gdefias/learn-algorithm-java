package Algorithm.BackTracking;

import java.util.HashSet;
import java.util.Set;

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
        System.out.println(totalNQueens(8));
    }

    //方法1： DFS
    //时间复杂度：大于O(N!)  需要O(N)的时间复杂度来判断某个位置是否可以放置皇后
    public static int totalNQueens(int n) {
        int[] A = new int[n];
        dfs(A, 0, n);
        return sum;
    }

    public static void dfs(int[] A, int row, int N) {
        if(row == N) {
            sum++;
            return;
        }

        for(int i=0; i<N; i++) {
            A[row] = i;
            if(isValidate(A, row, i)) {
                dfs(A, row+1, N);
            }
        }
    }

    public static boolean isValidate(int[] A, int row, int col) {
        for(int i=0; i<row; i++) {
            int j = A[i];
            if(j==col || (Math.abs(i-row) == Math.abs(j-col))) {
                return false;
            }
        }
        return true;
    }



    //方法2： 回溯 - 基于集合的回溯
    //时间复杂度：大于O(N!)  能够在O(1)的时间内判断一个位置是否可以放置皇后
    public int totalNQueens2(int n) {
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        return backtrack(n, 0, columns, diagonals1, diagonals2);
    }

    public int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;  //统计可以放置皇后的总数量
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);

                count += backtrack(n, row + 1, columns, diagonals1, diagonals2);

                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

}
