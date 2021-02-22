package Questions.dfsAndbfs;

/**
 * Created with IntelliJ IDEA.
 * Description: 机器人的运动范围 - DFS
 * User: Defias
 * Date: 2018-10

 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到
 方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。

 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */

public class MovingCount_DFS {
    public boolean[][] visited;
    public int m, n, k;

    public static void main(String[] args) {
        MovingCount_DFS O = new MovingCount_DFS();
        System.out.println(O.movingCount(1,1,1));
    }

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;

        this.visited = new boolean[m][n];
        return dfs(0, 0);
    }

    public int dfs(int i, int j) {
        if(i>=m || j>=n || visited[i][j] || sum(i, j)>k) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(i, j+1) + dfs(i+1, j);   //1 + 右方搜索的可达解总数 + 下方搜索的可达解总数
    }

    public int sum(int m, int n) {
        return bitsum(m) + bitsum(n);
    }

    public int bitsum(int n) {
        int sum = 0;
        while(n != 0) {
            sum += n%10;
            n = n/10;
        }
        return sum;
    }
}
