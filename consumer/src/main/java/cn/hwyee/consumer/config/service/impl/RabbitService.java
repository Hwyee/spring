package cn.hwyee.consumer.config.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author hui
 * @version 1.0
 * @className RabbitService
 * @description
 * @date 2023/4/6
 * @since JDK 1.8
 */
@Service
@Slf4j
public class RabbitService {


    @RabbitListener(queues = "test")
    @RabbitHandler
    public void test(String msg) {
      log.info("consumer收到：{}",msg);
    }
}
