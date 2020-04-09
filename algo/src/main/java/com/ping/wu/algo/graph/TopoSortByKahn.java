package com.ping.wu.algo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuping
 * @date 2019-11-13
 */

public class TopoSortByKahn {
    public static void mlain(String[] args) {
        new TopoSortByKahn().topoSortByKahn(generateGraph1());
    }

    public void topoSortByKahn(Graph g) {
        int[] inDegree = new int[g.v];
        for (int i = 0; i < g.v; i++) {
            for (int j = 0; j < g.adj[i].size(); j++) {
                inDegree[g.adj[i].get(j)]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < g.v; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            System.out.print("->" + i);
            for (int j = 0; j < g.adj[i].size(); j++) {
                int k = g.adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) {
                    queue.offer(k);
                }
            }
        }
    }

    private static Graph generateGraph1() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
//        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(2, 4);
//        graph.addEdge(1, 2);
        return graph;
    }
}
