package cn.hwyee.common.zk;

import cn.hwyee.common.config.zk.ZkClient;
import cn.hwyee.common.config.zk.ZkServer;
import cn.hwyee.common.lock.ZookeeperLock;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ZKTest
 * @description
 * @date 2024/3/3
 * @since JDK 1.8
 */
@SpringBootTest
public class ZKTest {

    @Autowired
    private ZooKeeper zooKeeper;

    @Autowired
    private ZkServer zkServer;

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private ZookeeperLock zookeeperLock;

    @Test
    void test1() throws InterruptedException, KeeperException {
        zooKeeper.getChildren("/", true).forEach(System.out::println);
    }

    @Test
    void testRegister() throws InterruptedException, KeeperException {
        zkServer.registeZk("hwyee7");
        Thread.sleep(5000000);
    }


    @Test
    void testSubscriber() throws InterruptedException, KeeperException {
        zkClient.subscribe();
        Thread.sleep(500000000);
    }

    @Test
    void testLock() throws Exception {
        InterProcessLock lock = zookeeperLock.getLock("/lock");
        new Thread(() -> {
            try {
                lock.acquire();
                System.out.println("lock1 lock...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                lock.release();
                System.out.println("lock1 release...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                lock.acquire();
                System.out.println("lock2 lock...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                lock.release();
                System.out.println("lock2 release...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        Thread.sleep(500000);
    }


}
