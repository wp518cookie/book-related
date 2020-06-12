package com.ping.wu.graph.recall.ewg;

import com.ping.wu.common.In;
import com.ping.wu.common.Queue;
import com.ping.wu.common.StdOut;
import com.ping.wu.sorting.IndexMinPQ;

/**
 * @author wuping
 * @date 2020-04-11
 */

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph g) {
        edgeTo = new Edge[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
        pq = new IndexMinPQ<>(g.V());
        for (int i = 0; i < g.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                prim(g, v);
            }
        }
    }

    private void prim(EdgeWeightedGraph g, int v) {
        distTo[v] = 0;
        pq.insert(v, distTo[v]);
        while (!pq.isEmpty()) {
            int w = pq.delMin();
            scan(g, w);
        }
    }

    private void scan(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;
            } if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.decreaseKey(w, e.weight());
                } else {
                    pq.insert(w, e.weight());
                }
            }
        }
    }

    // 要考虑到没有联通的部分
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }

    public Iterable<Edge> edges() {
        Queue<Edge> edges = new Queue();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                edges.enqueue(e);
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/ewg/txt/mediumEWG.txt");
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
