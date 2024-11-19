package cn.hwyee.common.util.net;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.http.HttpRequest;

/**
 * @author hui
 * @version 1.0
 * @className HttpUtil
 * @description
 * @date 2024/11/19
 * @since JDK 1.8
 */
@Slf4j
public class HttpUtil {
    public static RestTemplate restTemplate;
    //毫秒
    public static final int CONNECT_TIMEOUT = 1000 * 60 * 5;

    /**
     * HttpUtil:
     * can't instance.
     *
     * @return
     * @author hui
     * @version 1.0
     * @date 2024/11/19 17:13
     */
    private HttpUtil() {
    }

    public static String sendGet(HttpRequest request) throws IOException {
        HttpURLConnection httpURLConnection = null;
        StringBuilder sb = new StringBuilder();

        try {
            //UrlConnection 创建时不建立链接,调用connect()方法是才建立
            httpURLConnection = (HttpURLConnection) request.uri().toURL().openConnection();
            //链接超时时间
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            httpURLConnection.setReadTimeout(CONNECT_TIMEOUT);
            //请求方法
            httpURLConnection.setRequestMethod("GET");
            //建立链接
            httpURLConnection.connect();
            //获取相应数据
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = httpURLConnection.getInputStream()) {
                    if (inputStream != null) {
                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
                            String temp = null;
                            while (null != (temp = bufferedReader.readLine())) {
                                sb.append(temp);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.info("Exception " + e.getMessage() + e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return sb.toString();
    }


}
