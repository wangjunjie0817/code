package com.wang.code.xiangxue.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author WANGJJ
 * @date 2019/12/27
 */
public class UseSemaphore {

    public static void main(String[] args) throws InterruptedException {
        // 定义同时执行任务的线程数量
        Integer threadCount = 2;
        // 需要处理的任务总数
        int clientTotal = 10;
        // 定义信号量
        Semaphore semaphore = new Semaphore(threadCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i=0; i<10; i++) {
            final int count = i;
            executorService.submit(()->{
                try {
                    semaphore.acquire();
                    resolve(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void resolve(int i) throws InterruptedException {
        System.out.println("服务号" + i + "受理业务中。。。" );
        Thread.sleep(2000);
    }

}
