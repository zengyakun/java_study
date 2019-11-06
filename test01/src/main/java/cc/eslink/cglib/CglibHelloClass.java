package cc.eslink.cglib;

/**
 *@ClassName CglibHelloClass
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/6/3 15:15
 *@Version 1.0
 **/
public class CglibHelloClass {

    public String sayHello(String userName) {
        System.out.println("目标对象的方法执行了");
        return userName + " sayHello";
    }

    public String sayByeBye(String userName) {
        System.out.println("目标对象的方法执行了");
        return userName + " sayByeBye";
    }
}
