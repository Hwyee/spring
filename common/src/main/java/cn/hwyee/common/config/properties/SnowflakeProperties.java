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

    /**
     * 机器码 默认值 1
     */
    public static long workerId = 1;

    /**
     * 序列初始值最大偏移量 默认1
     */
    public static Integer maxVibrationOffset = 1;

    /**
     * 时间回拨最大等待时间 默认10s
     */
    public static int maxTolerateTimeDifferenceMilliseconds = 10;

    public void setMaxVibrationOffset(Integer maxVibrationOffset) {
        SnowflakeProperties.maxVibrationOffset = maxVibrationOffset;
    }

    public void setWorkerId(Long workerId){
        SnowflakeProperties.workerId = workerId;
    }

    public void setMaxTolerateTimeDifferenceMilliseconds(Integer maxTolerateTimeDifferenceMilliseconds){
        SnowflakeProperties.maxTolerateTimeDifferenceMilliseconds = maxTolerateTimeDifferenceMilliseconds;
    }

}
