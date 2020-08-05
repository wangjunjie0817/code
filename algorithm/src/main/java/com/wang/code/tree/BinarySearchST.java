package com.wang.code.tree;

/**
 * @author WANGJJ
 * @date 2020/04/21
 * 二分查找树
 */
public class BinarySearchST<T extends Comparable, V> {


    private T[] keys;
    private V[] vals;
    private int n;

    public BinarySearchST(int capacity){
        keys = (T[]) new Comparable[capacity];
        vals = (V[]) new Comparable[capacity];
    }

    public V get(T key){
        if (null == key) {
            return null;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0){
            return vals[i];
        }
        return null;
    }

    public void put(T key, V val){
        if (null == key || null == val){
            return;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0){
            vals[i] = val;
            return;
        }
        for (int j = n; j > i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public int rank(T key){
        int lo = 0;
        int hi = keys.length - 1;
        while (lo < hi){
            int mid = (lo + hi) / 2;

            if (keys[mid] != null && key.compareTo(keys[mid]) == 0){
                return mid;
            } else if (keys[mid] != null && key.compareTo(keys[mid]) > 0){
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>(10);
        System.out.println(binarySearchST.get("wang"));
        binarySearchST.put("wang", 18);
        System.out.println(binarySearchST.get("wang"));
    }
}
