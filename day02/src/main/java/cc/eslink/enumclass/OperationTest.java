package cc.eslink.enumclass;

/**
 *@ClassName OperationTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:31
 *@Version 1.0
 **/
public class OperationTest {

    public static void main(String[] args) {
        double x = 2.000;
        double y = 4.0000;

        for (Operation oper : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, oper, y, oper.eval(x, y));
        }
    }
}
