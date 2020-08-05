package com.wang.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author WANGJJ
 * @date 2020/07/15
 */

@Component
public class Redis {

    @Autowired
    StringRedisTemplate redisTemplate;

    public void test(){

    }

}
