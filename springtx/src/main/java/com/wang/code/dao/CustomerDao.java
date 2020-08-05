package com.wang.code.dao;

import com.wang.code.mapper.MysqlMapper;
import com.wang.code.pojo.Customer;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author WANGJJ
 * @date 2020/07/13
 */

@Repository
public class CustomerDao {

    @Autowired
    private MysqlMapper mysqlMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public Customer getCustomer(){
        return mysqlMapper.getCustomer();
    }

    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = -1, readOnly = false)
    public void insertCustomer1(){
        mysqlMapper.insertCustomer1();
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED, isolation = Isolation.DEFAULT, timeout = 2, readOnly = false)
    public void insertCustomer2() throws InterruptedException {

        mysqlMapper.insertCustomer2();
        throw new NullPointerException();
    }

    // 手动事务管理
    public void insertCustomer3(){
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);

        try {
            mysqlMapper.insertCustomer2();
            if (true){
                throw new NullPointerException();
            }
            transactionManager.commit(transactionStatus);
        } catch (Exception e){
            transactionManager.rollback(transactionStatus);
        }

    }

}
