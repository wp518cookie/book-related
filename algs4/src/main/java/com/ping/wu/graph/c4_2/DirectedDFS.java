package com.ping.wu.graph.c4_2;

import com.ping.wu.common.In;

/**
 * @author wuping
 * @date 2019-07-11
 */

public class DirectedDFS {
    private boolean[] marked;
    private int count;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    private void dfs(Digraph G, int s) {
        marked[s] = true;
        count++;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                dfs(G, v);
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
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/c4_2/tinyDG.txt");
        Digraph G = new Digraph(in);
        DirectedDFS directedDFS = new DirectedDFS(G, 3);
        System.out.println(directedDFS.marked(0));
    }
}
