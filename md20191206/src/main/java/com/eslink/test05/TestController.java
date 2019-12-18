package com.eslink.test05;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName TestController
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/14 16:24
 *@Version 1.0
 **/
@RestController
public class TestController {

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            System.out.println(t.getId() + "," + t.getName());
            return t;
        }
    }

    private static ForkJoinPool executor = (ForkJoinPool) Executors.newWorkStealingPool();
    private static ThreadPoolExecutor executor2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(4, new DefaultThreadFactory());


    @RequestMapping("/hello")
    public String hello() {

        return "Hello " + LocalDate.now().toString();
    }

    @RequestMapping("/export")
    public String export(int loop) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
//                Thread t = Thread.currentThread();
//                Thread t1 = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        boolean loop = true;
//                        while (loop && t.isAlive()) {
//                            try {
//                                //-------------------------- 发送心跳
//                                Thread.sleep(1000);
//                                System.out.println(LocalTime.now().toString() + ",thread:id=" + t.getId() + ",name=" + t.getName()
//                                        + ",subThread:id=" + Thread.currentThread().getId() + ",name=" + Thread.currentThread().getName());
//                            } catch (InterruptedException e) {
//                                loop = false;
//                            }
//                        }
//                    }
//                });
////                t1.setDaemon(true);
//                t1.start();

                int count = loop;
                // 模拟导出
                List<SfColumn> list = new ArrayList<>();
                if (count < 0) {
                    System.out.println("无限循环。。。。");
                    while (true) {
                        list.add(new SfColumn());
                        if (list.size() % 1000 == 0) {
                            System.out.println("current size:" + list.size());
                        }
                    }
                } else {
                    System.out.println("有限循环" + count);
                    while (count-- > 0) {
                        try {
                            Thread.sleep(new Random().nextInt(5) * 1000);
                        } catch (Exception e) {
                        }
                        list.add(new SfColumn());
                    }
                }
                System.out.println("--------------------------导出成功--------------------------");
            }
        });
        return "success,总线程数：" + executor.getParallelism() + ",活跃线程数：" + executor.getActiveThreadCount();
    }

    @RequestMapping("/export2")
    public String export2(int loop) {
        executor2.execute(new Runnable() {
            @Override
            public void run() {
//                Thread t = Thread.currentThread();
//                Thread t1 = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        boolean loop = true;
//                        while (loop && t.isAlive()) {
//                            try {
//                                //-------------------------- 发送心跳
//                                Thread.sleep(1000);
//                                System.out.println(LocalTime.now().toString() + ",thread:id=" + t.getId() + ",name=" + t.getName()
//                                        + ",subThread:id=" + Thread.currentThread().getId() + ",name=" + Thread.currentThread().getName());
//                            } catch (InterruptedException e) {
//                                loop = false;
//                            }
//                        }
//                    }
//                });
////                t1.setDaemon(true);
//                t1.start();

                int count = loop;
                // 模拟导出
                List<SfColumn> list = new ArrayList<>();
                if (count < 0) {
                    System.out.println("无限循环。。。。" + Thread.currentThread().getId() + "," + Thread.currentThread().getName());
                    while (true) {
                        list.add(new SfColumn());
//                        if (list.size() % 1000 == 0) {
//                            System.out.println("current size:" + list.size());
//                        }
                    }
                } else {
                    System.out.println("有限循环" + count);
                    while (count-- > 0) {
                        try {
                            Thread.sleep(new Random().nextInt(5) * 1000);
                        } catch (Exception e) {
                        }
                        list.add(new SfColumn());
                    }
                }
                System.out.println("--------------------------导出成功--------------------------");
            }
        });
        return "success,总线程数：" + executor2.getPoolSize() + ",活跃线程数：" + executor2.getActiveCount();
    }

    public static void main(String[] args) {
        List<SfColumn> list = new ArrayList<>(100000);
        System.out.println("无限循环。。。。");
        while (true) {
            list.add(new SfColumn());
            System.out.println("current size:" + list.size());
        }
    }
}
