package com.wang.code.one;

/**
 * @author WANGJJ
 * @date 2020/04/23
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Bag<Item> implements Iterable<Item> {
    private int N;
    private Node<Item> first;
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    public Bag() {
        first = null;
        N = 0;
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return N;
    }
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N ++;
    }
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        @Override
        public boolean hasNext() { return current != null; }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }

        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i=0;i<n;i++) {
            String item = in.next();
            bag.add(item);
        }
        System.out.println("size of bag = " + bag.size());
        for(String s : bag) {
            System.out.println(s);
        }
        for (String s : bag) {
            System.out.println(s);
        }
    }
}
