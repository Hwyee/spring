package cn.hwyee.common.util.crypto;

import cn.hwyee.common.config.properties.SnowflakeProperties;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author hui
 * @version 1.0
 * @className SnowFlakeUtil
 * @description 雪花算法(参考Apache ShardingSphere雪花算法实现)
 * 共64个bit,1位标识位(0正数,1负数),41位标识时间,10位机器码,12位序列.
 * @date 2023/5/9
 * @since JDK 1.8
 */
@Slf4j
@Component
public class SnowFlakeUtil {

    /**
     * 初始时间戳
     */
    public static final long EPOCH;

    /**
     * 时间戳的字节数量
     */
    private static final long TIMESTAMP_BIT_COUNT = 41L;

    /**
     * 机器码的字节数量
     */
    private static final long WORKER_ID_BIT_COUNT = 10L;


    /**
     * 序列的字节数量
     */
    private static final long SEQUENCE_BIT_COUNT = 12L;

    /**
     * 机器码向左平移位数
     */
    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BIT_COUNT;

    /**
     * 时间戳向左平移位数
     */
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BIT_COUNT;


    /**
     * 机器码最大值
     */
    private static final long WORKDER_ID_MAX_VALUE = 1023L;


    /**
     * 序列最大值
     */
    private static final long SEQUENCE_MAX_VALUE = 4095L;


    /**
     * 一天的毫秒数
     */
    private static final long MILLI_SECOND_OF_ONE_DAY = 24 * 60 * 60 * 1000L;

    /**
     * 最后一次使用的时间戳
     * 这个是否需要持久化,分布式系统写到redis里？
     */
    private long lastTimeStamp;

    /**
     * 序列值
     */
    private long sequence;

    /**
     * 序列的初始值偏移量,如果id都是每毫秒生成一个,防止生成的后几位都是0.
     */
    private long sequenceOffset = -1;


    static {
        //初始化时间戳
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.MAY, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }

    public static void main(String[] args) throws InterruptedException {
        snowflakeInfo();
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil();
        Comparable<?> comparable = snowFlakeUtil.generateKey();
        log.info(comparable.toString());
    }

    /**
     * generateKey:
     * 雪花算法:生成分布式ID
     * 生成的为long型数值,如果返回给前端会造成js number精度丢失,可以考虑返回前端字符串。
     *
     * @return java.lang.Comparable<?>
     * @author hui
     * @version 1.0
     * @date 2023/5/12 0:30
     */
    public synchronized Comparable<?> generateKey() throws InterruptedException {
        long currentMilliseconds = getCurrentTimeMillis();
        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
            currentMilliseconds = getCurrentTimeMillis();
        }
        if (lastTimeStamp == currentMilliseconds) {
            //如果sequence==0L,说明sequence==4096超出了sequence的最大值,所以得等待下一毫秒
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MAX_VALUE)) {
                currentMilliseconds = waitUntilNextTime(currentMilliseconds);
            }
        } else {
            vibrateSequenceOffset();
            sequence = sequenceOffset;
        }
        lastTimeStamp = currentMilliseconds;
        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (getWorkerId() << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    /**
     * waitTolerateTimeDifferenceIfNeed:
     * 如果时钟回拨,则一直等待到上次生成id的时间才继续生成id。
     * 如果等待时间超过max.tolerate.time.difference.milliseconds时间(默认10s),则会抛异常。
     *
     * @param currentMilliseconds
     * @return boolean
     * @author hui
     * @version 1.0
     * @date 2023/5/12 1:33
     */
    private boolean waitTolerateTimeDifferenceIfNeed(long currentMilliseconds) throws InterruptedException {
        try {
            if (this.lastTimeStamp <= currentMilliseconds) {
                return false;
            } else {
                long timeDifferenceMilliseconds = this.lastTimeStamp - currentMilliseconds;
                Preconditions.checkState(timeDifferenceMilliseconds < (long) this.getMaxTolerateTimeDifferenceMilliseconds(), "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", new Object[]{this.lastTimeStamp, currentMilliseconds});
                Thread.sleep(timeDifferenceMilliseconds);
                return true;
            }
        } catch (Throwable var5) {
            throw var5;
        }
    }

    /**
     * getWorkerId:
     * 从配置文件中获取WorkerId 默认1,【0-1023】否则报错
     * @author hui
     * @version 1.0
     * @return long
     * @date 2023/5/12 1:46
     */
    private long getWorkerId() {
        long result = SnowflakeProperties.workerId;
        Preconditions.checkArgument(result >= 0L && result <= WORKDER_ID_MAX_VALUE);
        return result;
    }

    /**
     * getMaxVibrationOffset:
     * 获取雪花算法序列初始值最大偏移量配置 默认1
     * @author hui
     * @version 1.0
     * @return int
     * @date 2023/5/12 1:47
     */
    private int getMaxVibrationOffset() {
        int result = SnowflakeProperties.maxVibrationOffset;
        Preconditions.checkArgument(result >= 0 && (long) result <= 4095L, "Illegal max vibration offset");
        return result;
    }

    /**
     * getMaxTolerateTimeDifferenceMilliseconds:
     * 获取时间回拨最大等待时间 默认10s
     * @author hui
     * @version 1.0
     * @return int
     * @date 2023/5/12 1:48
     */
    private int getMaxTolerateTimeDifferenceMilliseconds() {
        return SnowflakeProperties.maxTolerateTimeDifferenceMilliseconds;
    }

    /**
     * waitUntilNextTime:
     * 同一毫秒生成的时间序列满了，等到下个时间，其实就是等下一毫秒。
     * @author hui
     * @version 1.0
     * @param lastTime
     * @return long
     * @date 2023/5/12 1:49
     */
    private long waitUntilNextTime(long lastTime) {
        long result;
        for (result = getCurrentTimeMillis(); result <= lastTime; result = getCurrentTimeMillis()) {
        }

        return result;
    }

    /**
     * vibrateSequenceOffset:
     * 序列初始值偏移,如果没到初始值最大偏移量,则初始值每次自增 1
     * @author hui
     * @version 1.0
     * @return void
     * @date 2023/5/12 1:50
     */
    private void vibrateSequenceOffset() {
        this.sequenceOffset = this.sequenceOffset >= this.getMaxVibrationOffset() ? 0 : this.sequenceOffset + 1;
    }

    /**
     * SnowflakeInfo:
     * 日志输出雪花算法的一些信息
     *
     * @return void
     * @author hui
     * @version 1.0
     * @date 2023/5/10 15:18
     */
    private static void snowflakeInfo() {
        //可以使用约69.73年
        log.info("Snowflake可以使用的时间:{}年", Math.pow(2, TIMESTAMP_BIT_COUNT) / MILLI_SECOND_OF_ONE_DAY / 365);
        //最大数量1024,最大值1023
        log.info("Snowflake机器码可以使用的数量:{}个", Math.pow(2, WORKER_ID_BIT_COUNT));
        //最大数量4096,最大值4095
        log.info("Snowflake相同时间和机器可生成序列数量:{}个", Math.pow(2, SEQUENCE_BIT_COUNT));
    }

    /**
     * currentTimestamp:
     * 获取当前毫秒时间戳
     *
     * @return long
     * @author hui
     * @version 1.0
     * @date 2023/5/10 17:13
     */
    private long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

}
