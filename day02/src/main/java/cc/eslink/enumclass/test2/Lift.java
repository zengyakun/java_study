package cc.eslink.enumclass.test2;

/**
 *@ClassName Lift
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:45
 *@Version 1.0
 **/
public class Lift implements ILift {

    @Override
    public void setState(int state) {

    }

    public void open() {
        System.out.println("lift is opening");
    }

    public void close() {
        System.out.println("lift is closed");
    }

    public void run() {
        System.out.println("lift run up or down");
    }

    public void stop() {
        System.out.println("lift stopped");
    }
}
