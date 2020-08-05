package com.wang.code.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author WANGJJ
 * @date 2020/07/27
 */

@Component
public class MyBean {

    @KafkaListener(topics = "test")
    public void processMessage(String content) {
        System.out.println(content);
    }
}
