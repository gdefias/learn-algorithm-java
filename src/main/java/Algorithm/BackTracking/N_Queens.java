package Algorithm.BackTracking;
import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: N皇后

 n皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上

 https://leetcode-cn.com/problems/n-queens/

 给定一个整数 n，返回所有不同的n皇后问题的解决方案
 每一种解法包含一个明确的n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位

 */
public class N_Queens {
    public static void main(String[] args) {
        List<List<String>> res = solveNQueens(8);
        System.out.println(res.size());

        for(int i=0; i<res.size(); i++) {
            for(int j=0; j<res.get(i).size(); j++) {
                System.out.println(res.get(i).get(j));
            }
            System.out.println("-------------");
        }

    }

    //方法1: 回溯 - 基于二维数组模拟棋盘的回溯
    //时间复杂度：大于O(N!)，其中N是皇后数量  需要O(n^2)的时间内判断一个位置是否可以放置皇后
    //空间复杂度：O(N)，其中N是皇后数量
    public static List<List<String>> solveNQueens(int n) {
        char[][] borad = new char[n][n];    //棋盘
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                borad[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();  //满足要求的结果（棋盘上的布局）
        backtrace(borad, 0, res);  //从第0行开始放置皇后
        return res;
    }

    public static void backtrace(char[][] borad, int row, List<List<String>> res) {
        if(row == borad.length) { //所有行都放完了就得到一个满足要求的布局
            res.add(a2l(borad));
            return;
        }

        //选定行之后，尝试从第0列开始放置皇后
        for(int j=0; j<borad[0].length; j++) {
            if(isValidate(borad, row, j)) {
                borad[row][j] = 'Q';
                backtrace(borad, row+1, res);
                borad[row][j] = '.';
            }
        }
    }

    //检查棋盘的第row行第col列能否放置皇后Q?
    public static boolean isValidate(char[][] borad, int row, int col) {
        for(int i=0; i<row; i++) {  //检查已经放置皇后的第0行到第row-1行
            for(int j=0; j<borad[0].length; j++) {
                //如果第j列放置了一个皇后，那么如果j列刚好是要检查的列那肯定不满足要求
                //另外当前存在皇后的位置与需要检查的位置还不能在一条斜线上，即横坐标差值与纵坐标差值的绝对值不能相当
                if(borad[i][j]=='Q' && (j==col || Math.abs(i-row)==Math.abs(j-col))) {
                    return false;
                }
            }
        }
        return true;
    }

    //二维数组转List
    public static List<String> a2l(char[][] borad) {
        List<String> res = new ArrayList<>();
        for(int i=0; i<borad.length; i++) {
            res.add(new String(borad[i]));
        }
        return res;
    }


    //方法2: 回溯 - 基于集合的回溯
    //时间复杂度：O(N!)，其中N是皇后数量  能够在O(1)的时间内判断一个位置是否可以放置皇后
    //空间复杂度：O(N)，其中N是皇后数量
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        int[] queens = new int[n];  //记录放置的皇后的行列坐标，索引为行，值为列
        Arrays.fill(queens, -1);

        //为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合columns、diagonals1和diagonals2分别记录每一列以及两个方
        //向的每条斜线上是否有皇后
        //方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，例如(0,0)和(3,3)在同一条方向一的斜线上，行下标与列下标之差都等于0
        //方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等，例如(3,0)和(1,2)在同一条方向二的斜线上，行下标与列下标之和都等于3
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();

        backtrack2(res, queens, n, 0, columns, diagonals1, diagonals2);
        return res;
    }

    public void backtrack2(List<List<String>> res, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            res.add(board);
        } else {
            for (int i = 0; i < n; i++) { //选定行之后，尝试从第0列开始放置皇后
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

                queens[row] = i;  //第row行第i列放置一个皇后
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);

                backtrack2(res, queens, n, row + 1, columns, diagonals1, diagonals2);

                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    //根据放置的皇后行列坐标生成一个棋盘布局
    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

}
