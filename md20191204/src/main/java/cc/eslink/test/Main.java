package cc.eslink.test;

/**
 *@ClassName Main
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/29 14:15
 *@Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        System.out.println(Father.str);
    }
}

class Father {
    public static String str = "Hello,world";

    static {
        System.out.println("Father static block");
    }
}
