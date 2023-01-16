package Algorithm.Array;
/**
 * Created by Defias on 2017/10/20.
 *
 * “之”字形打印矩阵
 *
 *  宏观调度
 *  从源点(0,0)开始，分两路，一路（left）向下走，走到头就向右走；一路（right）向右走，走到头就向下走；两路重合时结束
 *  两路同时每走一步，打印一次状态
 *
 *  坐标系：
 *  --------------> Y
 *  |
 *  |
 *  |
 *  |
 *  V
 *  X
 *
 */
public class Array2ZigzagPrintMatrix {

    public static void main(String[] args) {
        int[][] A= new int[][] {
                {1,  2,  3,  4,  5,  6},
                {7,  8,  9,  10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36}
        };

        PrintMatrixZigZag(A);
    }

    public static void PrintMatrixZigZag(int[][] A) {
        int leftX = 0;  //第一路点（先向下走）
        int leftY = 0;

        int rightX = 0; //第二路点（先向右走）
        int rightY = 0;

        int endX = A.length-1;
        int endY = A[0].length-1;
        boolean isFromBottom = true; //控制打印方向

        while(leftY<=endY && rightX<=endX) {
            PrintLine(A, leftX, leftY, rightX, rightY, isFromBottom);
            leftX++;
            rightY++;

            if(leftX > endX) {  //第一路点转弯以后leftX会始终大于endX
                leftX = endX;
                leftY++;
            }

            if(rightY > endY) { //第二路点转弯以后rightY会始终大于endY
                rightY = endY;
                rightX++;
            }

            isFromBottom = !isFromBottom;
            System.out.println();
        }
    }

    public static void PrintLine(int[][] A, int leftX, int leftY, int rightX, int rightY, boolean isFromBottom) {
        if(isFromBottom) {
            int curend = leftY;  //斜向上走时从left点开始 结束点的横坐标等于起始的纵坐标
            while(curend <= leftX ) {
                System.out.print(A[leftX--][leftY++] + " ");
            }
        } else {
            int curend = rightY;   //斜向下走时从right点开始 结束点的横坐标等于起始的纵坐标
            while(curend >= rightX) {
                System.out.print(A[rightX++][rightY--] + " ");
            }
        }
    }
}
