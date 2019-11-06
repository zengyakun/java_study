package cc.eslink.lock;

import java.util.concurrent.CountDownLatch;

/**
 *@ClassName VolatileTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/31 15:50
 *@Version 1.0
 **/
public class VolatileTest {

    private static volatile boolean stop = false;

    private static final CountDownLatch LATCH = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    System.out.println("current:" + System.currentTimeMillis());
                    try {
                        Thread.sleep((long) (100 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                LATCH.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long) (10000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stop = true;
                System.out.println("stop了");
                LATCH.countDown();
            }
        }).start();


        LATCH.await();
        System.out.println("end");
    }
}
