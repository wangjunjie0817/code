package com.wang.code.book.capture13;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WANGJJ
 * @date 2020/05/09
 */
public class Demo {

    public static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(10000);
            return 20;
        }
    }

    public static void main(String[] args) {
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Demo.MyCallable());
//        futureTask.run();
//        try {
//            System.out.println(futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        for (AtomicInteger integer = new AtomicInteger(5); integer.intValue() < 10; integer.incrementAndGet()){
            System.out.println(integer.intValue());
        }

    }

}
