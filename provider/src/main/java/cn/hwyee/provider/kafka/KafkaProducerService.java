package cn.hwyee.provider.kafka;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName KafkaProducerService
 * @description
 * @date 2024/5/3
 * @since JDK 1.8
 */
@Service
@Slf4j
public class KafkaProducerService {

    /**
     * KafkaTemplate 包装了生产者并提供了将数据发送到 Kafka 主题的便捷方法
     */
    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public void send(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }
}
