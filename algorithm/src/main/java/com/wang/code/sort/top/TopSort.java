package com.wang.code.sort.top;

/**
 * @author WANGJJ
 * @date 2020/04/20
 */
public class TopSort {

    public static void sort(Comparable[] a){
        int n = a.length;
        for (int i = n / 2; i >= 1; i--){
            heapAdjust(a, i, n);
        }
        for (int i = n; i > 1; i--){
            exchange(a, 1, i);
            heapAdjust(a, 1, i);
        }

    }

    public static void heapAdjust(Comparable[] arr, int i, int n){
        int leftChild;
        for (int j = i; j < n; i = leftChild){
            leftChild = leftChild(i);
            if (leftChild + 1 <= n && less(arr, leftChild, leftChild++)){
                j++;
            }
            exchange(arr, i, j);
        }
    }

    public static int leftChild(int i){
        return i * 2;
    }

    public static void exchange(Comparable[] a, int i, int j){
        Comparable tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static boolean less(Comparable[] a, int i, int j){
        return a[i].compareTo(a[j]) < 0;
    }

    public static void main(String[] args) {
        Integer[] integers = {0, 9, 12, 14, 5, 8, 17, 3};
        TopSort.sort(integers);
        for (Integer a : integers) {
            System.out.println(a);
        }
    }

}
