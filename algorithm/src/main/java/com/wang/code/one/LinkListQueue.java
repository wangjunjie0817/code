package com.wang.code.one;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/18
 */
public class LinkListQueue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int n;

    private Node max = first;

    private class Node {
        private T t;
        private Node next;
    }

    public Boolean isEmpty(){
        return first == null;
    }

    public Node getFirst(){
        return first;
    }

    public T max(Node n) {
        if (!(n.t instanceof Integer)) {
            return max.t;
        }
        if (n.next == null) {
            return max.t;
        }
        if ((Integer)n.t < (Integer) n.next.t){
            max = n.next;
        }
        return max(n.next);
    }

    public void enqueue(T t) {
        Node oldLast = last;
        last = new Node();
        last.t = t;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;

    }

    public T dequeue() {
        Node oldFirst = first;
        first = oldFirst.next;
        if (isEmpty()){
            last = null;
        }

        n--;
        return oldFirst.t;

    }

    public class QueueIterator implements Iterator<T>{

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.t;
            current = current.next;
            return t;
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

}
