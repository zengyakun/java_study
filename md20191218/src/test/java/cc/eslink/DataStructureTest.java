package cc.eslink;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *@ClassName DataStructureTest
 *@Description java 常用数据结构
 *@Author zeng.yakun (0178)
 *@Date 2020/1/12 10:47
 *@Version 1.0
 **/
public class DataStructureTest {

    @Test
    public void test() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
    }

    @Test
    public void test2() {
        Stack<Integer> s = new Stack<>();
//        s.add(1);
        s.empty();
        s.peek();
        s.pop();
        s.push(1);
        s.search(2);

        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue(10);
//        q.add(1);
        q.remove();
        q.poll();
        q.add(1);
        q.element();
        q.offer(1);
        q.peek();
    }

    @Test
    public void test3() {
        LinkedHashMap<String, String> lm = new LinkedHashMap<>();
        lm.put("hello", "world");
    }
}
