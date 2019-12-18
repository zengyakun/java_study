package com.eslink.day04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *@ClassName ThreadPoolTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/14 10:21
 *@Version 1.0
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                System.out.println("thread id is: " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
