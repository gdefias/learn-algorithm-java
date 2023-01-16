package Algorithm.Array;
import Lib.Util;
/**
 * Created by Defias on 2017/9/26.

   顺时针打印矩阵 -  螺旋矩阵

   https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
   https://leetcode.cn/problems/spiral-matrix/

     给你一个 m 行 n 列的矩阵 matrix ，请按照顺时针螺旋顺序 ，返回矩阵中的所有元素。
     输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     输出：[1,2,3,6,9,8,7,4,5]

坐标系：
    --------------> Y
    |
    |
    |
    |
    V
    X
 */

public class Array2PrintMatrix {

    public static void main(String[] args) {
        int[][] A = Util.make2DArray();
        Util.print2DArray(A);
        Solution(A);
    }

    public static void Solution(int[][] A) {
        int[] res = spiralOrder(A);

        for(int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
            //1 2 3 8 13 18 23 22 21 16 11 6 7 12 17
        }
    }


    /*
    方法1: 按层模拟
    时间复杂度：O(mn)，其中 m 和 n 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。
    空间复杂度：O(1)。除了输出数组以外，空间复杂度是常数。

    思路：
    对于每层，从左上方开始以顺时针的顺序遍历所有元素。假设当前层的左上角位于(top,left)，右下角位于 (bottom,right)，按照如下顺序遍
    历当前层的元素。
    从左到右遍历上侧元素，依次为 (top,left) 到 (top,right)。
    从上到下遍历右侧元素，依次为 (top+1,right) 到 (bottom,right)。
    如果left<right且top<bottom ，则从右到左遍历下侧元素，依次为 (bottom,right−1) 到 (bottom,left+1)，以及从下到上遍历左侧元素，
    依次为(bottom,left)到(top+1,left)。
    遍历完当前层的元素之后，将left和top分别增加1 ，将right和bottom分别减少1，进入下一层继续遍历，直到遍历完所有元素为止。
    * */
    public static int[] spiralOrder(int[][] A) {
        if(A==null || A.length==0 || A[0].length==0)
            return new int[0];

        int rows = A.length; //行数
        int columns = A[0].length; //列数

        int[] res = new int[rows*columns]; //保存遍历结果
        int index = 0;

        //四个边界
        int left = 0;
        int right = columns-1;

        int top = 0;
        int bottom = rows-1;

        while(left<=right && top<=bottom) {
            for(int i=left; i<=right; i++) {  //打印top行  从左到右
                res[index++] = A[top][i];
            }

            for(int i=top+1; i<=bottom; i++) {  //打印right列 从上到下
                res[index++] = A[i][right];
            }

            if(left<right && bottom>top) {
                for(int i=right-1; i>=left; i--) {   //打印bottom行 从右到左
                    res[index++] = A[bottom][i];
                }

                for(int i=bottom-1; i>top; i--) {     //打印left列 从下到上
                    res[index++] = A[i][left];
                }
            }

            //打印完一圈统一调整边界
            left++;
            right--;

            top++;
            bottom--;

        }
        return  res;
    }

    // 按层模拟的另一种写法
    public static int[] spiralOrder2(int[][] matrix) {
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



}
