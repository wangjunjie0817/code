package com.wang.code.one;


import java.util.Stack;

/**
 * @author WANGJJ
 * @date 2020/04/18
 * 类说明：打印N的二进制表示
 */
public class PrintBinaryRepresentationOfN {

    public static void main(String[] args) {
        printBinaryRepresentationOfN(9);
    }

    public static void printBinaryRepresentationOfN(int n){
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            stack.push(n % 2);
            n /= 2;
        }
        for (Integer i : stack) {
            System.out.print(i);
        }

    }

}
