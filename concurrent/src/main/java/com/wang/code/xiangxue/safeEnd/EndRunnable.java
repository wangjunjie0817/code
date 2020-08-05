package com.wang.code.xiangxue.safeEnd;

/**
 * Runnable 线程终止
 */
public class EndRunnable {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new EndRunnable.UseRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(20L);
        thread.interrupt();
    }

    public static class UseRunnable implements Runnable {

        @Override
        public void run() {

            String name = Thread.currentThread().getName();

            while (!Thread.currentThread().isInterrupted()){
                System.out.println(name + "i am running");
            }
            System.out.println(name + "i am interrupted");
        }
    }
}
