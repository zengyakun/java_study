package cc.eslink.enumclass.test;

/**
 *@ClassName InfiniteMachineTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:41
 *@Version 1.0
 **/
public class InfiniteMachineTest {

    public static void main(String[] args) {

        Context context = new ContextImp();
        context.state(States.RED);
        while (context.state().process(context)) ;
    }

}
