package com.wang.code.io.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author WANGJJ
 * @date 2019/12/05
 */
public class AioServerHandler implements Runnable {

    public CountDownLatch latch;
    /*进行异步通信的通道*/
    public AsynchronousServerSocketChannel channel;

    public AioServerHandler(int port) {
        try {
            //创建服务端通道
            channel = AsynchronousServerSocketChannel.open();
            //绑定端口
            channel.bind(new InetSocketAddress(port));
            System.out.println("Server is start,port:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {

        latch = new CountDownLatch(1);
        //用于接收客户端的连接，异步操作，
        // 需要实现了CompletionHandler接口的处理器处理和客户端的连接操作
        channel.accept(this,new AioAcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
