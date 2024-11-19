package cn.hwyee.common;

import cn.hwyee.common.config.properties.SnowflakeProperties;
import cn.hwyee.common.util.net.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * @author ProjectYH
 */
@SpringBootApplication
public class CommonApplication implements CommandLineRunner {
    @Autowired
    private SnowflakeProperties snowflakeProperties;

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(ZooKeeperProperties.connectString);
//        zooKeeper.create("/java/java", "java".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
//        zkDemo.watchNodeOnce();
        System.out.println(snowflakeProperties);
        HttpUtil.restTemplate = new RestTemplate();
    }
}
