package com.wang.code.xiangxue.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author WANGJJ
 * @date 2019/12/26
 */
public class Pool {

    private static LinkedList<Connection> connectionPool = new LinkedList<>();

    public Pool(Integer initialize) {
        if(initialize>0) {
            for(int i=0;i<initialize;i++) {
                connectionPool.addLast(SqlConnectionImpl.getConnection());
            }
        }
    }

    public Connection getConnection(long mills) throws InterruptedException {
        synchronized (connectionPool){
            if (mills < 0) {
                if (connectionPool.isEmpty()){
                    connectionPool.wait();
                }
                return connectionPool.removeFirst();
            } else {
                long overtime = System.currentTimeMillis()+mills;
                long remain = mills;
                while(connectionPool.isEmpty()&&remain>0) {
                    connectionPool.wait(remain);
                    remain = overtime - System.currentTimeMillis();
                }
                Connection result  = null;
                if(!connectionPool.isEmpty()) {
                    result = connectionPool.removeFirst();
                }
                return result;
            }
        }
    }

    public void releaseConnection(Connection connection){
        synchronized (connectionPool){
            connectionPool.addLast(connection);
            connectionPool.notifyAll();
        }
    }
}
