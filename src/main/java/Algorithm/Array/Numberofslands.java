package Algorithm.Array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Defias on 2017/10/14.
 *
 * 岛屿数量
 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

 此外，你可以假设该网格的四条边均被水包围。

 输入:
 [
 ['1','1','1','1','0'],
 ['1','1','0','1','0'],
 ['1','1','0','0','0'],
 ['0','0','0','0','0']
 ]
 输出: 1

 输入:
 [
 ['1','1','0','0','0'],
 ['1','1','0','0','0'],
 ['0','0','1','0','0'],
 ['0','0','0','1','1']
 ]
 输出: 3
 */

public class Numberofslands {

    public static void main(String[] args) {
        Numberofslands O = new Numberofslands();
        char[][] grid = new char[4][5];
        grid[0] = "11000".toCharArray();
        grid[1] = "11000".toCharArray();
        grid[2] = "00100".toCharArray();
        grid[3] = "00011".toCharArray();

        System.out.println(O.numIslands1(grid));
    }

    //DFS
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

    //BFS
    public int numIslands1(char[][] grid) {
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
