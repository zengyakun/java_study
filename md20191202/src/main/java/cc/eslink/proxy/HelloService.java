package cc.eslink.proxy;

/**
 *@ClassName HelloService
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/6/3 14:45
 *@Version 1.0
 **/
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String userName) {
        System.out.println(userName + " hello");
        return userName + " hello";
    }

    @Override
    public String sayByeBye(String userName) {
        System.out.println(userName + " ByeBye");
        return userName + " ByeBye";
    }
}
