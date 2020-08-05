package com.wang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WANGJJ
 * @date 2020/06/22
 */

@RestController
@Slf4j
public class Controller {

    @Autowired
    private Environment environment;

    @Value("${name.wang:wang}")
    private String name;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/division/{num}")
    @Cacheable(value = "division", key = "'springCache'+#num")
    public Integer division(@PathVariable Integer num){
        System.out.println("do division");
        return num / 2;
    }

}
