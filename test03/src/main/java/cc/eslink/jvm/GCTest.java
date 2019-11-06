package cc.eslink.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName GCTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/30 10:39
 *@Version 1.0
 **/
public class GCTest {

    public static void main(String[] args) {
        Object obj = new Object();
        System.gc();
        System.out.println();
        obj = new Object();
        obj = new Object();
        System.gc();
        System.out.println();

        String s = "abc";

        List<String> list = new ArrayList<>(10);
        list.add(0, "a");
        list.add(11, "b");
        System.out.println(list);
    }
}
