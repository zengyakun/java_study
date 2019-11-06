package com.eslink.j8new.test;

/**
 *@ClassName MyArrayOps
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/10/17 17:56
 *@Version 1.0
 **/
public class MyArrayOps {

    public static <T> int countMatching(T[] vals, T v) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == v) count++;
        }
        return count;
    }
}
