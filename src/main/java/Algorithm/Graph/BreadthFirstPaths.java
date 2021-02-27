package Algorithm.Graph;

import Algorithm.Queue.MyQueue2;
import Lib.StdOut;
import java.util.Stack;

/**
 * Created by Defias on 2020/06.
 * Description: BFS  图的广度优先搜索  - 单点最短路径
 */

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // 起点s到顶点v的最短路径已知吗 （v：下标索引）
    private int[] edgeTo;      // 起点s到顶点v的已知路径上的最后一个顶点（v：下标索引）
    private int[] distTo;      // 起点s到顶点v的最短路径的边数/长度、（v：下标索引）


    public static void main(String[] args) {
        Graph G = Graph.mockGraph();
        int s = 0;

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s)
                        StdOut.print(x);
                    else
                        StdOut.print(x+"-");
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }


    //在G中找出所有起点为s的最短路径
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);

        //assert check(G, s);
    }


    //在G中找出所有以sources中任意一点作为起点的最短路径
    public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }


    //从单源点s开始进行宽度优先搜索
    private void bfs(Graph G, int s) {
        MyQueue2<Integer> q = new MyQueue2<Integer>(); //队列

        for (int v=0; v<G.V(); v++)
            distTo[v] = INFINITY;   //起点s到各顶点v的最短路径的边数设为无穷大

        distTo[s] = 0;  //起点s到起点s的最短路径的边数设为无穷大
        marked[s] = true;   //标记起点
        q.enqueue(s);  //入队
        while (!q.isEmpty()) {
            int v = q.dequeue();   //出队
            for (int w : G.adj(v)) {
                if (!marked[w]) {   //对于每个未标记的相邻顶点
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    //从多源点sources进行宽度优先搜索
    private void bfs(Graph G, Iterable<Integer> sources) {
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
        if (!hasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x=v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }


    // 校验：check optimality conditions for single source
    private boolean check(Graph G, int s) {

        // check that the distance of s = 0
        if (distTo[s] != 0) {
            StdOut.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    StdOut.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("distTo[" + v + "] = " + distTo[v]);
                    StdOut.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }

        // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
        // provided v is reachable from s
        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                StdOut.println("shortest path edge " + v + "-" + w);
                StdOut.println("distTo[" + v + "] = " + distTo[v]);
                StdOut.println("distTo[" + w + "] = " + distTo[w]);
                return false;
            }
        }

        return true;
    }
}
