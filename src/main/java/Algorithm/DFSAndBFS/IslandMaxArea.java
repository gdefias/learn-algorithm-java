package Algorithm.DFSAndBFS;

/**
 * @author: Felix
 * @date: 2023/1/2
 * @description:
 */
public class IslandMaxArea {

    public static void main(String[] args) {
        IslandMaxArea O = new IslandMaxArea();
        int[][] grid = new int[4][4];
        grid[0] = new int[] {0,1,0,0};
        grid[1] = new int[] {1,1,1,0};
        grid[2] = new int[] {0,1,0,0};
        grid[3] = new int[] {1,1,0,0};

        System.out.println(O.maxAreaOfIsland(grid));
    }

    //DFS
    //时间复杂度：O(m×n) 空间复杂度：O(m×n)
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j]==1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }

        return maxArea;
    }

    public int dfs(int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        if(i<0 || i>=rows || j<0 || j>=cols || grid[i][j]==0) {
            return 0;
        }
        grid[i][j] = 0;

        int cnt = 1;
        cnt += dfs(grid, i-1, j);
        cnt += dfs(grid, i+1, j);
        cnt += dfs(grid, i, j-1);
        cnt += dfs(grid, i, j+1);

        return cnt;
    }
}
