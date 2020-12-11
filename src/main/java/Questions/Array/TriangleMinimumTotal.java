package Questions.Array;

/**
 * Created by Defias on 2017/10/10.

 三角形最小路径和

 https://leetcode-cn.com/problems/triangle/

 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上
 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）


 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分
 */
import java.util.*;

public class TriangleMinimumTotal {
    public static int sum=0;

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6, 7),
                Arrays.asList(4, 1, 8, 3)
        );

        System.out.println(minimumTotal3(triangle));
    }

    //DFS
    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null) {
            throw new ArithmeticException();
        }
        if(triangle.size()==0) {
            return 0;
        }
        ArrayTriangleHelper(triangle, 0, 0);
        return sum;
    }

    public static void  ArrayTriangleHelper(List<List<Integer>> triangle, int m, int n) {
        if(triangle.size() < m+1) {
            return;
        } else {
            if (m==0) {
                sum += triangle.get(0).get(0);
                ArrayTriangleHelper(triangle, m+1, 0);
            } else {
                if(triangle.get(m).get(n) < triangle.get(m).get(n+1)) {
                    sum += triangle.get(m).get(n);
                } else {
                    sum += triangle.get(m).get(n+1);
                    n = n+1;
                }
                ArrayTriangleHelper(triangle, m+1, n);
            }
        }
    }

    //DP - 在原三角形矩阵中进行修改（从底向上）
    public static int minimumTotal2(List<List<Integer>> triangle) {
        for(int i=triangle.size()-2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
        return triangle.get(0).get(0);
    }

    //DP - 不修改原三角形矩阵
    public static int minimumTotal3(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }
}
