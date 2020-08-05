package com.wang;

import sun.misc.Request;

import javax.servlet.ServletRequest;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.ref.*;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WANGJJ
 * @date 2020/07/22
 */
public class Test {


    public static class Test1 {



        synchronized void getName1() throws InterruptedException {

            wait();
            System.out.println("name1");
        }

        synchronized void getName2(){
            notify();
            System.out.println("name2");
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        WeakReference<String> pr = new WeakReference<>(new String("hello"), queue);
        System.out.println(pr.get());
        System.gc();
        System.out.println(pr.get());
        new Thread(()->{
            for (;;){
                Reference<? extends String> poll = queue.poll();
                if (poll == null){
                    continue;
                }
                String s = poll.get();
                System.out.println(s);

            }

        });


        Thread.sleep(5000);

    }
}
