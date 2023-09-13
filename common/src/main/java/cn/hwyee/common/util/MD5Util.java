package cn.hwyee.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author hui
 * @version 1.0
 * @className MD5Util
 * @description
 * @date 2023/9/13
 * @since JDK 1.8
 */
@Slf4j
public class MD5Util {
    public static void main(String[] args) {
        System.out.println(encode("123456"));
        System.out.println(encode("123456"));
    }
    public static String encode(String code){
        String encode = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(code.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, digest);
            encode = number.toString(16);
            //MD5哈希值的长度是32个字符，如果我们的哈希值长度不足32个字符，我们需要在前面添加零，直到它的长度为32个字符。
            while (encode.length() < 32) {
                encode = "0" + encode;
            }
        }catch (Exception e){
            log.error("加密失败：{}",e);
            return null;
        }
        return encode;
    }
}
