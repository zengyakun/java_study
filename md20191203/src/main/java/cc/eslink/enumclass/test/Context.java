package cc.eslink.enumclass.test;

/**
 *@ClassName Context
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:39
 *@Version 1.0
 **/
public interface Context {
    State state();

    void state(State state);
}
