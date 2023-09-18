package cn.hwyee.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hui
 * @version 1.0
 * @className TimeRecord
 * @description 接口花费时间统计, 统计主流程请求的时间,
 * 子线程获取时间后会持久化到数据库日志表里记录。
 * @date 2023/3/17
 * @since JDK 1.8
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeRecord {
}
