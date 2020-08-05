package com.wang.code.xiangxue.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author WANGJJ
 * @date 2019/12/27
 */
public class UseFuture {

    public static class UseCallable implements Callable<Integer> {

        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算");
            Thread.sleep(10000);

            for (int i = 0; i < 5000; i++) {
                sum = sum + i;
            }
            System.out.println("Callable子线程计算完成，结果=" + sum);
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseCallable useCallable = new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(useCallable);
        new Thread(futureTask).start();
        Random r = new Random();
        TimeUnit.MILLISECONDS.sleep(1);
        if (r.nextBoolean()) {//随机决定是获得结果还是终止任务
            try {
                System.out.println("Get UseCallable result = " + futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.getCause();
            } finally {
                futureTask.cancel(true);
            }
        } else {
            System.out.println("中断计算");
            futureTask.cancel(true);
        }
    }

}
