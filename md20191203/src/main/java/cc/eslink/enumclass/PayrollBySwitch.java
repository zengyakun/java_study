package cc.eslink.enumclass;

/**
 *@ClassName PayrollBySwitch
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:17
 *@Version 1.0
 **/
public enum PayrollBySwitch {
    Monday,
    Tuesday,
    Wendsday,
    Thursday,
    Friday,
    Saturday,
    Sunday;

    private static final int FIXED_WORK_HOUR_PER_DAY = 8;

    //加班工资会是基本工资的一半。
    private static final double OVER_TIME_PAY_RATE = 1 / 2;

    public double pay(double workHours, double payPerHour) {

        double basePay = workHours * payPerHour;

        double overTimePay = 0;
        switch (this) {
            //周六日每个小时都算加班工资
            case Saturday:
            case Sunday:
                overTimePay = workHours * payPerHour * OVER_TIME_PAY_RATE;
                break;
            default:
                overTimePay = (workHours <= FIXED_WORK_HOUR_PER_DAY ?
                        0 : workHours - FIXED_WORK_HOUR_PER_DAY) * OVER_TIME_PAY_RATE;
                break;

        }
        return basePay + overTimePay;
    }
}
