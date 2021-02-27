package Algorithm.Graph.DiGraph;

import Lib.StdOut;
import Algorithm.Queue.MyQueue2;
import java.util.Stack;

/**
 * Created by Defias on 2020/06.
 * Description:  有向环检查（查找有向环）  -  非递归版本
 */

public class DirectedCycleX {

    private Stack<Integer> cycle;     // 有向环中的所有顶点（如果存在）

    public static void main(String[] args) {

        Digraph G = Digraph.mockDigraph();  //也可用DigraphGenerator图生成器

        DirectedCycleX finder = new DirectedCycleX(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }


    public DirectedCycleX(Digraph G) {

        // indegrees of remaining vertices
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }

        // initialize queue to contain all vertices with indegree = 0
        MyQueue2<Integer> queue = new MyQueue2<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        while(!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0) queue.enqueue(w);
            }
        }

        // there is a directed cycle in subgraph of vertices with indegree >= 1.
        int[] edgeTo = new int[G.V()];
        int root = -1;  // any vertex with indegree >= -1
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0) continue;
            else root = v;
            for (int w : G.adj(v)) {
                if (indegree[w] > 0) {
                    edgeTo[w] = v;
                }
            }
        }

        if (root != -1) {

            // find any vertex on cycle
            boolean[] visited = new boolean[G.V()];
            while (!visited[root]) {
                visited[root] = true;
                root = edgeTo[root];
            }

            // extract cycle
            cycle = new Stack<Integer>();
            int v = root;
            do {
                cycle.push(v);
                v = edgeTo[v];
            } while (v != root);
            cycle.push(root);
        }

        assert check();
    }


    // 有向环中的所有顶点（如果存在）
    public Iterable<Integer> cycle() {
        return cycle;
    }


    //G中是否含有有向环
    public boolean hasCycle() {
        return cycle != null;
    }

    //校验： certify that digraph has a directed cycle if it reports one
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }


        return true;
    }
}
