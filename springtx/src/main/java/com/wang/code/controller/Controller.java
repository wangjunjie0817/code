package com.wang.code.controller;

import com.alibaba.fastjson.JSONObject;
import com.wang.code.dao.CustomerDao;
import com.wang.code.pojo.Customer;
import com.wang.code.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

/**
 * @author WANGJJ
 * @date 2020/07/13
 */

@RestController
public class Controller {

    @Autowired
    MyService service;

    @GetMapping("/testGet")
    public String testGetData(){
        Customer customer = service.getCustomer();
        return JSONObject.toJSONString(customer);
    }

    @GetMapping("/testInsert")
    public String testInsertData() throws InterruptedException, MalformedURLException {
//        service.insertCustomer1();
        service.insertCustomer2();
        return "success";
    }

}
