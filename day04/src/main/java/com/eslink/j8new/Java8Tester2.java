package com.eslink.j8new;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *@ClassName Java8Tester2
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/10/17 16:54
 *@Version 1.0
 **/
public class Java8Tester2 {
    public static void main(String args[]) {
        Java8Tester2 java8tester = new Java8Tester2();
        java8tester.testZonedDateTime();
    }

    public void testZonedDateTime() {

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
