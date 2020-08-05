package com.wang.code.book.capture7;

import sun.tools.jconsole.Worker;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;

/**
 * @author WANGJJ
 * @date 2020/05/07
 */
public class LogService {

    private BlockingQueue<String> queue;
    private Thread workerThread;
    private boolean isShutDown;

    public LogService(Writer writer){
        this.workerThread = new WorkerThread(writer);
    }

    public void start(){
        this.workerThread.start();
    }

    public void stop(){
        this.isShutDown = true;
    }

    public void log(String logString) throws InterruptedException {
        this.queue.put(logString);
    }

    public class WorkerThread extends Thread{

        private Writer writer;

        public WorkerThread(Writer writer){
            this.writer = writer;
        }

        @Override
        public void run(){
            while (true){
                if (isShutDown){
                    break;
                }
                try {
                    String logString = queue.take();
                    this.writer.write(logString);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        try{
            int a = 0;
            int b = 1;
            System.out.println(a / b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("error occured");
        }
    }

}
