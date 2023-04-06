package cn.hwyee.provider.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author hui
 * @version 1.0
 * @className RabbitmqProviderService
 * @description
 * @date 2023/4/6
 * @since JDK 1.8
 */
@Service
@Slf4j
public class RabbitmqProviderService {
    @Resource
    private RabbitTemplate rabbitTemplate;
    public void test(String queue,String msg) {
        rabbitTemplate.convertAndSend(queue,msg);
    }
}
