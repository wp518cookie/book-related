package com.ping.wu.graph.recall.graph;

import com.ping.wu.common.In;

/**
 * @author wuping
 * @date 2020-04-09
 */

public class DepthFirstSearch {
    private int count;
    private boolean[] marked;

    public DepthFirstSearch(Graph g, int s) {
        count = 0;
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        count++;
        for (int v : g.adj(s)) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/depthFirstSearch.txt");
        Graph g = new Graph(in);
//        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        DepthFirstSearch search = new DepthFirstSearch(g, 1);
        System.out.println(search.count());
    }
}
