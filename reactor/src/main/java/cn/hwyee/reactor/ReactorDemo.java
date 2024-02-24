package cn.hwyee.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName FluxDemo
 * @description flux:可以发送N个元素的流
 * mono:可以有0个或1个元素
 * @date 2024/1/30
 * @since JDK 1.8
 */
public class ReactorDemo {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        //消费流,一个流可以有多个消费者，每个消费者获得的流是一样的-->广播模式
        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);

        Mono<Integer> mono = Mono.just(9);
        mono.subscribe(System.out::println);

        Flux.range(1, 5)
                .parallel()
                .map(t -> t + 10)
                //doOnNext 每个数据会触发
//                .doOnNext(t -> System.out.println(Thread.currentThread().getName() + "do OnNext." + t))
                //doOnEach 每个元素会触发，比doOnNext多了信号元素 SignalType 流的信号类型。 开启并行时，信号量会很多。
                .doOnEach(t -> System.out.println(Thread.currentThread().getName() + t.getType() + t.getClass()))
                .log()
                .subscribe(t -> {
                    System.out.println(Thread.currentThread().getName() + " " + t);
                });

    }


}
