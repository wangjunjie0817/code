package com.wang.code.sort;

/**
 * @author WANGJJ
 * @date 2020/04/20
 */
public class Merge {

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi){

        int i = lo;
        int j = mid + 1;
        Comparable[] aux = new Comparable[a.length];
        for (int k = lo; k <= hi; k++ ){
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid){a[k] = aux[j++];}
            else if (j > hi) {a[k] = aux[i++];}
            else if (less(a[i], a[j])){a[k] = aux[i++];}
            else {a[k] = aux[j++];}
        }
    }

    private static boolean less(Comparable a, Comparable b){
        System.out.println(a);
        return a.compareTo(b) < 0;

    }

    public static void exchange(Comparable[] a, int i, int j){
        Comparable tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    private static void show(Comparable[] comparables){
        for (Comparable comparable : comparables) {
            System.out.println(comparable);
        }
    }

    private static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])){
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        String[] strings = {"wang", "jun", "jie", "la", "ji"};
        sort(strings, 0, strings.length - 1);
        System.out.println(isSorted(strings));
        show(strings);


    }

}
