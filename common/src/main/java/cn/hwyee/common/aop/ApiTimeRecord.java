//package cn.hwyee.common.aop;
//
//import com.alibaba.ttl.TransmittableThreadLocal;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * @author hui
// * @version 1.0
// * @className ApiTimeRecord
// * @description 记录接口响应时间, 并存储到TL中
// * @date 2023/3/17
// * @since JDK 1.8
// */
//@Aspect
//@Slf4j
//@Component
//public class ApiTimeRecord {
//
//    /**
//     * start:
//     * 主业务切面,@TimeRecord标注类下的所有方法
//     *
//     * @return void
//     * @author hui
//     * @version 1.0
//     * @date 2023/3/30 9:23
//     */
//    @Pointcut("@within(cn.cid.hspp.front.commom.annotation.TimeRecord)")
//    public void start() {
//        //切面方法
//    }
//
//    /**
//     * end:
//     * 多线程存库任务切面
//     *
//     * @return void
//     * @author hui
//     * @version 1.0
//     * @date 2023/3/30 9:24
//     */
//    @Pointcut("execution(public void cn.cid.hspp.front.service.util.*Task.*saveTask(..))")
//    public void end() {
//        //切面方法
//    }
//
//
//    public static final TransmittableThreadLocal<Long> TTL = new TransmittableThreadLocal<>();
//    public static final ThreadLocal<String> TL = new ThreadLocal<>();
//
//    /**
//     * startBefore:
//     * 记录主业务开始时间
//     *
//     * @return void
//     * @author hui
//     * @version 1.0
//     * @date 2023/3/30 9:25
//     */
//    @Before("start()")
//    public void startBefore() {
//        TTL.set(System.currentTimeMillis());
//    }
//
//    /**
//     * endBefore:
//     * 主业务耗时=存库任务开始-主业务开始时间
//     *
//     * @return void
//     * @author hui
//     * @version 1.0
//     * @date 2023/3/30 9:26
//     */
//    @Before("end()")
//    public void endBefore() {
//        String timeRecord = String.valueOf(System.currentTimeMillis() - TTL.get());
//        TL.set(timeRecord);
//        log.info("主业务接口响应耗时：{}毫秒", timeRecord);
//    }
//
//}
