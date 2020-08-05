package com.wang.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wang.service.AnnotationService;

/**
 * @author WANGJJ
 * @date 2020/07/20
 */

@Service
public class AnnotationServiceImpl implements AnnotationService {
    @Override
    public String sayHello(String name) {
        return "hello world" + name;
    }
}
