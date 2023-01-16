package Algorithm.DFSAndBFS;

/**
 * @author: Felix
 * @date: 2023/1/2
 * @description: 岛屿的周长

    https://leetcode.cn/problems/island-perimeter/description/

    给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
    网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相
    连组成的岛屿）。

    岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。
    计算这个岛屿的周长。

    输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
    输出：16
 */
public class IslandPerimeter {

    public static void main(String[] args) {
        IslandPerimeter O = new IslandPerimeter();
        int[][] grid = new int[4][4];
        grid[0] = new int[] {0,1,0,0};
        grid[1] = new int[] {1,1,1,0};
        grid[2] = new int[] {0,1,0,0};
        grid[3] = new int[] {1,1,0,0};

        System.out.println(O.islandPerimeter2(grid));
    }


    //方法1：DFS
    //时间复杂度：O(nm) 其中n为网格的高度，m为网格的宽度。每个格子至多被遍历一次，因此总时间复杂度为O(nm)
    //空间复杂度：O(nm) 深度优先搜索复杂度取决于递归的栈空间，而栈空间最坏情况下会达到O(nm)
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j] == 1) {
                    return dfs(grid, rows, cols, i, j, visited);
                }
            }
        }
        return 0;
    }

    public int dfs(int[][] grid, int rows, int cols, int i, int j, boolean[][] visited) {
        if(i<0 || i>=rows || j<0 || j>=cols || grid[i][j]==0) {
            return 1;
        }

        if(visited[i][j]) {
            return 0;
        }

        int ans = 0;
        visited[i][j] = true;
        ans += dfs(grid, rows, cols, i-1, j, visited);
        ans += dfs(grid, rows, cols, i+1, j, visited);
        ans += dfs(grid, rows, cols, i, j-1, visited);
        ans += dfs(grid, rows, cols, i, j+1, visited);

        return ans;
    }


    //方法2：迭代
    //遍历每个陆地格子，看其四个方向是否为边界或者水域，如果是，将这条边的贡献（即 1）加入答案ans中
    //时间复杂度：O(nm) 空间复杂度：O(1)
    public int islandPerimeter2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int ans = 0;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j] == 1) {
                    ans += getPerimeter(grid, rows, cols, i-1, j);
                    ans += getPerimeter(grid, rows, cols, i+1, j);
                    ans += getPerimeter(grid, rows, cols, i, j-1);
                    ans += getPerimeter(grid, rows, cols, i, j+1);
                }
            }
        }
        return ans;
    }

    public int getPerimeter(int[][] grid, int rows, int cols, int i, int j) {
        if(i<0 || i>=rows || j<0 || j>=cols || grid[i][j]==0) {
            return 1;
        }
        return 0;
    }
}
