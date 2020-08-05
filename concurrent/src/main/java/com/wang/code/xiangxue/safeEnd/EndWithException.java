package com.wang.code.xiangxue.safeEnd;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class EndWithException {

    private static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss_SSS");

    public EndWithException() {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new EndWithException.UseThread("HasInterrputEx");
        System.out.println(endThread.getState());
        endThread.start();
        System.out.println("Main:" + formater.format(new Date()));
        Thread.sleep(800L);
        System.out.println(endThread.getState());
        System.out.println("Main begin interrupt thread:" + formater.format(new Date()));
        endThread.interrupt();


    }

    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName;
            for(threadName = Thread.currentThread().getName(); !this.isInterrupted(); System.out.println(threadName)) {
                try {
                    System.out.println("UseThread:" + EndWithException.formater.format(new Date()));
                    Thread.sleep(3000L);
                } catch (InterruptedException var3) {
                    System.out.println(threadName + " catch interrput flag is " + this.isInterrupted() + " at " + EndWithException.formater.format(new Date()));
                    this.interrupt();
                    var3.printStackTrace();
                }
            }

            System.out.println(threadName + " interrput flag is " + this.isInterrupted());
        }
    }

}
