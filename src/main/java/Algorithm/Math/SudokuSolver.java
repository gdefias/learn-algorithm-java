package Algorithm.Math;

/**
 * Created by Defias on 2017/10/14.
 *
 * Sudoku Solver  数独
 *
 *
 * https://leetcode.com/problems/sudoku-solver/description/
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
