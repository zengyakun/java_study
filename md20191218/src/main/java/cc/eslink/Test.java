package cc.eslink;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 *@ClassName Test
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/12/19 17:01
 *@Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        Runnable noArguments = () -> System.out.println("Hello World");

        ActionListener oneArgument = event -> System.out.println("button clicked");

        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };

        BinaryOperator<Long> add = (x, y) -> x + y;

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;

        final String[] array = {"hello", "world"};

        String name = "abc";
//        name = "bcd";
        Button button = new Button();
        button.addActionListener(event -> System.out.println("hi " + name));

        Predicate<Integer> atLeast5 = x -> x > 5;
        atLeast5.test(10);






    }
}
