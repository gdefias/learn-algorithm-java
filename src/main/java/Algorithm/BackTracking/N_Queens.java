package Algorithm.BackTracking;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Defias on 2020/07.
 * Description: N皇后

 n皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上

 https://leetcode-cn.com/problems/n-queens/

 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案
 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位

 */
public class N_Queens {
    public static void main(String[] args) {
        List<List<String>> res = solveNQueens(8);
        System.out.println(res.size());
        System.out.println(res);
    }

    public static List<List<String>> solveNQueens(int n) {
        char[][] borad = new char[n][n];    //棋盘
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                borad[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();  //满足要求的结果（棋盘上的布局）
        solveNQueensdfs(borad, 0, res);
        return res;
    }

    public static void solveNQueensdfs(char[][] borad, int row, List<List<String>> res) {
        if(row==borad.length) { //得到了一个满足要求的布局存入结果
            res.add(a2l(borad));
            return;
        }

        for(int i=0; i<borad.length; i++) {
            if(isValidate(borad, row, i)) {
                borad[row][i] = 'Q';
                solveNQueensdfs(borad, row+1, res);
                borad[row][i] = '.';
            }
        }
    }

    //检查棋盘的第row行第col列能否放置皇后Q?
    public static boolean isValidate(char[][] borad, int row, int col) {
        for(int i=0; i<row; i++) {
            for(int j=0; j<borad.length; j++) {
                if(borad[i][j]=='Q' && (j==col || Math.abs(i-row)==Math.abs(j-col))) {
                    return false;
                }
            }
        }
        return true;
    }

    //保存二维数组到List
    public static List<String> a2l(char[][] borad) {
        List<String> res = new ArrayList<>();
        for(int i=0; i<borad.length; i++) {
            res.add(new String(borad[i]));
        }
        return res;
    }
}
