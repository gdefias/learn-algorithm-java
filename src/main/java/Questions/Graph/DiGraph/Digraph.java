package Questions.Graph.DiGraph;

import Questions.Set.Bag;
import Lib.In;
import com.sun.istack.internal.NotNull;

import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * Created by Defias on 2020/06.
 * Description: 有向图
 */

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;           // 顶点数
    private int E;                 // 边数
    private Bag<Integer>[] adj;    // 邻接表
    private int[] indegree;        // 顶点的入度


    public static void main(String[] args) {
        Digraph G = Digraph.mockDigraph();
        System.out.println(G);
    }

    //制造一个图
    @NotNull
    public static Digraph mockDigraph() {
        Digraph digraph = new Digraph(9);
        digraph.addEdge(1,1);
        digraph.addEdge(1,2);
        digraph.addEdge(2,5);
        digraph.addEdge(3,6);
        digraph.addEdge(5,3);
        digraph.addEdge(5,4);
        digraph.addEdge(0,3);
        digraph.addEdge(4,6);
        digraph.addEdge(7,6);
        digraph.addEdge(6,8);
        return digraph;
    }


    //初始化空图 - 顶点数
    public Digraph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }


    //初始化空图 - 输入流
    public Digraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0)
                throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }


    //初始化空图 - 拷贝指定的有向图
    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < V; v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }


    //顶点数
    public int V() {
        return V;
    }

   //边数
    public int E() {
        return E;
    }


    // throw an IndexOutOfBoundsException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

   //给有向图添加一有向条边
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

   //获取指定顶点的邻接表(有顶点v指向的所有顶点)
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    //获取指定顶点的出度
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    //获取指定顶点的入度
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    //图的反向图
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    //字符串表示
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
