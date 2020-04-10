package com.ping.wu.graph.recall.digraph;

import com.ping.wu.common.In;
import com.ping.wu.common.PrintUtils;
import com.ping.wu.common.Queue;

import java.util.Stack;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class DepthFirstOrder {
    private boolean[] marked;
    private int[] post;
    private int[] pre;
    private Queue<Integer> postOrder;
    private Queue<Integer> preOrder;
    private int preCounter;
    private int postCounter;

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        post = new int[g.V()];
        pre = new int[g.V()];
        postOrder = new Queue();
        preOrder = new Queue();

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        preOrder.enqueue(v);
        pre[v] = preCounter++;

        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        postOrder.enqueue(v);
        post[v] = postCounter++;
    }

    public Iterable<Integer> post() {
        return postOrder;
    }

    public Iterable<Integer> pre() {
        return preOrder;
    }

    public int post(int v) {
        return post[v];
    }

    public int pre(int v) {
        return pre[v];
    }

    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack();
        for (int v : postOrder) {
            reverse.push(v);
        }
        return reverse;
    }

    public static void main(String[] args) {
        In in = new In("/Users/wp/Documents/code/self/study/fighting/algs4/src/main/java/com/ping/wu/graph/recall/digraph/txt/depthFirstOrder.txt");
        Digraph digraph = new Digraph(in);
        DepthFirstOrder order = new DepthFirstOrder(digraph);
        PrintUtils.print(order.pre());
        System.out.println();
        PrintUtils.print(order.post());
    }
}
