package com.wang.code.one;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author WANGJJ
 * @date 2020/04/19
 */
public class TripleSum {

    static Integer n;

    public static void main(String[] args) {
//        int[] ints = {3, 5, -8, 6, -11, 7};
//
//        calculateTripleSum(ints);

        int i = 0;
        while (i < 10) {
            System.out.println(randomInt(1000));
            i++;
        }

    }

    public static void calculateTripleSum (int[] integers) {
        int n = 0;
        Arrays.sort(integers);
        for (int i = 0; i < integers.length; i++){
            for (int j = i + 1; j < integers.length; j++) {
                if (BinarySearch.rank((- (integers[i] + integers[j])), integers) > j) {
                    n++;
                };
            }
        }
        System.out.println(n);
    }

    private static int randomInt(int length){
        if (n == null) {
            n = length;
        }
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = i;
        }
        Random random = new Random();
        int randomInt = random.nextInt(length);
        int a = ints[n-1];
        ints[n-1] = ints[randomInt];
        ints[randomInt] = a;
        int b = ints[n-1];
        n--;
        return b;

    }

}
