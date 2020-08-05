package com.wang.code.graphAlgorithm;

import java.util.Map;

/**
 * @author WANGJJ
 * @date 2020/05/11
 */
public class ListSum {

    public static Integer listSum(Integer[] integers){
        if (integers.length == 0){
            return 0;
        }
        if (integers.length == 1){
            return integers[0];
        }
        Integer[] integers1 = new Integer[integers.length - 1];
        for (int i = 1; i < integers.length; i++){
            integers1[i-1] = integers[i];
        }
        return Math.max(integers[0] , listSum(integers1));
    }

    public static void main(String[] args) {
        Integer[] integers = {1, 3, 6, 7};
        System.out.println(listSum(integers));
    }

}
