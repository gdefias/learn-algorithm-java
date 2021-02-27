package Algorithm.Graph;
import Lib.StdOut;

/**
 * Created by Defias on 2020/06.
 * Description: DFS 图的深度优先搜索  单点连通性
 *
 */

public class DepthFirstSearch {
    private boolean[] marked;    // 记录和起点s连通的所有顶点
    private int count;           // 与起点s连通的顶点总数


    public static void main(String[] args) {
        Graph G = Graph.mockGraph();
        int s = 0;  //起点

        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count() != G.V())
            StdOut.println("NOT connected");
        else
            StdOut.println("connected");  //连通图
    }

    //找到和起点s连通的所有顶点
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }


    //从顶点v开始进行深度优先搜索
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;   //标记为已访问
        for (int w : G.adj(v)) {    //递归的访问它的所有没有被标记过的邻居顶点
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }


    //顶点v和s是否连通
    public boolean marked(int v) {
        return marked[v];
    }


    //与起点s连通的顶点总数
    public int count() {
        return count;
    }
}
