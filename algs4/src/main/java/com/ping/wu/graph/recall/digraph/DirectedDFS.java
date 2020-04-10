package com.ping.wu.graph.recall.digraph;

import com.ping.wu.common.In;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class DirectedDFS {
    private boolean[] marked;
    private int count;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    public void dfs(Digraph g, int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/directedDFS.txt");
        DirectedDFS dfs = new DirectedDFS(new Digraph(in), 0);
        System.out.println(dfs.count());
    }
}
