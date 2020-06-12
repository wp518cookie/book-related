package com.ping.wu.graph.recall.ewg;

import java.util.NoSuchElementException;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class Edge implements Comparable<Edge> {
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int t) {
        if (t == w) {
            return v;
        } else if (t == v) {
            return w;
        } else {
            throw new NoSuchElementException();
        }
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
