package com.wang.code.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author WANGJJ
 * @date 2019/12/25
 */
public class Server {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {

        IOrder iOrder = new OrderImpl();
        LocateRegistry.createRegistry(6666);
        Naming.bind("rmi://localhost:6666/order", iOrder);
        System.out.println("服务器已经启动了");

    }

}
