package com.wang.code.io.aio.client;

import com.wang.code.io.Const;

import java.util.Scanner;

/**
 * @author WANGJJ
 * @date 2019/12/05
 */
public class AioClient {

    //IO通信处理器
    private static AioClientHandler clientHandle;

    public static void start(){
        if(clientHandle!=null)
            return;
        clientHandle = new AioClientHandler(Const.IP,Const.PORT);
        //负责网络通讯的线程
        new Thread(clientHandle,"Client").start();
    }
    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        if(msg.equals("q")) return false;
        clientHandle.sendMessag(msg);
        return true;
    }

    public static void main(String[] args) throws Exception{
        AioClient.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(AioClient.sendMsg(scanner.nextLine()));
    }

}
