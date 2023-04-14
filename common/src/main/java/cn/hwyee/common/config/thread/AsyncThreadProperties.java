package cn.hwyee.common.config.thread;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hui
 * @version 1.0
 * @className AsyncThreadProperties
 * @description Async注解多线程参数配置
 * @date 2023/4/14
 * @since JDK 1.8
 */
@Data
@Component
@ConfigurationProperties("async.pool")
public class AsyncThreadProperties {
    private AsyncThreadProperties(){}
    /**
     * 核心线程数
     */
    public static int coreSize = 5;
    /**
     * 最大线程数
     */
    public static int maxSize = 10;
    /**
     * 线程存在时间（秒）
     */
    public static int keepAlive = 30;
    /**
     * 等待队列大小
     */
    public static int queueCapacity = 50;

    public void setCoreSize(int coreSize){
        AsyncThreadProperties.coreSize = coreSize;
    }

    public void setMaxSize(int maxSize){
        AsyncThreadProperties.maxSize = maxSize;
    }

    public void setKeepAlive(int keepAlive){
        AsyncThreadProperties.keepAlive = keepAlive;
    }

    public void setQueueCapacity(int queueCapacity){
        AsyncThreadProperties.queueCapacity = queueCapacity;
    }
}
