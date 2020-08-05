package com.wang.code.sort.top;

import com.wang.code.one.Stack;

/**
 * @author WANGJJ
 * @date 2020/04/20
 */
public class TopMin {

    public static void main(String[] args) {
        MinPQ<Integer> topMax = new MinPQ<>(10);
        topMax.insert(10);
        topMax.insert(11);
        topMax.insert(9);
        topMax.insert(15);

        Stack<Integer> stack = new Stack<>(20);
        while (!topMax.isEmpty()){
            Integer a = topMax.delMin();
            stack.push(a);
        }
        for (int i: stack){
            System.out.println(i);
        }

    }

}
