package cc.eslink.enumclass.test2;

/**
 *@ClassName ILift
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:45
 *@Version 1.0
 **/
public interface ILift {

    public final static int OPEN_STATE = 1;
    public final static int CLOSE_STATE = 2;
    public final static int RUN_STATE = 3;
    public final static int STOP_STATE = 4;

    // 设置电梯状态
    public void setState(int state);

    // 电梯门开状态
    public void open();

    // 电梯关门状态
    public void close();

    // 电梯移动状态
    public void run();

    // 电梯停止状态
    public void stop();
}
