package Algorithm.dfsAndbfs;

/**
 * Created with IntelliJ IDEA.
 * Description: 机器人的运动范围 - DFS
 * User: Defias
 * Date: 2018-10
 */

public class MovingCount_DFS {

    public static void main(String[] args) {
        MovingCount_DFS O = new MovingCount_DFS();
        System.out.println(O.movingCount(1,1,1));
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];

        return dfs(m, n, k, visited, 0, 0);
    }

    public int dfs(int m, int n, int k, boolean[][] visited, int i, int j) {
        if(i>=m || j>=n || visited[i][j] || sum(i, j)>k) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(m, n, k, visited, i+1, j) + dfs(m, n, k, visited, i, j+1);    //1 + 右方搜索的可达解总数 + 下方搜索的可达解总数
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
