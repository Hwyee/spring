package cn.hwyee.common.config.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ZkClient
 * @description 模拟客户端进行订阅（监控）
 * zk相当与注册中心
 * @date 2024/3/4
 * @since JDK 1.8
 */
@Component
@Slf4j
public class ZkClient {

    @Autowired
    private ZooKeeper zooKeeper;

    public void subscribe() throws InterruptedException, KeeperException {
        zooKeeper.getChildren(ZooKeeperProperties.registerNode, true);
    }
}
