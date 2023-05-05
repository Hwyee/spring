package cn.hwyee.provider.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * @author hui
 * @version 1.0
 * @className PackageInfoService
 * @description
 * @date 2023/5/5
 * @since JDK 1.8
 */
@Slf4j
public class PackageInfoService {
    public static void main(String[] args) {
        log.info("包静态常量PACKAGE_NAME:{}",PackageArgs.PACKAGE_NAME);
        Package packageInfo = PackageInfoService.class.getPackage();
        Annotation[] annotations = packageInfo.getAnnotations();
        for (Annotation annotation : annotations) {
            log.info("包注解:{}",annotation.annotationType().getName());
        }
    }
}
