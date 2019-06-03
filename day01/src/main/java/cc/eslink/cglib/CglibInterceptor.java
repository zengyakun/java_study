package cc.eslink.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *@ClassName CglibInterceptor
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/6/3 15:16
 *@Version 1.0
 **/
public class CglibInterceptor implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before invoke");
        Object obj = methodProxy.invokeSuper(o, args);
        System.out.println("after invoke");
        return obj;
    }

    public Object newProxyInstance(Class<?> c) {
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public static void main(String[] args) {
        CglibInterceptor proxy = new CglibInterceptor();
        CglibHelloClass service = (CglibHelloClass) proxy.newProxyInstance(CglibHelloClass.class);
        service.sayHello("eslink");
        service.sayByeBye("eslink");
    }
}
