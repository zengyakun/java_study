package cc.eslink.enumclass.test2;

/**
 *@ClassName LiftState
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:47
 *@Version 1.0
 **/
public abstract class LiftState {

    // 状态转换
    protected StageChange stageChange;

    // 电梯门开状态
    public abstract void open();

    // 电梯关门状态
    public abstract void close();

    // 电梯移动状态
    public abstract void run();

    // 电梯停止状态
    public abstract void stop();
}
