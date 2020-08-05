package com.wang.code.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author WANGJJ
 * @date 2019/12/25
 */
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        IOrder order = (IOrder) Naming.lookup("rmi://localhost:6666/order");
        String remoteCallResult = order.pay("1688888");
        System.out.println(remoteCallResult);

    }

}
