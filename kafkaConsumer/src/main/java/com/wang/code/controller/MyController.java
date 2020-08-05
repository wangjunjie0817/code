package com.wang.code.controller;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WANGJJ
 * @date 2020/07/27
 */

@RestController
public class MyController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/testSendMsg")
    public void sendKafkaMsg(){
        kafkaTemplate.send("test", "firstMsg");
    }
}
