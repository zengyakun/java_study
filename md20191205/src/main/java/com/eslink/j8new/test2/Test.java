package com.eslink.j8new.test2;

import java.util.Arrays;
import java.util.function.IntFunction;

/**
 *@ClassName Test
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/10/17 18:01
 *@Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        MyFunc1 myClassCons = MyClass::new;
        MyClass mc = myClassCons.func(100);
        System.out.println("val in mc is: " + mc.getValue());
    }

    @org.junit.Test
    public void test() {
        IntFunction<Integer[]> arrayMaker = Integer[]::new;
        Integer[] array = arrayMaker.apply(10); // 创建数组 int[10]
        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[0]);
        }
        Arrays.asList(array).forEach(System.out::println);
    }

    @org.junit.Test
    public void test2() {
//        String a = new String("abc");
//        String b = "abc";
//        System.out.println(a == "abc");
//        System.out.println(a.equals("abc"));
//
//        System.out.println(b == "abc");
//        System.out.println(b.equals("abc"));


        String s1 = "hello";
        String s2 = "hello";
        String s3 = "he" + "llo";
        String s4 = "hel" + new String("lo");
        String s5 = new String("hello");
        String s6 = s5.intern();
        String s7 = "h";
        String s8 = "ello";
        String s9 = s7 + s8;
        System.out.println(s1==s2);//true
        System.out.println(s1==s3);//true
        System.out.println(s1==s4);//false
        System.out.println(s1==s9);//false
        System.out.println(s4==s5);//false
        System.out.println(s1==s6);//true
    }
}
