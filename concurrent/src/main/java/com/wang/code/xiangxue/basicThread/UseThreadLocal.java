package com.wang.code.xiangxue.basicThread;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class UseThreadLocal {

    static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static class TestThread implements Runnable{

        int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            Integer s = threadLocal.get();
            System.out.println(s);
            s = s + this.id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        Thread[] runs = new Thread[3];
        int i;
        for(i = 0; i < runs.length; ++i) {
            runs[i] = new Thread(new UseThreadLocal.TestThread(i));
        }

        for(i = 0; i < runs.length; ++i) {
            runs[i].start();
        }
    }

}
