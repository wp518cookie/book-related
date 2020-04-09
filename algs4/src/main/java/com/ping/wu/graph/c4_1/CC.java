package com.ping.wu.graph.c4_1;

import com.ping.wu.common.In;
import com.ping.wu.common.Queue;
import com.ping.wu.common.StdOut;

/**
 * @author wuping
 * @date 2019-06-24
 * connected components
 */

public class CC {
    /**
     * marked[v] = has vertex v been marked?
     */
    private boolean[] marked;
    /**
     * id[v] = id of connected component containing v
     */
    private int[] id;
    /**
     * size[id] = number of vertices in given component
     */
    private int[] size;
    /**
     * number of connected components
     */
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    public int size(int v) {
        validateVertex(v);
        return size[v];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length) {
            throw new IllegalArgumentException();
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/test1.txt");
        Graph G = new Graph(in);
        CC cc = new CC(G);
        int m = cc.count();
        StdOut.println(m + " components");
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue();
        }
        for (int i = 0; i < G.V(); i++) {
            components[cc.id(i)].enqueue(i);
        }
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
