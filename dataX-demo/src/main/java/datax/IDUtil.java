package datax;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 *@ClassName IDUtil
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2020/4/8 13:12
 *@Version 1.0
 **/
public class IDUtil {

//    public static String test() {
//        Random random = new Random();return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+ Stream.generate((Supplier) () -> String.valueOf(random.nextInt(10))).limit(10).collect(Collectors.joining());
//    }

    public static String test2() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(IDUtil.test2());
        System.out.println(IDUtil.test2());
        System.out.println(IDUtil.test2());
        System.out.println(IDUtil.test2());
    }
}
