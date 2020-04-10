package com.ping.wu.graph.recall.graph;

import com.ping.wu.common.In;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;
    private int[] size;

    public CC(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        size = new int[g.V()];
        count = -1;

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                count++;
                dfs(g, i);
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public int size(int v) {
        return size[id[v]];
    }

    public int id(int v) {
        return id[v];
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count + 1;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/cc.txt");
        Graph g = new Graph(in);
        CC cc = new CC(g);
        System.out.println(cc.count());
        System.out.println(cc.size(0));
        System.out.println(cc.connected(3, 4));
        System.out.println(cc.connected(0, 4));
    }
}
