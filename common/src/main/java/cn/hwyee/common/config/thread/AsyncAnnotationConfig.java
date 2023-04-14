package cn.hwyee.common.config.thread;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hui
 * @version 1.0
 * @className AsyncAnnotationConfig
 * @description @Async线程池config
 * @date 2023/4/14
 * @since JDK 1.8
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncAnnotationConfig implements AsyncConfigurer {

    /**
     * getAsyncExecutor:
     * 配置@Async默认线程池,只能配置一个该接口实现,否则会报错。
     * 用TtlExecutors.getTtlExecutor包裹可以使用阿里的TransmittableThreadLocal
     * @author hui
     * @version 1.0
     * @return java.util.concurrent.Executor
     * @date 2023/4/14 10:56
     */
    @Bean(name = "asyncThreadPool")
    @Override
    public Executor getAsyncExecutor() {
        return TtlExecutors.getTtlExecutor(new ThreadPoolExecutor(AsyncThreadProperties.coreSize, AsyncThreadProperties.maxSize,
                AsyncThreadProperties.keepAlive, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(AsyncThreadProperties.queueCapacity),
                new AsyncThreadFactory(), new ThreadPoolExecutor.AbortPolicy()));
    }

    /**
     * getAsyncUncaughtExceptionHandler:
     * 多线程未处理的异常,统一处理
     * @author hui
     * @version 1.0
     * @return org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
     * @date 2023/4/14 10:56
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) ->
                log.error("多线程执行报错,错误方法：{}", method.getName(), throwable);
    }
}
