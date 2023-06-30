package cn.hwyee.common.util.crypto;

import cn.hutool.core.codec.Base64Encoder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * @author hui
 * @version 1.0
 * @className Base64Util
 * @description
 * @date 2023/6/30
 * @since JDK 1.8
 */
@Slf4j
public class Base64Util {
    public static void main(String[] args) throws IOException {
        //jdk1.8 可以这样写
//        BASE64Encoder base64Encoder = new BASE64Encoder();
        //最好这样写,jdk9去除了以上类。
        Base64.Encoder base64Encoder = Base64.getEncoder();
        byte[] bytes = Files.readAllBytes(new File("./config/zhangsan.png").toPath());
        String encode = base64Encoder.encodeToString(bytes);
        log.info("base64 encoding：{}",encode);

        String url = Base64Encoder.encodeUrlSafe(bytes);
        log.info("base64 url encoding:{}",url);

    }
}
