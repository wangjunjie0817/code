package com.wang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author WANGJJ
 * @date 2020/06/22
 */

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler({IOException.class})
    public void handle(OutputStream o) throws IOException {
        o.write("wang".getBytes());
        o.flush();

    }

}
