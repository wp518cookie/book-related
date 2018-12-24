package com.ping.wu.common;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author wuping
 * @date 2018/12/24
 */

public class Queue<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int n;

    private static class Node<E> {
        private E item;
        private Node<E> next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    public void enqueue(E item) {
        Node<E> oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        E item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E item : this) {
            sb.append(item);
            sb.append(' ');
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>(first);
    }

    private class ListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E item = current.item;
            current = current.next;
            return item;
        }
    }
}
