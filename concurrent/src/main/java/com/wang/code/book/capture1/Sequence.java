package com.wang.code.book.capture1;

import com.wang.code.book.ThreadSafe;

/**
 * @author WANGJJ
 * @date 2020/05/03
 */

@ThreadSafe
public class Sequence {

    private int value;

    public synchronized int getNext(){
        return value++;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) throws InterruptedException {

        Unsafe unsafe = new Unsafe();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++){
                    unsafe.getNext();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        Thread.sleep(10);

        System.out.println(unsafe.getValue());

    }

}
