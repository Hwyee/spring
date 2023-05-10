package cn.hwyee.common.util;

import lombok.extern.slf4j.Slf4j;

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
public class SnowFlakeUtil {

    private SnowFlakeUtil(){}

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
     * 一天的毫秒数
     */
    private static final long MILLI_SECOND_OF_ONE_DAY = 24 * 60 * 60 * 1000L;

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

    public static void main(String[] args) {
        snowflakeInfo();
        Comparable<?> comparable = SnowFlakeUtil.generateKey();
        System.out.println(comparable);
    }

    public static synchronized Comparable<?> generateKey() {
        long currentMilliseconds = getCurrentTimeMillis();
//        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
//            currentMilliseconds = timeService.getCurrentMillis();
//        }
//        if (lastMilliseconds == currentMilliseconds) {
//            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
//                currentMilliseconds = waitUntilNextTime(currentMilliseconds);
//            }
//        } else {
//            vibrateSequenceOffset();
//            sequence = sequenceOffset;
//        }
//        lastMilliseconds = currentMilliseconds;
        long sequence = 0L;
        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (getWorkerId() << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    private static long getWorkerId() {
        return 0L;
    }

    /**
     * SnowflakeInfo:
     * 日志输出雪花算法的一些信息
     * @author hui
     * @version 1.0
     * @return void
     * @date 2023/5/10 15:18
     */
    private static void snowflakeInfo(){
        log.info("Snowflake可以使用的时间:{}年",Math.pow(2,TIMESTAMP_BIT_COUNT)/MILLI_SECOND_OF_ONE_DAY/365);
        log.info("Snowflake机器码可以使用的数量:{}个",Math.pow(2,WORKER_ID_BIT_COUNT));
        log.info("Snowflake相同时间和机器可生成序列数量:{}个",Math.pow(2,SEQUENCE_BIT_COUNT));
    }

    /**
     * currentTimestamp:
     * 获取当前毫秒时间戳
     * @author hui
     * @version 1.0
     * @return long
     * @date 2023/5/10 17:13
     */
    private static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

}
