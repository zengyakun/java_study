package cc.eslink.proxy;

import cc.eslink.proxy.demo.*;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *@ClassName Tests
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/28 18:25
 *@Version 1.0
 **/
public class Tests {

    @Test
    public void serviceProxy() {
        IService serviceA = new ServiceProxy(new ServiceA());//@1
        IService serviceB = new ServiceProxy(new ServiceB()); //@2
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();

        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<IService> proxyClass = (Class<IService>) Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        InvocationHandler invocationHandler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        IService proxyService = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        // 4. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }

    @Test
    public void test3() {
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        });
        // 3. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }

    @Test
    public void costTimeProxy() {
        IService serviceA = CostTimeInvocationHandler.createProxy(new ServiceA(), IService.class);
        IService serviceB = CostTimeInvocationHandler.createProxy(new ServiceB(), IService.class);
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();

        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }
}
