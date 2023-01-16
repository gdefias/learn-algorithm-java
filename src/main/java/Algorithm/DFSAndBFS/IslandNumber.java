package Algorithm.DFSAndBFS;

import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by Defias on 2017/10/14.

 岛屿数量

 https://leetcode.cn/problems/number-of-islands/

 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

 此外，你可以假设该网格的四条边均被水包围。

 输入:
 [
 ['1','1','1','1','0'],
 ['1','1','0','1','0'],
 ['1','1','0','0','0'],
 ['0','0','0','0','0']
 ]
 输出:1

 输入:
 [
 ['1','1','0','0','0'],
 ['1','1','0','0','0'],
 ['0','0','1','0','0'],
 ['0','0','0','1','1']
 ]
 输出: 3
 */

public class IslandNumber {

    public static void main(String[] args) {
        IslandNumber O = new IslandNumber();
        char[][] grid = new char[4][5];
        grid[0] = "11000".toCharArray();
        grid[1] = "11000".toCharArray();
        grid[2] = "00100".toCharArray();
        grid[3] = "00011".toCharArray();

        System.out.println(O.numIslands2(grid));
    }

    //方法1：DFS
    //将访问过的'1'的位置置为'0'，相当于visited布尔数组的作用
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j]=='1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    public void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        if(i<0 || i>=rows || j<0 || j>=cols || grid[i][j]=='0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }

    //方法1 DFS的另一种实现
    //用布尔数组标识已访问过的'1'的位置
    public int numIslands_(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int rowN = grid.length;
        int colN = grid[0].length;
        boolean[][] visited = new boolean[rowN][colN];
        int res = 0;


        for(int i=0; i<rowN; i++) {
            for(int j=0; j<colN; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    res++;
                    dfs_(grid, rowN, colN, i, j, visited);
                }

            }
        }

        return res;
    }

    public void dfs_(char[][] grid, int rowN, int colN, int i, int j, boolean[][] visited) {
        if(i<0 || i>=rowN || j<0 || j>=colN || visited[i][j] || grid[i][j]=='0') {
            return;
        }
        visited[i][j] = true;

        dfs_(grid, rowN, colN, i-1, j, visited);
        dfs_(grid, rowN, colN, i+1, j, visited);
        dfs_(grid, rowN, colN, i, j-1, visited);
        dfs_(grid, rowN, colN, i, j+1, visited);
    }



    //方法2：BFS
    //将访问过的'1'的位置置为'0'，相当于visited布尔数组的作用
    public int numIslands2(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j]=='1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void bfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});

        while(!queue.isEmpty()) {
            int[] index = queue.poll();
            i = index[0];
            j = index[1];
            if(i>=0 && i<rows && j>=0 && j<cols && grid[i][j]=='1') {
                grid[i][j] = '0';
                queue.offer(new int[] {i-1, j});
                queue.offer(new int[] {i+1, j});
                queue.offer(new int[] {i, j-1});
                queue.offer(new int[] {i, j+1});
            }
        }
    }
}
