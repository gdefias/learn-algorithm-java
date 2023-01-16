package Algorithm.Math;
/**
 * Created by Defias on 2017/10/14.

 解数独

 https://leetcode.cn/problems/sudoku-solver/

 编写一个程序，通过填充空格来解决数独问题。

 数独的解法需 遵循如下规则：
 数字 1-9 在每一行只能出现一次。
 数字 1-9 在每一列只能出现一次。
 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 数独部分空格内已填入了数字，空白格用 '.' 表示。

 提示：
 board.length == 9
 board[i].length == 9
 board[i][j] 是一位数字或者 '.'
 题目数据 保证 输入数独仅有一个解
 */

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        if(board==null) {
            return;
        }
        if(board.length!=9 || board[0].length!=9) {
            return;
        }

        solveSudokuHelper(board);
    }

    public static boolean solveSudokuHelper(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        for(int i=0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(board, i, j, k)) {
                            board[i][j] = k;
                            if (solveSudokuHelper(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(char[][] board, int row, int col, int k) {
        for(int i=0; i<9; i++) {
            if(board[row][i]!='.' && board[row][i]==k)
                return false;
            if(board[i][col]!='.' && board[i][col]==k)
                return false;

            if(board[(row/3)*3 + i/3][(col/3)*3 + i%3]!='.' && board[(row/3)*3 + i/3][(col/3)*3 + i%3]==k)
                return false;
        }
        return true;
    }
}
