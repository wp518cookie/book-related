package com.ping.wu.graph.recall.digraph;

import com.ping.wu.common.In;
import com.ping.wu.common.PrintUtils;

import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class DepthFirstDirectedPaths {
    private int[] edgeTo;
    private boolean[] marked;
    private int s;

    public DepthFirstDirectedPaths(Digraph g, int s) {
        this.edgeTo = new int[g.V()];
        this.marked = new boolean[g.V()];
        this.s = s;
        dfs(g, s);
    }

    public void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
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
        Stack stack = new Stack();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/graph/txt/depthFirstDirectedPaths.txt"));
        DepthFirstDirectedPaths directedPaths = new DepthFirstDirectedPaths(digraph, 0);
        System.out.println(directedPaths.hasPathTo(3));
        System.out.println(directedPaths.hasPathTo(4));
        PrintUtils.print(directedPaths.pathTo(3));
    }
}
