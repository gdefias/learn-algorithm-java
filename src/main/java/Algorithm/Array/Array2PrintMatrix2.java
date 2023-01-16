package Algorithm.Array;
/**
 * 螺旋矩阵 II
 *
 * https://leetcode.cn/problems/spiral-matrix-ii/
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix
 *
 * 给出n或边长（n=k*k）输出回形矩阵（正方形）
 * 输出：
 * 1  2  3  4
 * 12 13 14 5
 * 11 16 15 6
 * 10 9  8  7
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

public class Array2PrintMatrix2 {
    public static void main(String[] args) {
        int n = 16;  //元素个数
        int k = getk(n);  //组成的正方形边长
        if (k == -1) {
            throw new RuntimeException("n error！");
        }

        printMatrix(k);
    }

    //计算边长
    public static int getk(int n) {
        int k=0;
        for(int i=1; i<=n; i++) {
            if(i*i == n) {
                k = i;
            } else if(i*i > n) {
                break;
            }
        }
        if(k==0) {
            return -1;
        }
        return k;
    }

    //顺时针输出回形矩阵图（正方形）
    public static void printMatrix(int k){
        int[][] A = generateMatrix(k);

        for(int x=0; x<k; x++){
            for (int y=0; y<k; y++) {
                System.out.print(A[x][y]+" ");
            }
            System.out.println();
        }
    }


    public static int[][] generateMatrix(int k) {
        int[][] A = new int[k][k];
        int t = 1; // 起始数字
        int x = 0; // x坐标
        int y = 0; // y坐标

        for(int q=0; q<k/2; q++) { //q代表第几圈  总共有k/2圈
            for(x=q,y=q; y<k-q-1; y++){//第一条边 从左往右
                A[x][y]=t++;
            }

            for(x=q,y=k-q-1; x<k-q-1; x++){//第二条边  从上往下
                A[x][y]=t++;
            }

            for(x=k-q-1,y=k-q-1; y>q; y--){//第三条边   从右往左
                A[x][y]=t++;
            }

            for(x=k-q-1,y=q; x>q; x--){//第四条边   从下往上
                A[x][y]=t++;
            }
        }
        if(k%2==1) {  //边长为奇数时中间有个中心元素
            A[k/2][k/2]=t;
        }

        return A;
    }

}
