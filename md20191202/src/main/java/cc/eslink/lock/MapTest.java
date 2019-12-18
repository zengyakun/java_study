package cc.eslink.lock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 *@ClassName MapTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/31 14:18
 *@Version 1.0
 **/
public class MapTest {

//        private static Map<String, String> map = new HashMap<>();
    private static Map<String, String> map = new ConcurrentHashMap<>();

    private static CountDownLatch latch;

    @Before
    public void before() {
        for (int i = 0; i < 100000; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
    }

    @After
    public void after() {

    }

    @Test
    public void test() throws InterruptedException {
        System.out.println("mapSizes:" + map.size());
        int loop = 100;
        latch = new CountDownLatch(loop);
        // 开启多个线程随机删除几个key
        int size = map.size();
        Set<String> delKeys = new HashSet<>();
        for (int i = 0; i < loop; i++) {
            String delKey = String.valueOf(new Random().nextInt(size));
            delKeys.add(delKey);
            new DelThread(delKey).start();
        }
        latch.await();
        System.out.println("delKeys:" + delKeys.size());
        List<String> list = new ArrayList<>(delKeys);
        list.sort(Comparator.comparing(Integer::valueOf));
        System.out.println(list);
        System.out.println("mapSizes2:" + map.size());
    }

    public static class DelThread extends Thread {

        public String delKey;

        public DelThread(String delKey) {
            this.delKey = delKey;
        }

        @Override
        public void run() {
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (delKey.equals(key)) {
                    iterator.remove();
                    map.remove(key);
                }
            }
            latch.countDown();
        }
    }
}
