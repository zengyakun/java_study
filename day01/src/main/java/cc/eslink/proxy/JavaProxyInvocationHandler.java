package cc.eslink.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *@ClassName JavaProxyInvocationHandler
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/6/3 14:47
 *@Version 1.0
 **/
public class JavaProxyInvocationHandler implements InvocationHandler {

    private Object obj;

    public JavaProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    public Object newProxyInstance() {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke before");
        Object result = method.invoke(obj, args);
        System.out.println("invoke after");
        return result;
    }

    public static void main(String[] args) {
        JavaProxyInvocationHandler proxy = new JavaProxyInvocationHandler(new HelloService());
        IHelloService service = (IHelloService) proxy.newProxyInstance();
        service.sayHello("eslink");
        service.sayByeBye("eslink");
    }
}
