package Algorithm.Graph.DiGraph;

import Lib.StdOut;
import Algorithm.Queue.MyQueue2;
import java.util.Stack;

/**
 * Created by Defias on 2020/06.
 * Description: BFS - 单点最短有向路径
 *
 * 从源点s到顶点v是否存在一条有向路径，若存在找出其中最短的那条（边数量最少）
 */

public class BreadthFirstDirectedPaths {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // 顶点v是否可达
    private int[] edgeTo;      // 从起点到该顶点的已知路径上的最后一个顶点
    private int[] distTo;      // 起点s到顶点v的最短路径的边数/长度


    public static void main(String[] args) {
        Digraph G = Digraph.mockDigraph();

        int s = 0;
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s)
                        StdOut.print(x);
                    else
                        StdOut.print("->" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }

    //在G中找出所有起点为s的最短路径
    public BreadthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, s);
    }


    //在G中找出所有以sources中任意一点作为起点的最短路径
    public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }


    //从单源点s开始进行宽度优先搜索
    private void bfs(Digraph G, int s) {
        MyQueue2<Integer> q = new MyQueue2<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    //从多源点sources进行宽度优先搜索
    private void bfs(Digraph G, Iterable<Integer> sources) {
        MyQueue2<Integer> q = new MyQueue2<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }


    //是否存在有起点s到顶点v的路径
    public boolean hasPathTo(int v) {
        return marked[v];
    }


    //起点s到顶点v的最短路径的边数
    public int distTo(int v) {
        return distTo[v];
    }


    //返回从起点s到顶点v的最短路径，没有的话返回null
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

}
