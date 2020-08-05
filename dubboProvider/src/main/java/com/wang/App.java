package com.wang;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;
import com.wang.service.AnnotationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.wang.serviceImpl")
@PropertySource("classpath:/dubbo-provider.properties")
public class App {

    public static class MyProtocol implements Protocol{

        @Override
        public int getDefaultPort() {
            return 0;
        }

        @Override
        public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
            return null;
        }

        @Override
        public <T> Invoker<T> refer(Class<T> aClass, URL url) throws RpcException {
            return null;
        }

        @Override
        public void destroy() {

        }
    }


    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);

    }

}
