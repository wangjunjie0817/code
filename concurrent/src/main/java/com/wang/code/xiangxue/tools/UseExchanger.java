package com.wang.code.xiangxue.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @author WANGJJ
 * @date 2019/12/27
 */
public class UseExchanger {

    private static final Exchanger<Set<String>> exchange
            = new Exchanger<Set<String>>();

    public static void main(String[] args) {

        //第一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setA = new HashSet<String>();//存放数据的容器
                try {
                    /*添加数据
                     * set.add(.....)
                     * */
                    setA = exchange.exchange(setA);//交换set
                    /*处理交换后的数据*/
                } catch (InterruptedException e) {
                }
            }
        }).start();

        //第二个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setB = new HashSet<String>();//存放数据的容器
                try {
                    /*添加数据
                     * set.add(.....)
                     * set.add(.....)
                     * */
                    setB = exchange.exchange(setB);//交换set
                    /*处理交换后的数据*/
                } catch (InterruptedException e) {
                }
            }
        }).start();

    }

}
