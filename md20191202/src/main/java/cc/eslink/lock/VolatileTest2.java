package cc.eslink.lock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@ClassName VolatileTest2
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/31 16:05
 *@Version 1.0
 **/
public class VolatileTest2 {

//    public volatile int inc = 0;

    public AtomicInteger inc = new AtomicInteger();

    Lock lock = new ReentrantLock();

//    public void increase() {
//        inc++;
//    }

    // 方法1：synchronized关键字
//    public synchronized void increase() {
//        inc++;
//    }

    // 方法2：lock
//    public void increase() {
//        lock.lock();
//        try {
//            inc++;
//        } finally {
//            lock.unlock();
//        }
//    }

    public void increase() {
        inc.getAndIncrement();
    }


    @Test
    public void test() throws InterruptedException {
        final VolatileTest2 test = new VolatileTest2();
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                for (int j = 0; j < 700; j++) {
                    test.increase();
                    System.out.println("current:" + test.inc);
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println("finally:" + test.inc);
    }
}
