package Questions.Graph;

import Questions.Set.Bag;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by Defias on 2020/06.
 * Description:  图 - 邻接表表示
 */


public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;   //顶点数目
    private int E;   //边数目
    private Bag<Integer>[] adj;   //邻接表


    public static void main(String[] args) {
        Graph graph = mockGraph();

        System.out.println(graph);
    }


    //构造一个空图(V个顶点，0条边)
    public Graph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v=0; v<V; v++) {
            adj[v] = new Bag<Integer>();   //每个顶点对应一个空的背包用于存放相邻的顶点
        }
    }


    //从指定输入流in(数组input表示)读取数据来创建图
    //input[0]:顶点数  input[1]:边数   input[2]~input[input.length-1]:每一对数表示一条边
    public Graph(int[] input) {
        try {
            this.V = input[0];
            if (V < 0)
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = new Bag[V];
            for (int v=0; v<V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = input[1];
            if (E<0)
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");

            for (int i=0; i<E; i++) {
                int v = input[2*i+2];
                int w = input[2*i+3];
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }


    //从一个图深度拷贝一个图
    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v=0; v<G.V(); v++) {
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

    //制造一个图
    public static Graph mockGraph() {
        Graph graph = new Graph(9);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,5);
        graph.addEdge(0,6);
        graph.addEdge(5,3);
        graph.addEdge(5,4);
        graph.addEdge(4,3);
        graph.addEdge(4,6);
        graph.addEdge(7,6);
        graph.addEdge(7,8);

        return graph;
    }

   //图的顶点数
    public int V() {
        return V;
    }

    //图的边数
    public int E() {
        return E;
    }

    // throw an IndexOutOfBoundsException unless {@code 0 <= v < V}
    // 顶点校验
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }


   //通过指定边的两个端点添加一条边
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    //获得指定顶点的临结表
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }


    //获得指定顶点的度数
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    //所有顶点的最大度数
    public int maxDegree() {
        int max = 0;
        for(int v=0; v<V(); v++) {
            if(degree(v) > max) {
                max = degree(v);
            }
        }
        return max;
    }

    //所有顶点的平均度数
    public double agvDegree() {
        return 2 * E() / V();
    }


    //图的自环的个数
    public double numberOfSelfLoops() {
        int count = 0;
        for(int v=0; v<V(); v++) {
            for(int w: adj(v)) {
                if(v==w) {
                    count++;
                }
            }
        }
        return count/2;
    }

    //图的字符串表示
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
