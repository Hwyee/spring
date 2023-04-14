package cn.hwyee.provider.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author hui
 * @version 1.0
 * @className AsyncThreadService
 * @description Async注解服务
 * @date 2023/4/14
 * @since JDK 1.8
 */
@Service
@Slf4j
public class AsyncThreadService {

    /**
     * asyncDirectReturnString:
     * 没有返回Future,这样接口会变成同步的。
     * @author hui
     * @version 1.0
     * @param msg
     * @param time
     * @return java.lang.String
     * @date 2023/4/14 13:38
     */
    @Async
    public String asyncDirectReturnString(String msg, Long time) {
        try {
            Thread.sleep(time);
            log.info("返回值没有使用Future类:{}",msg);
        } catch (InterruptedException e) {
            log.error("多线程出错:{}",e.getMessage(),e);
            Thread.currentThread().interrupt();
        }
        return msg;
    }

    @Async
    public CompletableFuture<String> asyncReturnFutureString(String msg, Long time) {
        try {
            Thread.sleep(time);
            log.info("返回值使用Future类:{}",msg);
        } catch (InterruptedException e) {
            log.error("多线程出错:{}",e.getMessage(),e);
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(msg);
    }

}
