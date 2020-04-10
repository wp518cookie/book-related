package com.ping.wu.graph.recall.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author wuping
 * @date 2020-04-09
 */

public class Bag<T> implements Iterable<T> {
    private int n;
    private Node<T> first;

    private static class Node<T> {
        private T val;
        private Node<T> next;
    }

    public int size() {
        return n;
    }

    public void add(T item) {
        Node oldFirst = first;
        Node<T> newFirst = new Node();
        first = newFirst;
        newFirst.val = item;
        newFirst.next = oldFirst;
        n++;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> temp = current;
            current = current.next;
            return temp.val;
        }
    }
}
