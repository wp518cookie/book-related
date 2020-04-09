package com.ping.wu.graph.c4_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wuping
 * @date 2019-07-18
 */

public class BreadthFirstDirectedPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(s);
        bfs(G, s);
    }

    public int distTo(int v) {
        return distTo[v];
    }

    private void bfs(Digraph G, int s) {
        Queue<Integer> queue = new LinkedList();
        queue.offer(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int i : G.adj(w)) {
                if (!marked[i]) {
                    edgeTo[i] = w;
                    distTo[i] = distTo[w] + 1;
                    marked[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack();
        int t;
        for (t = edgeTo[v]; distTo[t] == 0; t = edgeTo[t]) {
            stack.push(t);
        }
        stack.push(t);
        return stack;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException();
        }
    }
}
