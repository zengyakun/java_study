package cc.eslink.test.function;

/**
 *@ClassName FunctionVerify
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/8/7 9:39
 *@Version 1.0
 **/
@FunctionalInterface
public interface FunctionVerify<T> {

    Boolean fun(T f);

    default void run() {
    }

    static void exec() {
    }
}
