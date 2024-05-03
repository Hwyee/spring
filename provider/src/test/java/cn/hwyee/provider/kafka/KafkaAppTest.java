package cn.hwyee.provider.kafka;

import jakarta.annotation.Resource;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.TopicBuilder;

import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName KafkaAppTest
 * @description
 * @date 2024/5/3
 * @since JDK 1.8
 */
@SpringBootTest
public class KafkaAppTest {
    @Resource
    private KafkaProducerService kafkaProducerService;
    @Resource
    private KafkaAdminService kafkaAdminService;

    @Test
    public void test1() {
        kafkaAdminService.createTopic( topic2(),topic3());
        kafkaProducerService.send("test", "hello world");
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            kafkaProducerService.send("test", "hello world"+i);
        }
    }

    public NewTopic topic2() {
        return TopicBuilder.name("test")
                .partitions(3)
                .replicas(2)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }


    public NewTopic topic3() {
        return TopicBuilder.name("thing3")
                .assignReplicas(0, List.of(0, 1))
                .assignReplicas(1, List.of(1, 2))
                .assignReplicas(2, List.of(2, 0))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }
}
