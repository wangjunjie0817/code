package com.wang.controller;

import com.wang.anno.LoginUser;
import com.wang.pojo.Person;
import com.wang.service.MyService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import javax.validation.Valid;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author WANGJJ
 * @date 2020/06/22
 */

@RestController
@Slf4j
public class Controller {

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    MessageSource messageSource;

    @Resource(name = "applicationTaskExecutor")
    TaskExecutor executor;

    @Autowired
    MyService myService;

    @RequestMapping("/")
    public String getName(HttpServletRequest httpServletRequest){
        RequestContextUtils.findWebApplicationContext(httpServletRequest);
        return "wang";
    }

    @GetMapping(path = "/pets")
    public String getName1(ServletRequest servletRequest) throws IOException {
        DispatcherType dispatcherType = servletRequest.getDispatcherType();
        servletRequest.startAsync();
        DispatcherType dispatcherType1 = servletRequest.getDispatcherType();
        return "wang";

    }

    @GetMapping(path = "async")
    public String getAsyncString() throws ExecutionException, InterruptedException {
        return getAsync().get();
    }

    @GetMapping("getString")
    public String getString(@RequestParam String name){
        myService.putString(name);
        myService.evictString(name);
        return myService.getTestString(name);
    }


    @Async
    public Future<String> getAsync() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "wang";
            }
        });

    };

    @GetMapping("handle")
    public ResponseBodyEmitter handle(){
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                emitter.send("hello");
                Thread.sleep(3000);
                emitter.send("world");
                Thread.sleep(3000);
                emitter.send("end");
                emitter.complete();
            }
        }).start();
        return emitter;
    }

    @GetMapping("sse")
    public SseEmitter sse() throws IOException {

        SseEmitter emitter = new SseEmitter();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                emitter.send("hello");
                Thread.sleep(3000);
                emitter.send("world");
                Thread.sleep(3000);
                emitter.send("end");
                emitter.complete();
            }
        }).start();
        return emitter;
    }

    @GetMapping("/download")
    public void downLoad(OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/wangjunjie/Desktop/城市地摊财富秘籍(1)(1).doc");
        byte[] bytes = new byte[50000];
        fileInputStream.read(bytes);

        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    @PostMapping("/person")
    public String postPerson(@LoginUser Person person, Errors e){
        if (e.hasErrors()){
            return "error";
        }
        return "success";
    }

    @GetMapping("/dataBinding/{dateTime}")
    public String bindData(@PathVariable LocalDateTime dateTime){
        log.info(dateTime.toString());
        return "success";
    }

    @GetMapping("/messageTest")
    public String test(){
        String messageUs = messageSource.getMessage("0000", null, Locale.ENGLISH);
        String messageChina = messageSource.getMessage("0000", null, Locale.CHINESE);
        String messageJapanese = messageSource.getMessage("0000", null, Locale.JAPANESE);
        log.info("messageUs : {}", messageUs);
        log.info("messageChina : {}", messageChina);
        log.info("messageJapanese : {}", messageJapanese);
        return "success";
    }

}
