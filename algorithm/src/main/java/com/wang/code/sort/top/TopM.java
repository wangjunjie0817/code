package com.wang.code.sort.top;

import com.wang.code.one.Stack;

/**
 * @author WANGJJ
 * @date 2020/04/20
 */
public class TopM {

    public static void main(String[] args) {
        MaxPQ<Integer> topMax = new MaxPQ<>(10);
        topMax.insert(10);
        topMax.insert(11);
        topMax.insert(9);
        topMax.insert(15);

        Stack<Integer> stack = new Stack<>(20);
        while (!topMax.isEmpty()){
            Integer a = topMax.delMax();
            stack.push(a);
        }
        for (int i: stack){
            System.out.println(i);
        }

    }

}
