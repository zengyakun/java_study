package cc.eslink.test2;

/**
 *@ClassName Main2
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/29 14:15
 *@Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        System.out.println(Father.str);
        System.out.println(4 >>> 1);
        System.out.println(3 >>> 1);
        System.out.println(7 >>> 1);

    }
}

class Father {
    public static final String str = "Hello,world";

    static {
        System.out.println("Father static block");
    }
}

