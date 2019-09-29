package cc.eslink.test3;

import java.lang.ref.PhantomReference;

/**
 *@ClassName PhantomReferenceTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/29 17:41
 *@Version 1.0
 **/
public class PhantomReferenceTest {

    public static void main(String[] args) {
        String s = new String("test");
        PhantomReference<String> r = new PhantomReference<>(s, null);
//        s = null;
        System.out.println("isEnQueued " + r.isEnqueued());
        System.out.println(r.get());
    }
}
