package com.wang.code.service;

import com.wang.code.dao.CustomerDao;
import com.wang.code.pojo.Customer;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author WANGJJ
 * @date 2020/07/13
 */

@Service
public class MyService {

    @Autowired
    CustomerDao customerDao;

    public Customer getCustomer(){
        return customerDao.getCustomer();
    }

    public void insertCustomer1(){
       customerDao.insertCustomer1();
    }

    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 2, readOnly = false)
    public void insertCustomer2() throws InterruptedException, MalformedURLException {
        customerDao.insertCustomer2();
        customerDao.insertCustomer1();

    }

}


