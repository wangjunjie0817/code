package com.wang.controller;

import com.wang.service.AnnotationAction;
import com.wang.service.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WANGJJ
 * @date 2020/07/20
 */

@RestController
public class TestController {

    @Autowired
    AnnotationAction annotationAction;

    @RequestMapping("/testDubbo")
    public String getName(){
        return annotationAction.doSayHello();
    }

}
