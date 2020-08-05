package com.wang.code.one;

/**
 * @author WANGJJ
 * @date 2020/04/18
 */
public class LinkListStack<T> {

    private Node first;
    private int n;

    private class Node {
        private T t;
        private Node next;
    }

    public void push(T t) {
        Node oldNode = first;
        first = new Node();
        first.t = t;
        first.next = oldNode;
        n++;
    }

    public T pop() {
        T t = first.t;
        first = first.next;
        n--;
        return t;

    }

}
