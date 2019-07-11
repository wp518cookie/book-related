package com.ping.wu.graph.c4_1;

import com.ping.wu.common.In;
import com.ping.wu.common.StdOut;

/**
 * @author wuping
 * @date 2019-06-21
 */

public class DepthFirstSearch {
    /**
     * marked[v] = is there an s-v path
     */
    private boolean[] marked;
    /**
     * number of vertices connected to s
     */
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int count() {
        return count;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public static void main(String[] args) {
        In in = new In();
        Graph G = new Graph(in);
        DepthFirstSearch search = new DepthFirstSearch(G, 1);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
        if (search.count() != G.V()) {
            StdOut.println("not connected");
        } else {
            StdOut.println("connected");
        }
    }
}
