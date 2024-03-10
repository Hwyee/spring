package cn.hwyee.common.config.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ZKDemo
 * @description
 * @date 2024/3/3
 * @since JDK 1.8
 */
@Component
@Slf4j
public class ZKDemo {
    @Autowired
    private ZooKeeper zooKeeper;

    public void watchNodeOnce() throws InterruptedException, KeeperException {
        List<String> children = zooKeeper.getChildren("/java", true);
    }
}
