package com.eslink.j8new;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName Test
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/10/17 13:41
 *@Version 1.0
 **/
public class Test {

    @org.junit.Test
    public void test2() throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        delayQueue.put(new DelayElement(1000));
        delayQueue.put(new DelayElement(3000));
        delayQueue.put(new DelayElement(4000));
        System.out.println("开始时间：" + Instant.now());
        while (!delayQueue.isEmpty()) {
            System.out.println(delayQueue.take());
        }
        System.out.println("结束时间：" + Instant.now());
    }

    @org.junit.Test
    public void test() {
        Queue<String> linkedList = new LinkedList<>();
        linkedList.add("Dog");
        linkedList.add("Camel");
        linkedList.add("Cat");
        while (!linkedList.isEmpty()) {
//            System.out.println(linkedList.poll());
            System.out.println(linkedList.remove());
        }

        System.out.println(Integer.MAX_VALUE);
    }

    static class DelayElement implements Delayed {

        long delayTime = System.currentTimeMillis();

        public DelayElement(long delayTime) {
            this.delayTime = this.delayTime + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long l = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            if (l > 0) {
                return 1;
            } else if (l < 0) {
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return Instant.ofEpochMilli(delayTime).toString();
//            return DateFormat.getDateInstance().format(new Date(delayTime));
        }
    }
}
