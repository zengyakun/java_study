package cc.eslink.test.function;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Stream;

/**
 *@ClassName Tests
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/8/7 14:03
 *@Version 1.0
 **/
public class Tests {

    @Test
    public void consumerTest() {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        f.andThen(f2).accept("test");

        System.out.println("--------------------");

        f.andThen(f).andThen(f).andThen(f).accept("test1");
    }

    @Test
    public void functionTest() {
        Function<Integer, Integer> f = s -> ++s;
        Function<Integer, Integer> g = s -> s * 2;

        // 1* 2 -> 2
        // ++2 -> 2
        System.out.println(f.compose(g).apply(1));

        // ++1 -> 2
        // 2 * 2 -> 4
        System.out.println(f.andThen(g).apply(1));

        System.out.println(Function.identity().apply("a"));
    }

    @Test
    public void predicateTest() {
        Predicate<String> p = o -> o.equals("test");
        Predicate<String> g = o -> o.startsWith("t");

        Assert.assertFalse(p.negate().test("test"));
//        Assert.assertTrue(p.negate().test("test"));

        Assert.assertTrue(p.and(g).test("test"));

        Assert.assertTrue(p.or(g).test("ta"));
    }

    @Test
    public void streamTest() {
        Stream.iterate(1,
//                new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//                return false;
//            }
//        },
                new UnaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer i) {
                        return i + 1;
                    }
                }).limit(1000).forEach(System.out::println);
    }

    @Test
    public void streamTest2() {
//        Stream.generate(new Supplier<Double>() {
//            @Override
//            public Double get() {
//                return Math.random();
//            }
//        }).limit(10).forEach(System.out::println);

//        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

//        Stream.of("1,2", "2,3", "4,5", "6").flatMap(new Function<String, Stream<?>>() {
//            @Override
//            public Stream<?> apply(String s) {
//                return Stream.of(s.split(","));
//            }
//        }).distinct().forEach(System.out::println);

        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
        s.flatMap(n -> Stream.of(n.split(""))).forEach(System.out::println);
    }

    @Test
    public void optionalTest() {
        Optional<Object> a = Optional.empty();
        System.out.println(a);
//        a.get();
        a.isPresent();

        Optional<String> b = Optional.of("test");
        System.out.println(b);
        a.get();

        Optional<Object> c = Optional.ofNullable(null);
        System.out.println(c);
        a.get();
    }

    @Test
    public void foo() {
        int i = 0;
        for (int j = 0; j < 50; j++)
            i = i++;
        System.out.println(i);
    }

    public IntFunction<Integer> addNum(int size, ToIntFunction<List<Integer>> fn) {
        final List<Integer> args = new ArrayList<>();
        return value -> {
            int result = -1;
            System.out.println(args.size() + "," + args);
            if (args.size() == size) {
                result = fn.applyAsInt(args);
            } else {
                args.add(value);
            }
            System.out.println(args);
            return result;
        };
    }

    @Test
    public void intFuncTest() {
        //  准备测试对象
        IntFunction<Integer> addFun = this.addNum(3, items -> {
            //  利用reduce进行求值
            return items.stream().reduce(0, (x, y) -> x + y);
        });
        //  方法调用还很生硬，有个莫名其妙的函数名apply，可能会引起业务的误解
        System.out.println(addFun.apply(1));
        System.out.println(addFun.apply(2));
        System.out.println(addFun.apply(3));
        //  超过了数量不求值
        int result = addFun.apply(4);
        System.out.println("result1:" + result);
        result = addFun.apply(5);
        System.out.println("result2:" + result);
        //  1+2+3 = 6
        Assert.assertEquals(6, result);
    }
}
