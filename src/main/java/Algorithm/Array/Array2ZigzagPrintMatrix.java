package Algorithm.Array;
/**
 * Created by Defias on 2017/10/20.
 *
 * “之”字形打印矩阵
 */
public class Array2ZigzagPrintMatrix {

    public static void main(String[] args) {
        int[][] A= new int[][] {{1,2,3,4,5,6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36}
        };

        PrintMatrixZigZag(A);
    }

    public static void PrintMatrixZigZag(int[][] A) {
        int leftX = 0;
        int leftY = 0;
        int rightX = 0;
        int rightY = 0;
        int endX = A.length-1;
        int endY = A[0].length-1;
        boolean isFromBottom = true;

        while(leftY<=endY && rightX<=endX) {
            PrintLine(A, leftX, leftY, rightX, rightY, isFromBottom);
            if(++leftX>endX) {
                leftX=endX;
                leftY++;
            }

            if(++rightY>endY) {
                rightY=endY;
                rightX++;
            }
            isFromBottom = !isFromBottom;
            System.out.println();
        }
    }

    public static void PrintLine(int[][] A, int leftX, int leftY, int rightX, int rightY, boolean isFromBottom) {
        if(isFromBottom) {
            int curend = leftY;
            while(leftX >= curend) {
                System.out.print(A[leftX--][leftY++] + " ");
            }
        } else {
            int curend = rightY;
            while(rightX <= curend) {
                System.out.print(A[rightX++][rightY--] + " ");
            }
        }
    }
}
