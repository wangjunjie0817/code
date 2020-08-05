package com.wang.code.book.capture3;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WANGJJ
 * @date 2020/05/05
 */
public class FinalDemo {

    private final Set<Integer> set = new HashSet<>();
    private final Integer i = 3;

    public FinalDemo(){
        set.add(1);
        set.add(2);
        set.add(3);
    }

    public void doSome(){
        set.add(4);
        for (int i: set){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        FinalDemo finalDemo = new FinalDemo();
        finalDemo.doSome();
    }
}
