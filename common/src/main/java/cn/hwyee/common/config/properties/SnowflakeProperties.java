package cn.hwyee.common.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hui
 * @version 1.0
 * @className SnowflakeProperties
 * @description 雪花算法自定义配置
 * @date 2023/5/10
 * @since JDK 1.8
 */
@Component
@ConfigurationProperties(prefix = "snowflake")
@Data
public class SnowflakeProperties {

}
