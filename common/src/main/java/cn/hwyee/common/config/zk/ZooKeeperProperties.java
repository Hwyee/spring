package cn.hwyee.common.config.zk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hui
 * @version 1.0
 * @className SnowflakeProperties
 * @description Zookeeper自定义配置
 * 因为是static属性，要自己重写set方法。
 * @date 2023/5/10
 * @since JDK 1.8
 */
@Component
@ConfigurationProperties(prefix = "zookeeper")
@Data
public class ZooKeeperProperties {

    /**
     * zk链接串 默认hwyee.cn:2182
     * 多个用逗号分开
     */
    public static String connectString = "hwyee.cn:2182";

    /**
     * 会话超时时间
     */
    public static Integer sessionTimeout = 2000;

    public void setConnectString(String connectString) {
        ZooKeeperProperties.connectString = connectString;
    }

    public void setSessionTimeout(Integer sessionTimeout){
        ZooKeeperProperties.sessionTimeout = sessionTimeout;
    }

}
