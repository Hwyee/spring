package cn.hwyee.common.util.crypto;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * encode:
     * 文件流 base64编码
     * @author hui
     * @version 1.0
     * @param document
     * @return java.lang.String
     * @date 2023/6/30 13:44
     */
    public static String encode(XWPFDocument document){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            document.write(byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64Encoder.encode(byteArray);
        }catch (Exception e){
            log.error("base64 编码出错:{}",e.getMessage(),e);
            return null;
        }
    }

    /**
     * decode:
     * 解码,返回inputStream.
     * 这个流没有关闭,后续操作需要进行关闭,谨慎使用。
     * @author hui
     * @version 1.0
     * @param code
     * @return java.io.InputStream
     * @date 2023/7/3 14:15
     */
    public static InputStream decode(String code){
        try {
            return new ByteArrayInputStream(Base64Decoder.decode(code.getBytes()));
        }catch (Exception e){
            log.error("base64 编码出错:{}",e.getMessage(),e);
            return null;
        }
    }

}
