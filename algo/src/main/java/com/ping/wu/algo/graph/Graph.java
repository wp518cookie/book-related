package com.ping.wu.algo.graph;

import java.util.LinkedList;

/**
 * @author wuping
 * @date 2019-11-13
 */

public class Graph {
    int v;
    LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
    }
}


