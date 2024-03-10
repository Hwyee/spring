package cn.hwyee.common.config.zk;

import cn.hwyee.common.system.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

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
@Order(2)//并不能改变bean的加载优先级
@DependsOn("zooKeeperProperties")//可以改变bean的加载优先级，让zooKeeperProperties先加载，便于读取配置。
public class ZooKeeperConfig implements Ordered {

    public static void main(String[] args) {

    }

    private ZooKeeper zooKeeper;

    @Bean("zooKeeper")
    public ZooKeeper getZooKeeper() throws IOException {
        System.out.println(ZooKeeperProperties.connectString);
        //System.out.println(SystemProperties.getAllProperties() + Thread.currentThread() + " - watched...")
        zooKeeper = new ZooKeeper(ZooKeeperProperties.connectString, ZooKeeperProperties.sessionTimeout, this::watchEventMethod
        );
        return zooKeeper;
    }


    public void watchEventMethod(WatchedEvent watchedEvent) {
        log.info("Watch event:" + watchedEvent.toString());
        List<String> children = null;
        try {
            children = zooKeeper.getChildren(ZooKeeperProperties.registerNode, true);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        children.forEach(c -> {
            try {
                log.info("WatchStart Children: " + new String(zooKeeper.getData(ZooKeeperProperties.registerNode + "/" + c, false, null)));
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public int getOrder() {
        return 2;
    }
}
