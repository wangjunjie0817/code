package com.wang.code.xiangxue.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class PoolTest {
    static Pool pool = new Pool(10);

    static CountDownLatch countDownLatch;


    public static void main(String[] args) throws InterruptedException {

        Integer threadCount = 50;

        Integer count = 20;

        countDownLatch = new CountDownLatch(threadCount);
        AtomicInteger got = new AtomicInteger();//计数器：统计可以拿到连接的线程
        AtomicInteger notGot = new AtomicInteger();//计数器：统计没有拿到连接的线程
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notGot));
            thread.start();
        }

        countDownLatch.await();
        System.out.println("总共尝试了: " + (threadCount * count));
        System.out.println("拿到连接的次数：  " + got);
        System.out.println("没能连接的次数： " + notGot);
    }

    public static class Worker implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got,
                      AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    Connection connection = pool.getConnection(1000);
                    if (connection != null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }

                    } else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()
                                +"等待超时!");
                    }
                } catch (InterruptedException e) {
                    notGot.incrementAndGet();
                }
                count--;
            }
            countDownLatch.countDown();
        }
    }

}
