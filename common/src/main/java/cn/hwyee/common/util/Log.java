package cn.hwyee.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName Log
 * @description
 * @date 2024/5/17
 * @since JDK 1.8
 */
@Slf4j
public class Log {
    private Log(){

    }

    public static void print(String s) {
        System.out.println("********************" + s + "***************************");
    }

    public static void printlog(String s) {
        log.info("********************" + s + "***************************");
    }

}
