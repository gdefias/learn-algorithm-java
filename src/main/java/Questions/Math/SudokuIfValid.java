package Questions.Math;

import java.util.HashSet;

/**
 * Created by Defias on 2017/10/14.
 *
 * Valid Sudoku
 *
 * https://leetcode.com/problems/valid-sudoku/description/
 */


public class SudokuIfValid {

    public boolean isValidSudoku(char[][] board) {
        if(board==null) {
            return false;
        }

        int row = board.length;
        int col = board[0].length;
        if(row!=9 || col!=9) {
            return false;
        }

        HashSet<String> set = new HashSet<>();
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(board[i][j]!='.') {
                    int checked = board[i][j];
                    if(checked<1 || checked>9) {
                        return false;
                    }
                    String row_value = "row_" + i + "_" + checked;
                    String col_value = "col_" + j + "_" + checked;
                    String box_value = "box_" + (i/3)*3+j/3 + "_" + checked;
                    if(!set.add(row_value) || !set.add(col_value) || !set.add(box_value)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
