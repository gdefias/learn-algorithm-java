package Algorithm.Graph;

import Algorithm.Queue.MyQueue2;
import Lib.StdOut;

/**
 * Created by Defias on 2020/06.
 * Description: 图中的所有连通分量 - 连通性
 *
 * 使用DFS深度优先搜索(使用并查集算法更好)
 *
 */
public class CC {

    private boolean[] marked;   // 顶点v是否已标记了
    private int[] id;           // 各顶点的连通分量标识id
    private int[] size;         // 各连通分量的大小
    private int count;          // 连通分量的数量


    public static void main(String[] args) {
        Graph G = Graph.mockGraph();
        int s = 0;

        CC cc = new CC(G);

        //连通分量数
        int m = cc.count();
        StdOut.println(m + " components");

        //每个连通分量一个队列
        MyQueue2<Integer>[] components = (MyQueue2<Integer>[]) new MyQueue2[m];
        for (int i = 0; i < m; i++) {
            components[i] = new MyQueue2<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        //打印
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }

    //预处理 - 图
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }


    //预处理 - 加权无向图
    public CC(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // depth-first search for a Graph
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    // depth-first search for an EdgeWeightedGraph
    private void dfs(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }


    //获取顶点v所在的连通分量的标识(0~count()-1)
    public int id(int v) {
        return id[v];
    }


    //获取指定顶点所在连通分量的大小
    public int size(int v) {
        return size[id[v]];
    }

    //连通分量数
    public int count() {
        return count;
    }


    //顶点v和w是否连通
    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }


    //顶点v和w是否在同一个连通分量中
    @Deprecated
    public boolean areConnected(int v, int w) {
        return id(v) == id(w);
    }
}
