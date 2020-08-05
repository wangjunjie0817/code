package com.wang.code.one;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/17
 */
public class Stack<T> implements Iterable<T>{

    private T[] ts;

    private int n;

    public Stack(int cap){
        ts = (T[]) new Object[cap];
    }

    public Boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public T pop(){
        T t = ts[--n];
        ts[n] = null;
        if (n > 0 && n < ts.length / 4) {
            resize(ts.length / 2);
        }
        return t;
    }

    public void push(T t){
        if (n == ts.length) {
            resize(2 * ts.length);
        }
        ts[n++] = t;
    }

    @Override
    public Iterator<T> iterator(){
        return new ReverseArrayIterator();
    }

    private void resize(int max) {
        T[] tsNew = (T[]) new Object[max];
        for (int i = 0; i < n; i++){
            tsNew[i] = ts[i];
        }
        ts = tsNew;
    }

    private class ReverseArrayIterator implements Iterator<T> {

        int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return ts[--i];
        }
    }


}
