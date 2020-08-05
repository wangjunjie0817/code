package com.wang.code.sort.top;

/**
 * @author WANGJJ
 * @date 2020/04/28
 */
public class MinPQ<T extends Comparable<T>> {

    private T[] pq;
    private int n = 0;

    public MinPQ(int max) {
        pq = (T[]) new Comparable[max+1];
    }

    public void insert(T t){
        pq[++n] = t;
        swim(n);
    }

    public T delMin(){
        T max = pq[1];
        pq[1] = pq[n--];
        pq[n+1] = null;
        sink(1);
        return max;
    }

    public void swim(int i){
        while (i > 1 && more(i / 2, i)){
            exchange(i / 2, i);
            i /= 2;
        }
    }

    public void sink(int i){
        while (2 * i <= n){
            int j = 2 * i;
            if (j < n){
                if (more(j, j + 1)) {
                    j++;
                }
            }
            if (!more(i, j)){
                break;
            }
            exchange(i, j);
            i = j;
        }
    }

    public int getN(){
        return n;
    }

    public boolean isEmpty(){
        return n <= 0;
    }

    public boolean more(int i, int j){
        return pq[i].compareTo(pq[j]) > 0;
    }

    public void exchange(int i, int j){
        T tmp = pq[j];
        pq[j] = pq[i];
        pq[i] = tmp;
    }

}
