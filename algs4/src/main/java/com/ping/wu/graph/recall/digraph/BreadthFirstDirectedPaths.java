package com.ping.wu.graph.recall.digraph;

import com.ping.wu.common.In;
import com.ping.wu.common.PrintUtils;
import com.ping.wu.common.Queue;

import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class BreadthFirstDirectedPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstDirectedPaths(Digraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];

        for (int v = 0; v < g.V(); v++) {
            distTo[v] = INFINITY;
        }

        bfs(g, s);
    }

    public void bfs(Digraph g, int s) {
        marked[s] = true;
        distTo[s] = 0;
        Queue<Integer> queue = new Queue();
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v] != INFINITY;
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(x);
        return stack;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/digraph/txt/breadthFirstDirectedPaths.txt");
        BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(new Digraph(in), 0);
        System.out.println(paths.hasPathTo(4));
        System.out.println(paths.hasPathTo(5));
        PrintUtils.print(paths.pathTo(4));
    }
}
