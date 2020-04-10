package com.ping.wu.graph.recall.graph;

import com.ping.wu.common.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-09
 */

public class DepthGraphPaths {
    private int[] edgeTo;
    private boolean[] marked;
    private final int s;

    public DepthGraphPaths(Graph g, int s) {
        this.s = s;
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        for (int w : g.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/depthFirstPaths.txt");
        Graph g = new Graph(in);
        DepthGraphPaths paths = new DepthGraphPaths(g, 0);
        Iterator<Integer> it = paths.pathTo(2).iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " -> ");
        }
    }
}
