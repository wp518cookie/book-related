package com.ping.wu.graph.recall.digraph;

import com.ping.wu.common.In;
import com.ping.wu.common.PrintUtils;

import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private Stack<Integer> cycle;
    private int[] edgeTo;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/digraph/txt/directedCycle.txt");
        Digraph digraph = new Digraph(in);
        DirectedCycle cycle = new DirectedCycle(digraph);
        System.out.println(cycle.hasCycle());
        PrintUtils.print(cycle.cycle());
    }
}
