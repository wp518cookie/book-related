package com.ping.wu.graph.c4_1;

import com.ping.wu.common.In;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author wuping
 * @date 2019-06-20
 */

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;    // number of vertices
    private int E;          // number of edges
    private Bag<Integer>[] adj; // adjacent list

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Graph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of verices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag();
        }
    }

    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) {
                throw new IllegalArgumentException();
            }
            adj = new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag();
            }
            int E = in.readInt();
            if (E < 0) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> reverse = new Stack();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
