package com.wang.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author WANGJJ
 * @date 2020/06/24
 */

@Service
@Slf4j
public class MyService {

    @Cacheable(cacheNames = "wangCache", key = "#name")
    public String getTestString(String name){
        log.info("+++++++++++++++++");
        return "getString" + name;
    }

    @CachePut(cacheNames = "wangCache", key = "#name")
    public String putString(String name){
        log.info("+++++++++++++++++");
        return "getString" + name;
    }

    @CacheEvict(cacheNames = "wangCache", allEntries = true)
    public String evictString(String name){
        log.info("+++++++++++++++++");
        return "getString" + name;
    }

}
