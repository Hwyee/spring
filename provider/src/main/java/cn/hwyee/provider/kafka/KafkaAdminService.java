package cn.hwyee.provider.kafka;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName KafkaAdminService
 * @description kafka主题管理
 * @date 2024/5/3
 * @since JDK 1.8
 */
@Service
@Slf4j
public class KafkaAdminService {

    @Resource
    private KafkaAdmin kafkaAdmin;

    public void createTopic(String topicName,Integer partition,Integer replication){
        NewTopic build = TopicBuilder.name(topicName).partitions(partition).replicas(replication).build();
        kafkaAdmin.createOrModifyTopics(build);
    }

    public void createTopic(NewTopic... newTopic){
        kafkaAdmin.createOrModifyTopics(newTopic);
    }


}
