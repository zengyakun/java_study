package com.eslink.j8new.test;

import java.util.Arrays;

/**
 *@ClassName Test
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/10/17 17:58
 *@Version 1.0
 **/
public class Test {

    public static <T> int myOp(MyFunc<T> f, T[] vals, T v) {
        return f.func(vals, v);
    }

    public static void main(String[] args) {
        Integer[] vals = {1, 2, 3, 4, 2, 3, 4, 4, 5};
        String[] strs = {"One", "Two", "Three", "Two"};
        int count;
        count = myOp(MyArrayOps::<Integer>countMatching, vals, 4);
        System.out.println("vals contains " + count + " 4s");
        count = myOp(MyArrayOps::<String>countMatching, strs, "Two");
        System.out.println("strs contains " + count + " Twos");
    }

    @org.junit.Test
    public void test() {
        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);
    }
}
