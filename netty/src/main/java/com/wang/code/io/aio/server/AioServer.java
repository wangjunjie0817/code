package com.wang.code.io.aio.server;

import com.wang.code.io.Const;

/**
 * @author WANGJJ
 * @date 2019/12/05
 */
public class AioServer {
    private static AioServerHandler serverHandle;
    //统计客户端个数
    public volatile static long clientCount = 0;

    public static void start(){
        if(serverHandle!=null) {
            return;
        }
        serverHandle = new AioServerHandler(Const.PORT);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        AioServer.start();
    }
}
