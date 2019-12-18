package cc.eslink.test3;

import java.lang.ref.WeakReference;

/**
 *@ClassName WeakReferenceTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/29 17:39
 *@Version 1.0
 **/
public class WeakReferenceTest {

    public static void main(String[] args) {
        String s = new String("test");
        WeakReference<String> r = new WeakReference<>(s);
        s = null;
        System.gc();
        System.out.println("isEnQueued " + r.isEnqueued());
        System.out.println("s=" + r.get());

    }
}
