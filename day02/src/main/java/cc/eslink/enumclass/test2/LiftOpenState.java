package cc.eslink.enumclass.test2;

/**
 *@ClassName StageChange
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:47
 *@Version 1.0
 **/
public class LiftOpenState extends LiftState {
    public void open() {
        System.out.println("电梯门已经开启");
    }

    public void close() {
//        super.stageChange.setLiftState(StageChange.closeState);
        super.stageChange.getLiftState().close();
        System.out.println("电梯关门");
    }

    public void run() {

    }

    public void stop() {

    }
}
