package com.wang.code.book.capture2;

import java.util.Vector;

/**
 * @author WANGJJ
 * @date 2020/05/05
 *
 * 说明：这个类展示了synchronize关键字的用法
 */
public class SynchronizeDemo {

    public static class Father{

        public synchronized void doSomeThing(){
            System.out.println(this);
        }

    }

    public static class Son extends Father{

        @Override
        public synchronized void doSomeThing(){
            System.out.println(this);
            System.out.println(super.toString());
            super.doSomeThing();
        }

    }

    public static class Demo{

        // 对象锁
        public synchronized void print1(){
            System.out.println("对象锁");
        }

        // 类锁
        public static synchronized void print2(){
            System.out.println("类锁");
        }

        public void print3(){
            synchronized (this){
                System.out.println("同步代码块");
            }
        }
    }

    // 运行结果如下，发现其实父类和子类锁的是同一个对象
//        com.wang.code.book.capture2.SynchronizeDemo$Son@61bbe9ba
//        com.wang.code.book.capture2.SynchronizeDemo$Son@61bbe9ba
//        com.wang.code.book.capture2.SynchronizeDemo$Son@61bbe9ba
    public static void main(String[] args) {
        Father father = new Son();
        father.doSomeThing();

        System.out.println(Father.class);
    }



}
