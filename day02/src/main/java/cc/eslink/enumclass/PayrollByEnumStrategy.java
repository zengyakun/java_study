package cc.eslink.enumclass;

/**
 *@ClassName PayrollByEnumStrategy
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:18
 *@Version 1.0
 **/
public enum PayrollByEnumStrategy {

    Monday(PayType.WeekDay),
    Tuesday(PayType.WeekDay),
    Wendsday(PayType.WeekDay),
    Thursday(PayType.WeekDay),
    Friday(PayType.WeekDay),
    Saturday(PayType.WeekEnd),
    Sunday(PayType.WeekEnd);

    PayType payType;

    private PayrollByEnumStrategy(PayType payType) {
        this.payType = payType;
    }

    public double pay(double workHours, double payPerHour) {
        return payType.pay(workHours, payPerHour);
    }

    private enum PayType {

        WeekDay {
            @Override
            double overTimePay(double workHours, double payPerHour) {
                double overTimePay = workHours <= FIXED_WORK_HOUR_PER_DAY ?
                        0 : (workHours - FIXED_WORK_HOUR_PER_DAY) * payPerHour * OVER_TIME_PAY_RATE;
                return overTimePay;
            }
        },
        WeekEnd {
            @Override
            double overTimePay(double workHours, double payPerHour) {
                double overTimePay = workHours * payPerHour * OVER_TIME_PAY_RATE;
                return overTimePay;
            }
        };

        private static final int FIXED_WORK_HOUR_PER_DAY = 8;

        //加班工资会是基本工资的一半。
        private static final double OVER_TIME_PAY_RATE = 1 / 2;

        abstract double overTimePay(double workHours, double payPerHour);

        public double pay(double workHours, double payPerHour) {

            double basePay = workHours * payPerHour;

            double overTimePay = overTimePay(workHours, payPerHour);
            return basePay + overTimePay;
        }
    }
}
