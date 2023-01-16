package Algorithm.DFSAndBFS;

import java.util.*;

/**
 * @author: Felix
 * @date: 2023/1/2
 * @description: 最大人工岛
 *
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 */
public class IslandLargest {
    public int n = 0;

    public static void main(String[] args) {
        IslandLargest O = new IslandLargest();
        int[][] grid = new int[5][5];
        grid[0] = new int[] {1,0,1,0,1};
        grid[1] = new int[] {0,1,1,0,1};
        grid[2] = new int[] {1,1,1,0,0};
        grid[3] = new int[] {1,0,1,1,1};
        grid[4] = new int[] {0,0,1,1,0};

        System.out.println(O.largestIsland(grid));
    }

    public int largestIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Map<Integer, Integer> islandIds = new HashMap<>();  //记录行列坐标key对应的岛ID
        Map<Integer, Integer> islandAreas = new HashMap<>(); //记录岛ID对应的岛面积
        int islandId = 1;
        int maxOneArea = 0;  //最大的单个岛面积
        int maxArea = 0;   //所能得到的最大人工岛面积
        this.n = rows;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j]==1) {
                    int area = dfs(grid, i, j, islandIds, islandId);
                    islandAreas.put(islandId, area);
                    maxOneArea = Math.max(maxOneArea, area);
                    islandId++;
                }
            }
        }


        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(!islandIds.containsKey(getKey(i, j))) {
                    Set<Integer> connectIds = new HashSet<>();
                    if(islandIds.containsKey(getKey(i+1, j))) {
                        connectIds.add(islandIds.get(getKey(i+1, j)));
                    }
                    if(islandIds.containsKey(getKey(i-1, j))) {
                        connectIds.add(islandIds.get(getKey(i-1, j)));
                    }
                    if(islandIds.containsKey(getKey(i, j+1))) {
                        connectIds.add(islandIds.get(getKey(i, j+1)));
                    }
                    if(islandIds.containsKey(getKey(i, j-1))) {
                        connectIds.add(islandIds.get(getKey(i, j-1)));
                    }

                    maxArea = Math.max(maxArea, getArea(islandAreas, connectIds)+1);
                }
            }
        }

        if(maxArea == 0) {
            return maxOneArea;
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int i, int j, Map<Integer, Integer> islandIds, int islandId) {
        int rows = grid.length;
        int cols = grid[0].length;
        if(i<0 || i>=rows || j<0 || j>=cols || grid[i][j]==0) {
            return 0;
        }
        islandIds.put(getKey(i, j), islandId);
        grid[i][j] = 0;

        int cnt = 1;
        cnt += dfs(grid, i-1, j, islandIds, islandId);
        cnt += dfs(grid, i+1, j, islandIds, islandId);
        cnt += dfs(grid, i, j-1, islandIds, islandId);
        cnt += dfs(grid, i, j+1, islandIds, islandId);

        return cnt;
    }

    public int getKey(int i, int j) {
        if(i<0 || i>=this.n || j<0 || j>=this.n) {
            return -1;
        }
        return i*this.n+j+1;
    }

    public int getArea(Map<Integer, Integer> islandAreas, Set<Integer> islandIds) {
        Iterator<Integer> iterator = islandIds.iterator();
        int ans = 0;
        while(iterator.hasNext()) {
            ans += islandAreas.get(iterator.next());
        }

        return ans;
    }
}
