package cc.eslink.condition.proxy2;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *@ClassName CglibTest
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 18:06
 *@Version 1.0
 **/
public class CglibTest {

    @Test
    public void test1() {
        //使用Enhancer来给某个类创建代理类，步骤
        //1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
        enhancer.setSuperclass(Service1.class);
        /*3.设置回调，需实现org.springframework.cglib.proxy.Callback接口，
        此处我们使用的是org.springframework.cglib.proxy.MethodInterceptor，也是一个接口，实现了Callback接口，
        当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理*/
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 代理对象方法拦截器
             * @param o 代理对象
             * @param method 被代理的类的方法，即Service1中的方法
             * @param objects 调用方法传递的参数
             * @param methodProxy 方法代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法:" + method);
                //可以调用MethodProxy的invokeSuper调用被代理类的方法
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        //4.获取代理对象,调用enhancer.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
        Service1 proxy = (Service1) enhancer.create();
        //5.调用代理对象的方法
        proxy.m1();
        proxy.m2();
    }

    @Test
    public void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法：" +method);
                Object result = methodProxy.invoke(o, objects);
                return result;
            }
        });

        Service2 proxy = (Service2) enhancer.create();
        proxy.m1();
    }
}
