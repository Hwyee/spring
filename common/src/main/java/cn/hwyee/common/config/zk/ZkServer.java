package cn.hwyee.common.config.zk;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ZkServer
 * @description 模拟服务端进行注册
 * zk相当与注册中心
 * @date 2024/3/4
 * @since JDK 1.8
 */
@Service
@Slf4j
public class ZkServer {

    @Resource
    private ZooKeeper zooKeeper;

    /**
     * connectZk:
     * 模拟服务端上线
     * @author hui
     * @version 1.0
     * @return void
     * @date 2024/3/4 23:54
     */
    public void registeZk(String hostname) throws InterruptedException, KeeperException {
        //注册临时节点，这样服务端失去连接后，节点消失，相当于下线。
        String zkNode = zooKeeper.create("/servers/" + hostname, hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info(hostname + " is online.");
    }

}
