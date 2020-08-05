package com.wang.code.xiangxue.safeEnd;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class EndThread {

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new EndThread.UseThread("endThread");
        endThread.start();
        Thread.sleep(20L);
        endThread.interrupt();
    }

    private static class UseThread extends Thread {

        UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();

            while(!currentThread().isInterrupted()) {
                System.out.println(threadName + " is run!");
            }
        }
    }
}
