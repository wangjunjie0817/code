package com.wang.code.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author WANGJJ
 * @date 2019/12/25
 */
public class OrderImpl extends UnicastRemoteObject implements IOrder {

    protected OrderImpl() throws RemoteException {
        super();
    }

    @Override
    public String pay(String id) throws RemoteException {
        return "支付成功" + id;
    }
}
