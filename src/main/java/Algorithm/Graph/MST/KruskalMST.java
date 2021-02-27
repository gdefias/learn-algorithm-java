package Algorithm.Graph.MST;

import Algorithm.Graph.Edge;
import Algorithm.Graph.EdgeWeightedGraph;
import Algorithm.Queue.MyPriorityQueue2;
import Algorithm.Queue.MyQueue2;
import Algorithm.Set.UF_QuickUnion;
import Lib.In;
import Lib.StdOut;

/**
 * Created by Defias on 2020/06.
 * Description: 加权无向图的最小生成树 - Kruskal算法  O(ElgE)
 */


public class KruskalMST {

    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;                        // weight of MST
    private MyQueue2<Edge> mst = new MyQueue2<Edge>();  // edges in MST


    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }

    public KruskalMST(EdgeWeightedGraph G) {
        // more efficient to build heap by passing array of edges
        MyPriorityQueue2<Edge> pq = new MyPriorityQueue2<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF_QuickUnion uf = new UF_QuickUnion(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }

        // check optimality conditions
        assert check(G);
    }


    //Returns the edges in a minimum spanning tree (or forest)
    public Iterable<Edge> edges() {
        return mst;
    }


    //Returns the sum of the edge weights in a minimum spanning tree (or forest)
    public double weight() {
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
            return false;
        }

        // check that it is acyclic
        UF_QuickUnion uf = new UF_QuickUnion(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF_QuickUnion(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}
