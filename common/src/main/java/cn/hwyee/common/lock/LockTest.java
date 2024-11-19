package cn.hwyee.common.lock;

import cn.hwyee.common.util.ThreadPool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static cn.hwyee.common.util.Log.print;
import static cn.hwyee.common.util.Log.printlog;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName LockTest
 * @description
 * @date 2024/5/7
 * @since JDK 1.8
 */
@Slf4j
public class LockTest {
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = ThreadPool.LOCK;
    private final ReentrantLock lock = new ReentrantLock();
    private static int count = 0;

    private static final Object o = new Object();

    private final Object a = new Object();
    private final Object b = new Object();

    private AtomicInteger aI = new AtomicInteger(0);



    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LockTest lockTest = new LockTest();
        /*
         * 无锁测试，无序
         */
//        lockTest.testNoLock();
        /*
         * 有锁测试，有序 ReentrantLock
         */
//        lockTest.testReentrantLock();

        /*
         * synchronized 测试
         */
//        lockTest.SynchronizerTest();

        /*
         * deadLock 测试
         */
//        lockTest.deadLockTest();

        /*
         * wait notify 测试
         */
//        Thread.sleep(15000);
//        lockTest.waitNotifyTest();

        /*
         * wait not notify 测试
         * 线程会阻塞
         */
//        lockTest.waitNoNotify();

        /*
         * await signal 测试
         */
        lockTest.awaitSignalTest();

        /*
         * wait not notify 测试
         * 锁的对象是线程，看线程结束后会不会notify
         * 线程不会阻塞
         */
//        lockTest.waitNoNotify_LockThreadInstance();


//        Thread.sleep(10);
//        synchronized (o) {
//            print(ClassLayout.parseInstance(o).toPrintable());
//        }

//        THREAD_POOL_EXECUTOR.shutdown();

        /**
         * join()
         * 等待线程完成后再执行操作
         */
//        lockTest.joinTest();

