package Questions.Graph.DiGraph;

import Lib.StdOut;

import java.util.Stack;

/**
 * Created by Defias on 2020/06.
 * Description: DFS - 单点有向路径
 *
 *
 * 从源点s到顶点v是否存在一条有向路径，若存在找出这条路径
 */


public class DepthFirstDirectedPaths {

    private boolean[] marked;  // 顶点v是否可达
    private int[] edgeTo;      // 从起点到该顶点的已知路径上的最后一个顶点
    private final int s;       // 源点


    public static void main(String[] args) {
        Digraph G = Digraph.mockDigraph();

        int s = 0;
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);

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


    public DepthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    //是否存在有起点s到顶点v的有向路径
    public boolean hasPathTo(int v) {
        return marked[v];
    }



    //获取起点s到顶点v的路径，若不存在路径返回null
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
