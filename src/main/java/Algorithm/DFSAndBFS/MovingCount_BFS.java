package Algorithm.DFSAndBFS;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Created with IntelliJ IDEA.
 * Description:  机器人的运动范围 - BFS
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/

 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到
 方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。

 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 在搜索的过程中搜索方向可以缩减为向右和向下，而不必再向上和向左进行搜索： 因为随着限制条件 k 的增大，(0, 0) 所在的蓝色方格区域内新加入的
 非障碍方格都可以由上方或左方的格子移动一步得到

 */

public class MovingCount_BFS {

    public static void main(String[] args) {
        MovingCount_BFS O = new MovingCount_BFS();
        System.out.println(O.movingCount(16,16,10));
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;

        Queue<int[]> queue = new LinkedList<int[]>();
        int[] start = new int[] {0, 0};  //初始位置
        queue.add(start);

        while(queue.size()>0) {
            int[] tmp = queue.poll();
            int i = tmp[0];
            int j = tmp[1];

            if(i>=m || j>=n || visited[i][j] || sum(i, j)>k ) {
                continue;
            }

            visited[i][j] = true;      //避免重复
            res++;  //每到达一个格子加1

            int[] toright = new int[] {i, j+1};
            queue.add(toright);

            int[] todown = new int[] {i+1, j};
            queue.add(todown);
        }

        return res;
    }

    public int sum(int m, int n) {
        return bitsum(m) + bitsum(n);
    }

    public int bitsum(int n) {
        int sum = 0;
        while(n > 0) {
            sum += n%10;
            n = n/10;
        }
        return sum;
    }
}
