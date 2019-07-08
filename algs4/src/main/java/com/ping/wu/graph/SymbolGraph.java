package com.ping.wu.graph;

import com.ping.wu.common.In;

import java.util.Iterator;

/**
 * @author wuping
 * @date 2019-06-25
 */

public class SymbolGraph {
    // string -> index
    private ST<String, Integer> st;
    // index -> string
    private String[] keys;
    // 正常的图
    private Graph graph;

    public SymbolGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];
        for (String key : st.keys()) {
            keys[st.get(key)] = key;
        }
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

    public boolean contains(String s) {
        return st.contains(s);
    }

    @Deprecated
    public int index(String s) {
        return st.get(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    @Deprecated
    public String name(int v) {
        validateVertex(v);
        return keys[v];
    }

    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    @Deprecated
    public Graph G() {
        return graph;
    }

    public Graph graph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }
}
