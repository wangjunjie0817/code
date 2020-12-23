package com.wang.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ThreadLocalDemo {

    static Map<Thread, ThreadLocal> map = new HashMap<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    TestClass t = new TestClass(i);
                    t.printId();
                    t.threadLocal = null;
                    t = null;
                }
            }
        }).start();

    }

    static class TestClass {
        private int id;
        private int[] arr;
        public ThreadLocal<TestClass> threadLocal;

        TestClass(int id) {
            this.id = id;
            arr = new int[1000000];
            threadLocal = new ThreadLocal<>();
            new InheritableThreadLocal<>();
            threadLocal.set(this);
        }

        public void printId() {
            System.out.println(threadLocal.get().id);
        }
    }
}
