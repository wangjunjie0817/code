package com.wang.code.sort;

import sun.security.x509.CertificatePolicyMap;

import java.util.Arrays;

/**
 * @author WANGJJ
 * @date 2020/04/20
 */
public class Quick {
    public static void main(String[] args) {
        String[] arr = {"2", "3", "1", "0", "2", "5", "3"};

        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) <= 0;

    }

    public static void quickSort(String[] arr,int low,int high) {

        int i,j;

        if(low >= high) {
            return;
        }
        //p就是基准数,这里就是每个数组的第一个
        String temp;
        String p = arr[low];
        i = low;
        j = high;
        while(i < j) {
            //右边当发现小于p的值时停止循环
            while(less(p, arr[j]) && i < j) {
                j--;
            }

            //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）

            //左边当发现大于p的值时停止循环
            while(less(arr[i], p) && i < j) {
                i++;
            }

            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[low] = arr[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[i] = p;
        quickSort(arr,low,i-1);  //对左边快排
        quickSort(arr,i+1,high); //对右边快排

    }
}
