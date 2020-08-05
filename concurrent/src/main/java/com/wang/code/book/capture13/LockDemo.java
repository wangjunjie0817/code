package com.wang.code.book.capture13;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WANGJJ
 * @date 2020/05/08
 */
public class LockDemo {



    public static class MyRunnable implements Runnable{

        ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                test();
            } catch (InterruptedException e) {
                System.out.println("catch exception");
            }
        }

        private void test() throws InterruptedException {
            lock.lockInterruptibly();
            try {
                Thread.sleep(15000);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        thread.interrupt();


    }

}
