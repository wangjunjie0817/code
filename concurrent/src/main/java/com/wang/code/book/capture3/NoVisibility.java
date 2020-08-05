package com.wang.code.book.capture3;

/**
 * @author WANGJJ
 * @date 2020/05/05
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    public static class MyThread extends Thread{

        @Override
        public void run() {
            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        MyThread myThread = new MyThread();
        myThread.start();

        ready = true;
        number = 42;
    }

}
