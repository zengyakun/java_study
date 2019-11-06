package cc.eslink.test.function;

import java.util.Date;
import java.util.function.Consumer;

/**
 *@ClassName FunctionTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/8/7 9:36
 *@Version 1.0
 **/
public class FunctionTest {

    public static void main(String[] args) {
        Boolean a = eatFruit(FunctionTest::eatApple);
//        Boolean b = eatFruit(FunctionTest::eatBanana);

//        Consumer c = new Consumer() {
//            @Override
//            public void accept(Object o) {
//                System.out.println(o);
//            }
//        };
//        c.accept(new Date());
//
//        Consumer d = (o) -> {
//            System.out.println(o);
//        };
//
//        Consumer a = (o) -> System.out.println(o);
//
//        Consumer b = System.out::println;
    }

    public static Boolean eatFruit(FunctionVerify<? extends Apple> f) {
        System.out.println("吃水果");
        System.out.println(f);
        return true;
    }

    public static Boolean eatApple(Apple apple) {
        System.out.println("吃苹果");
        System.out.println(apple);
        return true;
    }

    public static Boolean eatBanana(Banana banana) {
        System.out.println("吃香蕉");
        return true;
    }


}
