package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableCaching
public class App {
    public static void main(String[] args) {


        SpringApplication springApplication = new SpringApplication(App.class);
        springApplication.setApplicationContextClass(AnnotationConfigServletWebServerApplicationContext.class);
        springApplication.run(args);

    }
}
