package Algorithm.Array;
/**
 * 给出行数rows和列数cols输出回形矩阵
 *
 * rows=3 cols=5
 * 输出：
 * 1  2  3   4  5
 * 12 13 14 15  6
 * 11 10 9  8   7
 *
 *  坐标系：
 *  --------------> Y
 *  |
 *  |
 *  |
 *  |
 *  V
 *  X
 * */
public class Array2PrintMatrix3 {

    public static void main(String[] args) {

        print2DArray(5, 5);
    }


    public static void print2DArray(int rows, int cols) {
        int [][]A = new int[rows][cols];
        int t = 1; // 起始数字

        int left = 0;
        int right = cols-1;
        int top = 0;
        int bottom = rows-1;

        while(left<=right && top<=bottom) {
            for(int i=left; i<=right; i++) {
                A[top][i] = t++;
            }

            for(int i=top+1; i<=bottom; i++) {
                A[i][right] = t++;
            }

            if(left<right && top<bottom) {
                for(int i=right-1; i>=left; i--) {
                    A[bottom][i] = t++;
                }

                for(int i=bottom-1; i>top; i--) {
                    A[i][left] = t++;
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }

        //输出
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                System.out.print(A[i][j]+",");
            }
            System.out.println();
        }
    }
}
