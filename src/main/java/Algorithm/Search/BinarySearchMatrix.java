package Algorithm.Search;

/**
 * Created by Defias on 2017/10/7.
 *
 * 查找二维矩阵： 写出一个高效的算法来搜索m×n矩阵中的值
 *
 * 这个矩阵具有以下特性：
 * 每行中的整数从左到右是排序的。
 * 每行的第一个数大于上一行的最后一个整数。
 [
 [1, 3, 5, 7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 给出target = 3，返回true
 */
public class BinarySearchMatrix {
    public static void main(String[] args) {
        int[][] nums = new int[][] {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix(nums, 4));
    }


    //解法：两次二分法查找
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int column = matrix[0].length;

        //查找target可能存在的那一行
        int start = 0, end = row-1;
        while (start <= end) {
            int mid = start + (end-start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

        row = start-1;
        if(row < 0) {
            return false;
        }

        //在上一步确定的行中查找target
        start = 0;
        end = column - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return false;
    }
}
