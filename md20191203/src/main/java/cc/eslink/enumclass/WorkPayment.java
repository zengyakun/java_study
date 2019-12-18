package cc.eslink.enumclass;

/**
 *@ClassName WorkPayment
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:24
 *@Version 1.0
 **/
public class WorkPayment {

    public static void main(String[] args) {
        double sum = 0;
        for (PayrollBySwitch day : PayrollBySwitch.values()) {
            sum += day.pay(10, 50);
        }
        System.out.println("工资总额：" + sum);

        sum = 0;
        for (PayrollByEnumStrategy day : PayrollByEnumStrategy.values()) {
            sum += day.pay(10, 50);
        }
        System.out.println("工资总额：" + sum);
    }
}
