package Algorithm.Graph;

import Lib.StdOut;

import java.util.Stack;

/**
 * Created by Defias on 2020/06.
 * Description:  DFS  图的深度优先搜索 - 单点路径
 */


public class DepthFirstPaths {
    private boolean[] marked;    // 这个顶点上是否调用过dfs
    private int[] edgeTo;        // 从起点到该顶点（从1开始）的已知路径上的最后一个顶点
    private final int s;         // 起点


    public static void main(String[] args) {
        Graph G = Graph.mockGraph();
        int s = 0;

        DepthFirstPaths dfs = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s)
                        StdOut.print(x);
                    else
                        StdOut.print(x + "-");
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

    //在G中找出所有起点为s的路径
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    //从顶点v开始进行深度优先搜索
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    //是否存在有起点s到顶点v的路径
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //获取起点s到顶点v的路径，若不存在路径返回null
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x=v; x!=s; x=edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
