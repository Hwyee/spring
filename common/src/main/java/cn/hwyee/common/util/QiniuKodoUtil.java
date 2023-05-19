package cn.hwyee.common.util;

import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hui
 * @version 1.0
 * @className QiniuKodoUtil
 * @description 七牛云对象存储kodo工具类
 * @date 2023/5/19
 * @since JDK 1.8
 */
@Slf4j
public class QiniuKodoUtil {
    static String accessKey = "v6NxVBuFnKkzrko2X4mNAdNm3cR0mrKZJE26lRl0";
    static String secretKey = "o8igvN1LT_PqCq-f_pkXWMgZQqBCJpO3ZlVW8XK5";
    static String bucket = "bucket name";

    public static void main(String[] args) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
    }

}
