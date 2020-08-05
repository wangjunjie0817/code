package com.wang.code.service;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WANGJJ
 * @date 2020/07/28
 */

@Service
public class MyService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void test(){
        kafkaTemplate.send("test", "firstMsg");
    }

}
