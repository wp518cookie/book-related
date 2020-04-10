package com.ping.wu.graph.recall.graph;

import com.ping.wu.common.In;
import com.ping.wu.graph.c4_1.ST;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Graph graph;

    public SymbolGraph(String filename, String delimiter) {
        st = new ST();

        In in = new In(filename);
        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String t) {
        return st.contains(t);
    }

    public Graph graph() {
        return graph;
    }

    public int indexOf(String key) {
        return st.get(key);
    }

    public String nameOf(int idx) {
        return keys[idx];
    }
}
