package cn.hwyee.common.lock;

import cn.hwyee.common.util.Log;
import cn.hwyee.common.util.ThreadPool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName JUC LOCK
 * @description JUC
 * @date 2024/5/17
 * @since JDK 1.8
 */
@Slf4j
public class JUC {
    int count = 0;
    public static void main(String[] args) throws Exception {
        JUC juc = new JUC();
//        juc.countDownLatchTest();
//        juc.semaphoreTest();
        juc.cyclicBarrierTest();
    }

    /**
     * countDownLatchTest:
     * 1等多或多等多个线程执行完毕
     * @author hui
     * @version 1.0
     * @return void
     * @date 2024/5/17 22:42
     */
    public void countDownLatchTest() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            ThreadPool.LOCK.execute(() -> {
                Log.printlog("executing");
                cdl.countDown();
            });
        }
        cdl.await();
        Log.printlog("thread executing ending...");
    }

    /**
     * semaphoreTest:
     * 共享锁，最多允许多个线程同时执行
     * @author hui
     * @version 1.0
     * @return void
     * @date 2024/5/17 22:43
     */
    public void semaphoreTest() throws Exception {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            ThreadPool.LOCK.execute(() -> {
                Log.printlog("executing");
                try {
                    semaphore.acquire();
                    Log.printlog("semaphore acquired");
                    Thread.sleep(1000);
                    Log.printlog(String.valueOf(count++));
                    semaphore.release();
                    Log.printlog("semaphore released");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * CyclicBarrierTest:
     * 所有线程都达到某个状态再往下执行
     * 多等多
     * 有足够的线程调用了 await() 方法以达到设定的屏障数，那么所有等待的线程将被释放,然后重置栅栏
     * @author hui
     * @version 1.0
     * @return void
     * @date 2024/5/17 22:42
     */
    public void cyclicBarrierTest() throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);//等待数不足则阻塞
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(11);//等待数不足则阻塞
        for (int i = 0; i < 10; i++) {
            ThreadPool.LOCK.execute(() -> {
                Log.printlog("executing");
                try {
                    cyclicBarrier.await();
                    Log.printlog("cyclicBarrier awaited");
                    Thread.sleep(1000);
                    Log.printlog(String.valueOf(count++));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
