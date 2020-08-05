package com.wang.code.xiangxue.basicThread;

import java.util.concurrent.TimeUnit;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class VolatileUnsafe {
    public VolatileUnsafe() {
    }

    public static void main(String[] args) {
        VolatileUnsafe.VolatileVar v = new VolatileUnsafe.VolatileVar();
        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(v);
        Thread t4 = new Thread(v);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static class VolatileVar implements Runnable {
        private volatile int a;

        private VolatileVar() {
            this.a = 0;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            this.a = this.a++;
            System.out.println(threadName + ":======" + this.a);
            try {
                TimeUnit.MILLISECONDS.sleep(20L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++this.a;
            System.out.println(threadName + ":======" + this.a);
        }
    }
}
