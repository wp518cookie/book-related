package com.ping.wu.graph.c4_2;

import com.ping.wu.common.In;

import java.util.Stack;

/**
 * @author wuping
 * @date 2019-07-11
 */

public class DepthFirstDirectedPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Digraph G, int s) {
        marked[s] = true;
        for (int v : G.adj(s)) {
            if (!marked[v]) {
                edgeTo[v] = s;
                dfs(G, v);
            }
        }
    }

    public Iterable<Integer> pathTo(int t) {
        if (!hashPathTo(t)) {
            return null;
        }
        Stack<Integer> stack = new Stack();
        for (int v = t; v != s; v = edgeTo[v]) {
            stack.add(v);
        }
        stack.add(s);
        return stack;
    }

    public boolean hashPathTo(int t) {
        return marked[t];
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/c4_2/DepthFirstDirectedPaths.txt");
        Digraph digraph = new Digraph(in);
        DepthFirstDirectedPaths depthFirstDirectedPaths = new DepthFirstDirectedPaths(digraph, 1);
        for (int i : depthFirstDirectedPaths.pathTo(4)) {
            System.out.print(i + " ");
        }
    }
}
