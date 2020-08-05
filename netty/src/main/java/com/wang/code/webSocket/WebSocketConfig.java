package com.wang.code.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WANGJJ
 * @date 2020/01/09
 */

@Configuration
public class WebSocketConfig {

    @Bean
    public WebSocketServer webSocketServer(){
        return new WebSocketServer();
    }
}
