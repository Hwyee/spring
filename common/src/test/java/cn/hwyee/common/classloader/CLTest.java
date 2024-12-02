package cn.hwyee.common.classloader;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName CLTest
 * @description
 * @date 2024/12/2
 * @since JDK 1.8
 */
@Slf4j
public class CLTest {
    public static void main(String[] args) throws IOException {
        //j2ee javaee jakartaEE
        File file = new File("config/lib/spring-web-6.0.2.jar");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{
                file.toURI().toURL()
        });
        Enumeration<URL> resources = urlClassLoader.getResources("META-INF/services/jakarta.servlet.ServletContainerInitializer");
        while (resources.hasMoreElements()){
            System.out.println(resources.nextElement().getPath().toString());

        }
    }
}
