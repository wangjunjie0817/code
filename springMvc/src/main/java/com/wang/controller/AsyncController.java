package com.wang.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @author WANGJJ
 * @date 2020/06/23
 */

@RestController
public class AsyncController {

    @GetMapping("/quotes")
    public Callable<String> getAsyncResult(){

        return new Callable<String>() {
            public String call() throws Exception {
                // ...
                return "someView";
            }
        };

    }

    private void asyncSetResult(DeferredResult<String> deferredResult){
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(3000);
                deferredResult.setResult("asyncResult");
            }
        }).start();
    }

}
