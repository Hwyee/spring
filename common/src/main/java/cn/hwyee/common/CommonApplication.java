package cn.hwyee.common;

import cn.hwyee.common.config.properties.SnowflakeProperties;
import cn.hwyee.common.config.zk.ZKDemo;
import cn.hwyee.common.config.zk.ZooKeeperConfig;
import cn.hwyee.common.config.zk.ZooKeeperProperties;
import cn.hwyee.common.lock.ZookeeperLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ProjectYH
 */
@SpringBootApplication
public class CommonApplication implements CommandLineRunner {
    @Autowired
    private SnowflakeProperties snowflakeProperties;
    @Autowired
    private ZooKeeper zooKeeper;
    @Autowired
    private ZKDemo zkDemo;
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ZooKeeperProperties.connectString);
//        zooKeeper.create("/java/java", "java".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        zkDemo.watchNodeOnce();
    }
}
