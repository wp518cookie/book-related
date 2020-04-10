package com.ping.wu.graph.recall.digraph;

import com.ping.wu.common.In;
import com.ping.wu.common.PrintUtils;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class Topological {
    private Iterable<Integer> order;
    private int[] rank;

    public Topological(Digraph g) {
        rank = new int[g.V()];
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g);
        this.order = depthFirstOrder.reversePost();
        int i = 0;
        for (int t : order) {
            rank[t] = i++;
        }
    }

    public boolean hasOrder() {
        return order != null;
    }

    public int rank(int v) {
        return rank[v];
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/digraph/txt/depthFirstOrder.txt");
        Digraph g = new Digraph(in);
        Topological topological = new Topological(g);
        PrintUtils.print(topological.order());
        System.out.println(topological.rank(3));
    }
}
