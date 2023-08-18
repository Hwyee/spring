package cn.hwyee.common.util.file;

import com.alibaba.fastjson2.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    /**
     * 空间名
     * 如不存在会报错：no such bucket
     */
    static String bucket = "hwyee";

    public static void main(String[] args) throws QiniuException, UnsupportedEncodingException {
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//        log.info(upToken);
//        String s = uploadFile("G:\\图片\\回到过去.png");
//        log.info(s);
        String downloadUrl = downloadFile("qn.hwyee.cn",true,"回到过去");
        log.info("下载地址:{}",downloadUrl);

        manualDoloadFile();

    }

    private static void manualDoloadFile() throws UnsupportedEncodingException {
        String fileName = "回到过去";

        String domainOfBucket = "https://qn.hwyee.cn";

        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");

        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);


        Auth auth = Auth.create(accessKey, secretKey);

        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间

        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        log.info("下载地址:{}",finalUrl);
    }

    public static String uploadFile(String path) {
        //构造一个带指定 Region 对象的配置类,要配置对应空间的Region,否则会报错。或者使用autoRegion
        Configuration cfg = new Configuration(Region.autoRegion());
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;

        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);

        //...生成上传凭证，然后准备上传

        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = path;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "回到过去";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {

            Response response = uploadManager.put(localFilePath, key, upToken);

            //解析上传成功的结果

            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);

            log.info(putRet.key);

            log.info(putRet.hash);
            return response.toString();

        } catch (QiniuException ex) {

            Response r = ex.response;

            log.error(r.toString());

            try {
                log.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }

        }
        return null;
    }


    public static String downloadFile(String domain,boolean useHttps,String key) throws QiniuException {
        // domain   下载 domain, eg: qiniu.com【必须】

        // useHttps 是否使用 https【必须】

        // key      下载资源在七牛云存储的 key【必须】

        DownloadUrl url = new DownloadUrl(domain, useHttps, key);

        // 配置 attname 配置 fop 配置 style
//        url.setAttname(attname)
//                .setFop(fop)
//                .setStyle(style, styleSeparator, styleParam) ;

        // 带有效期

        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间

        long deadline = System.currentTimeMillis()/1000 + expireInSeconds;

        Auth auth = Auth.create(accessKey, secretKey);

        String urlString = url.buildURL(auth, deadline);

        System.out.println(urlString);
        return urlString;
    }

}
