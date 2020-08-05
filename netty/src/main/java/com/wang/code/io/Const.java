package com.wang.code.io;

/**
 * @author WANGJJ
 * @date 2019/12/04
 */
public class Const {

    public static String IP = "127.0.0.1";
    public static Integer PORT = 7777;

    //返回给客户端的应答
    public static String response(String msg){
        return "Hello,"+msg+",Now is "+new java.util.Date(
                System.currentTimeMillis()).toString() ;
    }

}
