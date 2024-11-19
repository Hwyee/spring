package cn.hwyee.common.lock;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName DCL
 * @description 单例模式 Double Check Lock
 * @date 2024/5/16
 * @since JDK 1.8
 */
public class DCL {
    private static volatile DCL instance;

    private DCL() {
    }

    public static DCL getInstance() {
        if (instance == null) {
            synchronized (DCL.class) {
                if (instance == null) {
                    instance = new DCL();
                }
            }
        }
        return instance;
    }
}

class DCL_NoVolatail {
    private static DCL_NoVolatail instance;

    private DCL_NoVolatail() {
    }

    public static DCL_NoVolatail getInstance() {
        if (instance == null) {
            synchronized (DCL_NoVolatail.class) {
                if (instance == null) {
                    instance = new DCL_NoVolatail();
                }
            }
        }
        return instance;
    }
}
