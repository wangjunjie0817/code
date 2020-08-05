package com.wang.code.sort;

/**
 * @author WANGJJ
 * @date 2020/04/19
 */
public class Selection {

    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            int min = i;
            for (int j = i + 1; j < a.length; j++){
                if (less(a[j], a[min])){
                    min = j;
                }
            }
            exchange(a, i, min);
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
