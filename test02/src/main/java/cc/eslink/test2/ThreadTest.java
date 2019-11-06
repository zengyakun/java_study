package cc.eslink.test2;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 *@ClassName ThreadTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/26 17:06
 *@Version 1.0
 **/
public class ThreadTest {

    public static void main(String[] args) {
//        Thread.currentThread().setPriority(4);
//        Thread thread = new Thread();
//        System.out.println(thread.getPriority());

        Thread thread = new Thread(() -> {
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            System.out.println(1 / 0);
            System.out.println("error");
        });
        thread.setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
        });
        thread.start();
    }

    @Test
    public void test() {
        Thread thread = new Thread(() -> {
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            System.out.println(1 / 0);
            System.out.println("error");
        });
        thread.setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
        });
        thread.start();
    }

    @Test
    public void test2() {
        System.out.println(1 / 0);
    }


}
