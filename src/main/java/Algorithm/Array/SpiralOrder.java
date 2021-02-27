package Algorithm.Array;
import Lib.Util;

/**
 * Created by Defias on 2017/9/26.
 *
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 * 顺时针打印矩阵 - 按层打印
 */

public class SpiralOrder {

    public static void main(String[] args) {
        int[][] A = Util.MakeArray();
        Solution20(A);

    }

    public static void Solution20(int[][] A) {
        int[] res = spiralOrder(A);

        for(int i=0; i<res.length; i++) {
            System.out.println(res[i]);
        }
    }

    //方法1
    public static int[] spiralOrder(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return new int[0];
        }
        int rows = matrix.length;  //行数
        int columns = matrix[0].length;  //列数
        int[] res = new int[rows*columns];  //保存遍历结果
        int index = 0;

        //四个边界
        int left = 0;
        int right = columns-1;
        int top = 0;
        int bottom = rows-1;

        while(true) {
            //从左到右
            for(int i=left; i<=right; i++) {
                res[index++] = matrix[top][i];
            }
            if(++top>bottom) {
                break;
            }

            //从上到下
            for(int i=top; i<=bottom; i++) {
                res[index++] = matrix[i][right];
            }
            if(--right<left) {
                break;
            }

            //从右到左
            for(int i=right; i>=left; i--) {
                res[index++] = matrix[bottom][i];
            }
            if(--bottom<top) {
                break;
            }

            //从下到上
            for(int i=bottom; i>=top; i--) {
                res[index++] = matrix[i][left];
            }
            if(++left>right) {
                break;
            }
        }
        return res;
    }


    //方法2
    public static int[] printCircle(int[][] A) {
        if(A==null || A.length==0 || A[0].length==0)
            return new int[0];

        int rows = A.length;
        int columns = A[0].length;

        int[] res = new int[rows*columns];
        int index = 0;

        int left=0, right=columns-1, top=0, bottom=rows-1;

        while(left<=right && top<=bottom) {
            for (int column=left; column<=right; column++) {  //打印top行
                res[index++] = A[top][column];
            }

            for(int row=top+1; row<=bottom; row++) {  //打印right列
                res[index++] = A[row][right];
            }

            if(left<right && bottom>top) {
                for(int column=right-1; column>left; column--) {   //打印bottom行
                    res[index++] = A[bottom][column];
                }

                for (int row=bottom; row>top; row--) {     //打印left列
                    res[index++] = A[row][left];
                }
            }

            //打印完一圈统一调整边界
            top++;
            right--;
            bottom--;
            left++;
        }
        return  res;
    }
}
