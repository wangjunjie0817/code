package com.wang;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.wang.service.AnnotationAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.wang.service")
@PropertySource("classpath:/dubbo-consumer.properties")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
