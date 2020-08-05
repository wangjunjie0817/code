package com.wang.code.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author WANGJJ
 * @date 2020/07/16
 */

@RestController
public class BasicController {

    @GetMapping("/hello")
    public Mono<String> sayHelloWorld(){
        return Mono.just("hello world");
    }

    @RequestMapping("/sse")
    public Flux<ServerSentEvent<Integer>> randomNum(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq-> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data-> ServerSentEvent.<Integer>builder()
                        .data(data.getT2()).build());
    }

}
