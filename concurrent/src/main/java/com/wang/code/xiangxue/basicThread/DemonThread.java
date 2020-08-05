package com.wang.code.xiangxue.basicThread;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class DemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DemonThread.UseThread();
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5L);
    }

    public static class UseThread extends Thread{
        @Override
        public void run(){
            try {
                while (!this.isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName() + " interrupt flag is " + this.isInterrupted());
            } finally {
                // 注意这里的finally里的代码不一定会执行
                System.out.println("...........finally");
            }
        }
    }
}
