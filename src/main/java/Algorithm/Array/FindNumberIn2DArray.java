package Algorithm.Array;

import Lib.Util;

/**
 * Created by Defias on 2020/07.
 * Description:  二维数组中的查找
 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个
 整数，判断数组中是否含有该整数。

 https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/

 现有矩阵 matrix 如下：
 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 给定 target=5，返回true。
 给定target=20，返回false。

 0 <= n <= 1000
 0 <= m <= 1000
 */

public class FindNumberIn2DArray {

    public static int[][] A;

    public static void main(String[] args) {
        A = Util.make2DArray();
        System.out.println(findNumberIn2DArray(A, 20));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int i=0, j=cols-1;
        while(i<rows&&j>=0) {
            if(matrix[i][j] == target) {
                return true;
            } else if(matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
