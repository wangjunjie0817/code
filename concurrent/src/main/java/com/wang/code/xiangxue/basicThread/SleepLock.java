package com.wang.code.xiangxue.basicThread;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class SleepLock {

    private Object lock = new Object();

    public SleepLock() {
    }

    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();

        try {
            Thread.sleep(1000L);
            System.out.println(" Main slept!");
        } catch (InterruptedException var5) {
            var5.printStackTrace();
        }

        threadB.start();
    }

    private class ThreadNotSleep extends Thread {
        private ThreadNotSleep() {
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock time=" + System.currentTimeMillis());
            synchronized(SleepLock.this.lock) {
                System.out.println(threadName + " taking the lock time=" + System.currentTimeMillis());
                System.out.println("Finish the work: " + threadName);
            }
        }
    }

    private class ThreadSleep extends Thread {
        private ThreadSleep() {
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock");

            try {
                synchronized(SleepLock.this.lock) {
                    System.out.println(threadName + " taking the lock");
                    System.out.println("Finish the work: " + threadName);
                }
                Thread.sleep(5000L);

            } catch (InterruptedException var5) {
            }

        }
    }
}
