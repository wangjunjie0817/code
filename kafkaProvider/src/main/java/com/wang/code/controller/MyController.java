package com.wang.code.controller;

import com.wang.code.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WANGJJ
 * @date 2020/07/27
 */

@RestController
public class MyController {

    @Autowired
    private MyService service;

    @RequestMapping("/testSendMsg")
    public void sendKafkaMsg(){
        service.test();
    }
}
