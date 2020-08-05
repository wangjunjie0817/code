package com.wang.code.sort;

/**
 * @author WANGJJ
 * @date 2020/04/19
 */
public class ShellSort {
    public static void sort(Comparable[] a){
        int step = a.length / 2;
        while (step > 0){
            for (int i = 0; i < a.length; i++) {
                for (int j = i; j >= step && less(a[j], a[j - step]); j -= step){
                    exchange(a, j, j-step);
                }
            }
            step /= 2;
        }
    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;

    }

    public static void exchange(Comparable[] a, int i, int j){
        Comparable tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static void show(Comparable[] comparables){
        for (Comparable comparable : comparables) {
            System.out.println(comparable);
        }
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])){
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        String[] strings = {"wang", "jun", "jie"};
        sort(strings);
        System.out.println(isSorted(strings));
        show(strings);


    }


}
