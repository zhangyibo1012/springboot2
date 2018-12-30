package cn.zyblogs.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Class: TestController.java
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "1")
    private String get1(){
        log.info("get1 start" );
        final String result = createStr();
        log.info("get1 end" );
        return  result;
    }

    @GetMapping(value = "2")
    private Mono<String> get2(){
        log.info("get2 start" );
//        Mono.fromSupplier 传入一个提供者
        Mono<String> result = Mono.fromSupplier(() -> createStr());
        log.info("get2 end" );
        return result;
    }

    /**
     * Flux : 返回0-n个元素
     * text/event-stream 以流的形式返回  一条一条的数据
     * @return
     */
    @GetMapping(value = "3" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 5).mapToObj(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return "flux data--" + i;
                }));
        return result;
    }

    private String createStr(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some string";
    }

}
