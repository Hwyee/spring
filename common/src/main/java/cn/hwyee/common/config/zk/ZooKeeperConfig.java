package cn.hwyee.common.config.zk;

import cn.hwyee.common.system.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Get;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ZooKeeperConfig
 * @description
 * @date 2024/3/2
 * @since JDK 1.8
 */
@Configuration
@Slf4j
public class ZooKeeperConfig {

    public static void main(String[] args) {

    }

    @Bean
    public ZooKeeper getZooKeeper() throws IOException {
        return new ZooKeeper(ZooKeeperProperties.connectString, ZooKeeperProperties.sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(SystemProperties.getAllProperties() + Thread.currentThread() + " - watched...");
            }
        });
    }

}
