package cc.eslink.test3;

import java.lang.ref.SoftReference;

/**
 *@ClassName SoftReferenceTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/29 17:35
 *@Version 1.0
 **/
public class SoftReferenceTest {

    public static void main(String[] args) {
        String s = new String("test");
        SoftReference<String> r = new SoftReference<>(s);
        s = null;
        System.gc();
        System.out.println("s=" + r.get());
    }
}
