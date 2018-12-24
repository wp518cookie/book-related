package com.ping.wu.graph;

/**
 * @author wuping
 * @date 2018/12/24
 */

public class DepthFirstSearch {
    private boolean[] marked;
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
        Graph G = new Graph(5);
        G.addEdge(0, 2);
        G.addEdge(0, 1);
        G.addEdge(3, 4);
        DepthFirstSearch search = new DepthFirstSearch(G, 3);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
        if (search.count() != G.V()) {
            System.out.println("NOT connected");
        } else {
            System.out.println("connected");
        }
    }
}
