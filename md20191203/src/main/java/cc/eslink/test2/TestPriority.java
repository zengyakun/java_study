package cc.eslink.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *@ClassName TestPriority
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/26 17:17
 *@Version 1.0
 **/
public class TestPriority {
    static AtomicLong minTimes = new AtomicLong(0);
    static AtomicLong normTimes = new AtomicLong(0);
    static AtomicLong maxTimes = new AtomicLong(0);

    public static void main(String[] args) {
        List<MyThread> minThreadList = new ArrayList<>();
        List<MyThread> normThreadList = new ArrayList<>();
        List<MyThread> maxThreadList = new ArrayList<>();

        int count = 1000;
        for (int i = 0; i < count; i++) {
            MyThread myThread = new MyThread("min----" + i);
//            myThread.setPriority(Thread.MIN_PRIORITY);
            myThread.setPriority(5);
            minThreadList.add(myThread);
        }
        for (int i = 0; i < count; i++) {
            MyThread myThread = new MyThread("norm---" + i);
//            myThread.setPriority(Thread.NORM_PRIORITY);
            myThread.setPriority(6);
            normThreadList.add(myThread);
        }
        for (int i = 0; i < count; i++) {
            MyThread myThread = new MyThread("max----" + i);
//            myThread.setPriority(Thread.MAX_PRIORITY);
            myThread.setPriority(7);
            maxThreadList.add(myThread);
        }

        for (int i = 0; i < count; i++) {
            maxThreadList.get(i).start();
            normThreadList.get(i).start();
            minThreadList.get(i).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("maxPriority  统计：" + maxTimes.get());
        System.out.println("normPriority 统计：" + normTimes.get());
        System.out.println("minPriority  统计：" + minTimes.get());
        System.out.println("普通优先级与最高优先级相差时间：" + (normTimes.get() - maxTimes.get()) + "ms");
        System.out.println("最低优先级与普通优先级相差时间：" + (minTimes.get() - normTimes.get()) + "ms");

    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " priority: " + this.getPriority());
            switch (this.getPriority()) {
//                case Thread.MAX_PRIORITY:
                case 7:
                    maxTimes.getAndAdd(System.currentTimeMillis());
                    break;
//                case Thread.NORM_PRIORITY:
                case 6:
                    normTimes.getAndAdd(System.currentTimeMillis());
                    break;
//                case Thread.MIN_PRIORITY:
                case 5:
                    minTimes.getAndAdd(System.currentTimeMillis());
                    break;
                default:
                    break;
            }
        }
    }
}
