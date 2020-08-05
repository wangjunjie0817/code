package com.wang.code.one;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author WANGJJ
 * @date 2020/04/15
 */
public class BinarySearch {

    public static int rank(int key, int[] whiteList) {
        int lo = 0;
        int hi = whiteList.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (key < whiteList[mid]){
                hi = mid - 1;
            } else if (key > whiteList[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        int i = 1;
        while (20 != i++);
        System.out.println(i);

        System.out.println(2.5);
    }

}
