package cn.hwyee.common.lock;

import cn.hwyee.common.config.zk.ZooKeeperProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ZookeeperLock
 * @description 分布式锁
 * @date 2024/2/24
 * @since JDK 1.8
 */
@Component
@Slf4j
public class ZookeeperLock {

    /**
     * getLock:
     * 获取分布式可重入锁
     * @author hui
     * @version 1.0
     * @param node
     * @return org.apache.curator.framework.recipes.locks.InterProcessLock
     * @date 2024/3/11 1:43
     */
    public InterProcessLock getLock(String node){
        return new InterProcessMutex(getZooKeeper(), node);
    }

    /**
     * getZooKeeper:
     * 获取zk客户端链接
     * @author hui
     * @version 1.0
     * @return org.apache.curator.framework.CuratorFramework
     * @date 2024/3/11 1:43
     */
    public CuratorFramework getZooKeeper(){
        //重试策略，初试时间 3 秒，重试 3 次
        RetryPolicy policy = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework conn = CuratorFrameworkFactory.builder().
                connectString(ZooKeeperProperties.connectString).
                connectionTimeoutMs(ZooKeeperProperties.sessionTimeout).
                sessionTimeoutMs(ZooKeeperProperties.sessionTimeout).
                retryPolicy(policy).build();
        //开启链接 CuratorFrameworkState.START
        conn.start();
        log.info("Started ZooKeeper connection...");
        return conn;
    }

}
