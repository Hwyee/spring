package cn.hwyee.consumer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName KafkaConsumerService
 * @description
 * @date 2024/5/3
 * @since JDK 1.8
 */
@Service
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(id = "test",//唯一标识符
            topics = "test",
            clientIdPrefix = "myClientId"//提供后，将覆盖消费者工厂配置中的客户端 id 属性。
    )
    public void listen(String data) {
        log.info("test listener receiving data:{}",data);
    }
}
