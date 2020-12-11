package Questions.Graph.DiGraph;

import Questions.Set.Bag;

/**
 * Created by Defias on 2020/06.
 * Description: DFS -  单点和多点的可达性
 */
public class DirectedDFS {
    private boolean[] marked;  // 顶点v是否可达（是否存在从单源点或多源点到v的有向路径）
    private int count;         // 可达的顶点数


    public static void main(String[] args) {

        Digraph G = Digraph.mockDigraph();

        Bag<Integer> sources = new Bag<Integer>();
        sources.add(0);
        sources.add(1);

        DirectedDFS dfs = new DirectedDFS(G, sources);

        //打印图中所有可达的顶点
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v))
                System.out.print(v + " ");
        }
        System.out.println();
    }

    //在G中找到从s可达的所有顶点
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    //在G中找到从sources的所有顶点可达的所有顶点
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }


    //v是可达的吗
    public boolean marked(int v) {
        return marked[v];
    }

    // 可达的顶点数
    public int count() {
        return count;
    }
}
