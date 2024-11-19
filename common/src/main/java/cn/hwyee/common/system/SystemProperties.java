package cn.hwyee.common.system;

import cn.hwyee.common.config.zk.ZooKeeperProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName SystemProperties
 * @description 系统参数
 * @date 2024/3/3
 * @since JDK 1.8
 */
@Component
@ConfigurationProperties(prefix = "system")
@Data
public class SystemProperties {
    /**
     * java.version：Java 运行时环境的版本。
     */
    public static String javaVersion = System.getProperty("java.version");

    /**
     * java.vendor：Java 运行时环境的供应商。
     */
    public static String javaVendor = System.getProperty("java.vendor");

    /**
     * java.home：Java 安装目录。
     */
    public static String javaHome = System.getProperty("java.home");

    /**
     * java.class.path：Java 类路径。
     */
    public static String javaClassPath = System.getProperty("java.class.path");

    /**
     * os.name：操作系统名称
     */
    public static String osName = System.getProperty("os.name");

    /**
     * os.arch：操作系统体系结构。
     */
    public static String osArch = System.getProperty("os.arch");

    /**
     * os.version：操作系统版本。
     */
    public static String osVersion = System.getProperty("os.version");

    /**
     * user.name：当前用户的用户名。
     */
    public static String userName = System.getProperty("user.name");

    /**
     * user.home：当前用户的主目录。
     */
    public static String userHome = System.getProperty("user.home");

    /**
     * user.dir：当前用户的工作目录。
     */
    public static String userDir = System.getProperty("user.dir");

    public void setJavaVersion(String javaVersion) {
        SystemProperties.javaVersion = javaVersion;
    }

    public void setJavaVendor(String javaVendor) {
        SystemProperties.javaVendor = javaVendor;
    }

    public void setJavaHome(String javaHome) {
        SystemProperties.javaHome = javaHome;
    }

    public void setJavaClassPath(String javaClassPath) {
        SystemProperties.javaClassPath = javaClassPath;
    }

    public void setOsName(String osName) {
        SystemProperties.osName = osName;
    }

    public void setOsArch(String osArch) {
        SystemProperties.osArch = osArch;
    }

    public void setOsVersion(String osVersion) {
        SystemProperties.osVersion = osVersion;
    }

    public void setUserName(String userName) {
        SystemProperties.userName = userName;
    }

    public void setUserHome(String userHome) {
        SystemProperties.userHome = userHome;
    }
    public void setUserDir(String userDir) {
        SystemProperties.userDir = userDir;
    }

    public static String getAllProperties() {
        return javaVersion + "\n" + javaHome + "\n" + javaVendor + "\n" +
                javaClassPath + "\n" + osName + "\n" + osArch + "\n" +
                osVersion + "\n" + userName + "\n" + userHome + "\n" +
                userDir + "\n";
    }

}
