package cn.hwyee.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName ThreadPool
 * @description
 * @date 2024/5/17
 * @since JDK 1.8
 */
@Slf4j
public class ThreadPool {
    private ThreadPool(){

    }
    @SuppressWarnings("removal")
    public static final ThreadPoolExecutor LOCK = new ThreadPoolExecutor(
            10,
            100,
            1,
            java.util.concurrent.TimeUnit.SECONDS,
            new java.util.concurrent.ArrayBlockingQueue<>(100),
            new ThreadFactory() {
                private static final AtomicInteger poolNumber = new AtomicInteger(1);
                private final ThreadGroup group;
                private final AtomicInteger threadNumber = new AtomicInteger(1);
                private final String namePrefix;

                {
                    SecurityManager s = System.getSecurityManager();
                    group = (s != null) ? s.getThreadGroup() :
                            Thread.currentThread().getThreadGroup();
                    namePrefix = "lock-test-" +
                            poolNumber.getAndIncrement() +
                            "-thread-";
                }

                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(group, r,
                            namePrefix + threadNumber.getAndIncrement(),
                            0);
                    if (t.isDaemon()) {
                        t.setDaemon(false);
                    }
                    if (t.getPriority() != Thread.NORM_PRIORITY) {
                        t.setPriority(Thread.NORM_PRIORITY);
                    }
                    return t;
                }
            });

    static {
        LOCK.allowCoreThreadTimeOut(true);//没有线程jvm会自动退出
    }
}
