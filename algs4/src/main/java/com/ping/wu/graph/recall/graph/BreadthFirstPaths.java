package com.ping.wu.graph.recall.graph;

import com.ping.wu.common.In;
import com.ping.wu.common.Queue;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-09
 */

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        distTo = new int[g.V()];
        edgeTo = new int[g.V()];

        for (int i = 0; i < g.V(); i++) {
            distTo[i] = INFINITY;
        }
        bfs(g, s);
    }

    private void bfs(Graph g, int s) {
        marked[s] = true;
        distTo[s] = 0;
        Queue<Integer> queue = new Queue();
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
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
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(x);
        return stack;
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/breadthFirstPaths.txt");
        Graph g = new Graph(in);
        BreadthFirstPaths paths = new BreadthFirstPaths(g, 0);
        System.out.println(paths.hasPathTo(10));
        System.out.println(paths.distTo(10));
        Iterator<Integer> it = paths.pathTo(10).iterator();
        while (it.hasNext()) {
            System.out.print(" " + it.next());
        }
    }
}