        /**
         * yield 不会释放锁
         * 让出线程，但是调度器可以忽略这个提示，不一定让出成功
         * 或者让出后，自己又抢到了
         */
//        lockTest.yieldTest();


    }

    private void awaitSignalTest() {
        AtomicBoolean flag = new AtomicBoolean(true);
        Condition condition = lock.newCondition();
        THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                lock.lock();
                printlog("awaitSignalTest a thread start...");
                while (flag.get()){
                    condition.await();
                }
                printlog("awaitSignalTest a thread ending...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                lock.lock();
                printlog("awaitSignalTest b thread start...");
                flag.set(false);
                condition.signal();
                printlog("awaitSignalTest b thread ending...");
            } finally {
                lock.unlock();
            }
        });
    }

    private void yieldTest() throws InterruptedException {
        THREAD_POOL_EXECUTOR.execute(() -> {
            for (int i = 0; i < 10; i++) {
                printlog("yield test sub thread start..." + i);
                if (i == 3) {
                    Thread.yield();
                }
            }
        });
        THREAD_POOL_EXECUTOR.execute(() -> {
            for (int i = 0; i < 10; i++) {
                printlog("i got it..." + i);
            }
        });

    }


    /**
     * joinTest:
     * 线程池用不了join，获取不了线程对象
     *
     * @return void
     * @author hui
     * @version 1.0
     * @date 2024/5/17 14:24
     */
    public void joinTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                printlog("join test sub thread start...");
                Thread.sleep(200);
                printlog("join test sub thread ending...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        printlog("join test main thread start...");
        //同步方法
        thread.join();
        printlog("join test main thread ending...");
    }



    public void testNoLock() throws ExecutionException, InterruptedException {
        print("testNoLock start");
        CompletableFuture<Void>[] objectCompletableFuture = new CompletableFuture[10];
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                count++;
                log.info("testNoLock " + count);
            }, THREAD_POOL_EXECUTOR);
            objectCompletableFuture[i] = voidCompletableFuture;
        }
        CompletableFuture.allOf(objectCompletableFuture).join();
        print("testNoLock end");
    }

    public void testReentrantLock() {
        print("testReentrantLock start");
        for (int i = 0; i < 10; i++) {
            THREAD_POOL_EXECUTOR.execute(() -> {
                lock.lock();
                try {
                    count++;
                    log.info("testReentrantLock " + count);
                } finally {
                    lock.unlock();
                }
            });
        }
    }

    /**
     * SynchronizerTest:
     * jdk1.6，锁升级，无锁，偏向锁，轻量级锁，重量级锁。
     *
     * @return void
     * @author hui
     * @version 1.0
     * @date 2024/5/15 16:27
     */
    public void SynchronizerTest() {
        print("SynchronizerTest start");
        for (int i = 0; i < 1; i++) {
            THREAD_POOL_EXECUTOR.execute(() -> {
                synchronized (o) {
                    count++;
                    try {
                        print("SynchronizerTest rdy waiting");
                        o.wait();
                        print("SynchronizerTest waiting end");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("SynchronizerTest " + count);
                }
            });
        }
    }

    public void deadLockTest() {
        THREAD_POOL_EXECUTOR.execute(() -> {
            synchronized (a) {
                try {
                    print("a rdy waiting starting");
                    Thread.sleep(1000);
                    print("a rdy waiting ending");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                print("try get b lock...");
                synchronized (b) {
                    log.info("a");
                }
            }
        });

        THREAD_POOL_EXECUTOR.execute(() -> {
            synchronized (b) {
                try {

                    print("b rdy waiting starting");
                    Thread.sleep(1000);
                    print("b rdy waiting ending");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                print("try get a lock...");
                synchronized (a) {
                    log.info("b");
                }
            }
        });

    }

    public void waitNoNotify() throws InterruptedException {
        THREAD_POOL_EXECUTOR.submit(() -> {
            try {
                synchronized (o) {
                    printlog("a waitNoNotify waiting starting..");
                    o.wait();
                    printlog("a waitNoNotify waiting ending..");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.sleep(1000);
        THREAD_POOL_EXECUTOR.submit(() -> {
            try {
                synchronized (o) {
                    printlog("a waitNoNotify notify starting..");
//                        o.notify();
                    printlog("a waitNoNotify notify ending..");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * waitNoNotify_LockThreadInstance:
     * thread pool内部用的锁对象是thread实例，
     * 测试当thread实例结束后，会自动notifyall
     *
     * @return void
     * @author hui
     * @version 1.0
     * @date 2024/5/17 14:47
     */
    public void waitNoNotify_LockThreadInstance() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                printlog("thread instance starting..");
                Thread.sleep(2000);
                printlog("thread instance ending..");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        THREAD_POOL_EXECUTOR.submit(() -> {
            try {
                synchronized (thread) {
                    printlog("thread pool waiting starting..");
                    o.wait();
                    printlog("thread pool waiting ending..");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }


    /**
     * waitNotifyTest:
     * 交替打印
     *
     * @return void
     * @author hui
     * @version 1.0
     * @date 2024/5/15 18:12
     */
    private void waitNotifyTest() throws ExecutionException, InterruptedException {
        THREAD_POOL_EXECUTOR.submit(() -> {
            try {
                for (; aI.get() < 10; aI.incrementAndGet()) {
                    synchronized (o) {
                        while (aI.get() % 2 == 0) {
                            o.wait();
                        }
                        printlog(String.valueOf(aI.get()));
                        o.notify();
                    }
                }
                Thread.sleep(2000);
                print("a ending");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        THREAD_POOL_EXECUTOR.submit(() -> {
            try {
                for (; aI.get() < 10; aI.incrementAndGet()) {
                    synchronized (o) {
                        while (aI.get() % 2 != 0) {
                            o.wait();
                        }
                        printlog(String.valueOf(aI.get()));
                        o.notify();
                    }
                }
                print("b ending");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }


}
