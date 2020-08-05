package com.wang.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WANGJJ
 * @date 2020/06/22
 */

@Component
public class MyHandler {

    @ResponseBody
    public Integer getName() {
        return 2;
    }
}
