package com.ping.wu.graph.recall.graph;

import com.ping.wu.common.In;

import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class Bipartite {
    private boolean isBitpartite;
    private boolean[] color;
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Bipartite(Graph g) {
        isBitpartite = true;
        color = new boolean[g.V()];
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                color[w] = !color[v];
                edgeTo[w] = v;
            } else if (color[v] == color[w]) {
                isBitpartite = false;
                cycle = new Stack();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isBitpartite() {
        return isBitpartite;
    }

    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/bipartite.txt");
        Graph g = new Graph(in);
        Bipartite bipartite = new Bipartite(g);
        System.out.println(bipartite.isBitpartite());
    }
}
