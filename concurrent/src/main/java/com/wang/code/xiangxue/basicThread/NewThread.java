package com.wang.code.xiangxue.basicThread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 启动线程的三种方法
 */
public class NewThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo:
                threadInfos) {
            System.out.println(threadInfo.getThreadName());
            System.out.println(threadInfo.getThreadId());
        }

        Thread thread1 = new UseThread();
        thread1.start();

        Thread thread2 = new Thread(new UseRun());
        thread2.start();

        FutureTask<String> futureTask = new FutureTask(new UseCall());
        Thread thread3 = new Thread(futureTask);
        thread3.start();
        System.out.println(futureTask.get());

    }

    public static class UseThread extends Thread {

        @Override
        public void run(){
            System.out.println("I am extend Thread");
            System.out.println(this.getName());
        }

    }

    public static class UseCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("I am implements Callable");
            return "CallResult";
        }
    }

    public static class UseRun implements Runnable {

        @Override
        public void run() {
            System.out.println("I am implements Runnable");
        }
    }

}
