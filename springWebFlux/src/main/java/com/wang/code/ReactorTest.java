package com.wang.code;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author WANGJJ
 * @date 2020/07/16
 */
public class ReactorTest {

    public static void main(String[] args) throws InterruptedException {
//        Flux.just("Hello", "World").subscribe(System.out::println);
//        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
//        Flux.empty().subscribe(System.out::println);
//        Flux.range(1, 10).subscribe(System.out::println);
//        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);

//        Flux.generate(sink->{sink.next("wang");sink.complete();}).subscribe(System.out::println);
//        final Random random = new Random();
//        Flux.generate(ArrayList::new, (list, sink)->{
//            int value = random.nextInt(100);
//            list.add(value);
//            sink.next(value);
//            if (list.size() == 10) {
//                sink.complete();
//            }
//            return list;
//        }).subscribe(System.out::println);
//
//        Mono.fromCallable(new Callable<String>() {
//            @Override
//            public String call() throws Exception{
//                return "wang";
//            }
//        }).subscribe(System.out::println);

//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
//        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);

//        Flux.range(1, 10).filter(i->i % 2 == 0).subscribe(System.out::println);
//        Flux.range(1, 100).window(20).subscribe(System.out::println);

//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .subscribe(System.out::println);

        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);


    }


}
