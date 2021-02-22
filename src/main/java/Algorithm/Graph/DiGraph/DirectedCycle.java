package Questions.Graph.DiGraph;

import Lib.StdOut;

import java.util.Stack;

/**
 * Created by Defias on 2020/06.
 * Description: 有向环检查（查找有向环）
 *
 */

public class DirectedCycle {

    private boolean[] marked;        // 顶点v是否已标记
    private int[] edgeTo;            // 从起点到该顶点的已知路径上的最后一个顶点
    private boolean[] onStack;       // 递归调用的栈上的所有顶点
    private Stack<Integer> cycle;    // 有向环中的所有顶点（如果存在）


    public static void main(String[] args) {
        Digraph G = Digraph.mockDigraph();
        DirectedCycle finder = new DirectedCycle(G);
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


    public DirectedCycle(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null)
                dfs(G, v);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }


    //G中是否含有有向环
    public boolean hasCycle() {
        return cycle != null;
    }


    // 有向环中的所有顶点（如果存在）
    public Iterable<Integer> cycle() {
        return cycle;
    }


    //校验：certify that digraph has a directed cycle if it reports one
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1)
                    first = v;
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
