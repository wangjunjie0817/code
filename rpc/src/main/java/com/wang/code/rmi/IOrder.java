package com.wang.code.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author WANGJJ
 * @date 2019/12/25
 */
public interface IOrder extends Remote {

    /**
     * 定义远程调用接口
     * return 调用结果
     */
    String pay(String id) throws RemoteException;

}
