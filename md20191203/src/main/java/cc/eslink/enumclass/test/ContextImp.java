package cc.eslink.enumclass.test;

/**
 *@ClassName ContextImp
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:40
 *@Version 1.0
 **/
public class ContextImp implements Context {
    State state;

    @Override
    public State state() {
        // TODO Auto-generated method stub
        return state;
    }

    @Override
    public void state(State state) {
        this.state = state;

    }
}
