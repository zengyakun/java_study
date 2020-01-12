package cc.eslink;

import org.junit.Test;

/**
 *@ClassName VolatileVisibility
 *@Description
 * volatile本身并不对数据运算处理维持原子性，强调的是读写及时影响主存。
 *@Author zeng.yakun (0178)
 *@Date 2020/1/12 15:56
 *@Version 1.0
 **/
public class VolatileVisibility {
    public static class TestData {
        volatile
        int num = 0;

//        synchronized
        public void updateNum() {
            num++;
        }
    }

    // volatile可见性
    @Test
    public void test() {
        final TestData testData = new TestData();
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("ChildThread num-->" + testData.num);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testData.updateNum();
                System.out.println("ChildThread update num-->" + testData.num);
            }
        }).start();

        while (testData.num == 0) {
        }

        System.out.println("MainThread num-->" + testData.num);
    }

    // volatile非原子性
    @Test
    public void test2() {
        final TestData testData = new TestData();
        for (int i = 1; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 1000; j++) {
                        testData.updateNum();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("最终结果：" + testData.num);
    }
}
