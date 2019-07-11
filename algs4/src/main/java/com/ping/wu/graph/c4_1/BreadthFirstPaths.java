package com.ping.wu.graph.c4_1;

import com.ping.wu.common.In;
import com.ping.wu.common.Queue;
import com.ping.wu.common.StdOut;

import java.io.File;
import java.util.Stack;

/**
 * @author wuping
 * @date 2019-06-24
 */

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
        assert check(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException();
        }
    }

    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            validateVertex(v);
        }
    }

    private boolean check(Graph G, int s) {
        if (distTo[s] != 0) {
            StdOut.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    StdOut.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("distTo[" + v + "] = " + distTo[v]);
                    StdOut.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }
        for (int w = 0; w < G.V(); w++) {
           if (!hasPathTo(w) || w == s) {
               continue;
           }
           int v = edgeTo[w];
           if (distTo[w] != distTo[v] + 1) {
               StdOut.println("shortest path edge " + v + "-" + w);
               StdOut.println("distTo[" + v + "] = " + distTo[v]);
               StdOut.println("distTo[" + w + "] = " + distTo[w]);
               return false;
           }
        }
        return true;
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

    public static void main(String[] args) {
        In in = new In(new File("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/test.txt"));
        Graph G = new Graph(in);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                boolean first = true;
                StdOut.printf("%d to %d (%d):  ", 0, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == 0) {
                        if (first) {
                            StdOut.print(x);
                            first = false;
                        } else {
                            StdOut.print("-" + x);
                        }
                    } else {
                        if (first) {
                            StdOut.print(x);
                            first = false;
                        } else {
                            StdOut.print("-" + x);
                        }
                    }
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d (-):  not connected\n", 0, v);
            }
        }
    }
}
