package com.wang.code.one;


import java.util.*;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WANGJJ
 * @date 2020/04/15cd
 */
public class test {

    public static void main(String[] args) {

        LinkListQueue<Integer> linkListQueue = new LinkListQueue<>();
        linkListQueue.enqueue(4);
        linkListQueue.enqueue(3);
        linkListQueue.enqueue(5);
        linkListQueue.enqueue(2);
        System.out.println(linkListQueue.max(linkListQueue.getFirst()));
        String a = "wangjunjie";
        String[] strings = a.split("");
        for (String s: strings) {
            System.out.println(s);
        }

    }

    public boolean isValid(String s) {
        String[] strings = s.split("");
        Stack<String> stack = new Stack<String>();
        Map<String, String> hashMap = new HashMap();
        hashMap.put(")", "(");
        hashMap.put("]", "[");
        hashMap.put("}", "{");
        for(String symbol:strings) {
            if ("(".equals(symbol) || "[".equals(symbol) || "{".equals(symbol)){
                stack.push(symbol);
            } else if (
                    ")".equals(symbol) || "]".equals(symbol) || "}".equals(symbol)
            ) {
                if(!stack.pop().equals(hashMap.get(symbol))){
                    stack.push(symbol);
                }
            }
        }
        return stack.isEmpty();
    }


}
