package com.ping.wu.graph.recall.graph;

import com.ping.wu.common.In;

import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-09
 */

public class Cycle {
    private Stack<Integer> cycle;
    private int[] edgeTo;
    private boolean[] marked;

    public Cycle(Graph g) {
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, -1, v);
            }
        }
    }

    private boolean hasSelfLoop(Graph g) {
        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v)) {
                if (w == v) {
                    cycle = new Stack();
                    cycle.push(w);
                    cycle.push(w);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasParalleleEdges(Graph g) {
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v)) {
                if (marked[w]) {
                    cycle = new Stack();
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked[w] = true;
            }
            for (int w = 0; w < g.V(); w++) {
                marked[w] = false;
            }
        }
        return false;
    }

    private void dfs(Graph g, int u, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, v, w);
            } else if (u != w) {
                cycle = new Stack();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/cycle.txt");
        Graph g = new Graph(in);
        Cycle finder = new Cycle(g);
        System.out.println(finder.hasCycle());
        for (int t : finder.cycle) {
            System.out.print(" " + t);
        }
    }
}
