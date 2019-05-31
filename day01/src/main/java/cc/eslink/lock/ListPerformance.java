package cc.eslink.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author Alfred Xu <alfred.xu@heweisoft.com>
 *
 */
public class ListPerformance {

    private static CountDownLatch cdl;

    private final int threadNumber;

    private static final int totalCount = 100000;

    public ListPerformance(int n) {
        threadNumber = n;
    }

    private static class TestThread implements Runnable {
        private static long totalTime;
        private final int No;
        private final int loop = totalCount;
        private final Thread t;
        private final List<Integer> list;

        TestThread(int No, List<Integer> list) {
            this.No = No;
            this.list = list;
            t = new Thread(this);
        }

        public void start() {
            t.start();
        }

        public synchronized void addTime(long time) {
            totalTime += time;
//            System.out.println(String.format("time:%d, totalTime:%d, getCount:%d", time, totalTime, cdl.getCount()));
        }

        @Override
        public void run() {
            long time = randomAccess();
            addTime(time);
            cdl.countDown();
        }

        @Override
        public String toString() {
            return "Thread " + No + ":";
        }

        public long randomAccess() {
            Date date1 = new Date();
            Random random = new Random();
            for (int i = 0; i < loop; i++) {
                int n = random.nextInt(loop);
                list.get(n);
            }
            Date date2 = new Date();
            long time = date2.getTime() - date1.getTime();
            // System.out.println(this + list.getClass().getSimpleName()
            // + " time:" + time);
            return time;
        }

    }

    public void initList(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(new Integer(i));
        }
    }

    public void test(List<Integer> list) throws InterruptedException {
        cdl = new CountDownLatch(list.size());
        System.out.println("Test List Performance");
        TestThread[] ts = new TestThread[threadNumber];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new TestThread(i, list);
        }
        for (int i = 0; i < ts.length; i++) {
            ts[i].start();
        }
        cdl.await();
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ListPerformance lp = new ListPerformance(totalCount);
        List<Integer> al = Collections
                .synchronizedList(new ArrayList<Integer>());
        lp.initList(al, totalCount);
        lp.test(al);
        System.out.println(al.size());
        System.out.println(TestThread.totalTime);

        TestThread.totalTime = 0;
        CopyOnWriteArrayList<Integer> cl = new CopyOnWriteArrayList<Integer>(al);
        lp.test(cl);
        System.out.println(cl.size());
        System.out.println(TestThread.totalTime);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }
}
