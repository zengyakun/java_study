package com.eslink.day04;

import org.apache.catalina.startup.SafeForkJoinWorkerThreadFactory;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 *@ClassName WorkStealingPoolTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/14 11:21
 *@Version 1.0
 **/
public class WorkStealingPoolTest {
    // 线程数
    private static final int threads = 10;
    // 用于计数线程是否执行完成
    CountDownLatch countDownLatch = new CountDownLatch(threads);

    /**
     * newFixedThreadPool execute
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * newFixedThreadPool submit submit
     */
    @Test
    public void test2() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//            Callable 带返回值
            executorService.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * newFixedThreadPool submit Callable
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//          Runnable 带返回值
            FutureTask<?> futureTask = new FutureTask<>(new Callable<String>() {
                /**
                 * call
                 * @return currentThreadName
                 */
                @Override
                public String call() {
                    return Thread.currentThread().getName();
                }
            });
            executorService.submit(new Thread(futureTask));
            System.out.println(futureTask.get());
        }
        System.out.println("---- end ----");
    }

    @Test
    public void test4() throws InterruptedException {
        System.out.println("--------start-------");
        ForkJoinPool executor = (ForkJoinPool) Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(100) * 1000);
                        System.out.println(LocalTime.now().toString() + "," + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long a = executor.getQueuedTaskCount();
                    int b = executor.getPoolSize();
                    int c = executor.getActiveThreadCount();
                    long d = executor.getStealCount();
                    int e = executor.getRunningThreadCount();
                    int f = executor.getParallelism();
                    System.out.println(a + "," + b + "," + c + "," + d + "," + e + "," + f);
                }
            }
        }).start();
        countDownLatch.await();
        for (int i = 0; i < 100; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(50000);
        System.out.println("--------end-------");
    }

    @Test
    public void test5() throws InterruptedException {
        System.out.println("--------start-------");
        ForkJoinPool executor = new ForkJoinPool(3, new SafeForkJoinWorkerThreadFactory() {
            @Override
            public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
                ForkJoinWorkerThread thread = super.newThread(pool);
                System.out.println("thread:" + thread.getId() + "," + thread.getName() + "," + thread.getPoolIndex());
                return thread;
            }
        }, null, false);

        for (int i = 0; i < threads; i++) {
            ForkJoinTask<?> t = executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10 * 1000);
                        System.out.println(executor.getActiveThreadCount() + "," + executor.getParallelism());
                        System.out.println(LocalTime.now().toString() + "," + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        System.out.println("--------end-------");
    }

    @Test
    public void test6() throws InterruptedException {
        System.out.println("--------start-------" + LocalTime.now().toString());
        long start = System.currentTimeMillis();
        ForkJoinPool executor = (ForkJoinPool) Executors.newWorkStealingPool();
        for (int i = 0; i < 1; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Thread t = Thread.currentThread();
                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean loop = true;
                            while (loop) {
                                try {
                                    // 发送心跳
                                    Thread.sleep(1000);
                                    System.out.println(LocalTime.now().toString() + ",thread:id=" + t.getId() + ",name=" + t.getName()
                                            + ",subThread:id=" + Thread.currentThread().getId() + ",name=" + Thread.currentThread().getName());
                                } catch (InterruptedException e) {
                                    loop = false;
                                }
                            }
                        }
                    });
                    t1.setDaemon(true);
                    t1.start();
                    try {
                        // 模拟执行
//                        Thread.sleep(10 * 1000);
                        List<Demo> list = new ArrayList<>();
                        while (true) {
                            // 模拟内存溢出
                            list.add(new Demo());
//                            if (list.size() % 10000 == 0) {
//                                System.out.println(list.size());
//                            }
                            if (list.size() > 70090000) {
                                System.out.println(list.size());
                            }
                        }
//                        System.out.println(LocalTime.now().toString() + "," + t.getName());
                    } catch (Exception e) {
                        System.out.println("发生异常：");
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
//                        t1.interrupt();
                    }
                }
            });
        }
//        countDownLatch.await();
        System.out.println("开始等待。。。");
        Thread.sleep(50000);
        System.out.println("--------end-------" + LocalTime.now().toString());
        System.out.println("cost:" + (System.currentTimeMillis() - start) + "ms");
    }

    private class Demo {
    }
}
