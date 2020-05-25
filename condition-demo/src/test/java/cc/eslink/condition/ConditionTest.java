package cc.eslink.condition;

import cc.eslink.condition.demo1.MainConfig3;
import cc.eslink.condition.demo2.MainConfig4;
import cc.eslink.condition.demo3.IService;
import cc.eslink.condition.demo3.MainConfig1;
import cc.eslink.condition.demo4.MainConfig2;
import cc.eslink.condition.demo5.MainConfig5;
import cc.eslink.condition.demo6.MainConfig7;
import cc.eslink.scope.ThreadScope;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName ConditionTest
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 14:33
 *@Version 1.0
 **/
public class ConditionTest {

    @Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        Map<String, String> serviceMap = context.getBeansOfType(String.class);
        serviceMap.forEach((beanName, bean) -> {
            System.out.println(String.format("%s->%s", beanName, bean));
        });
    }

    @Test
    public void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig4.class);
        Map<String, String> serviceMap = context.getBeansOfType(String.class);
        serviceMap.forEach((beanName, bean) -> {
            System.out.println(String.format("%s->%s", beanName, bean));
        });
    }

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig1.class);
        Map<String, IService> serviceMap = context.getBeansOfType(IService.class);
        serviceMap.forEach((beanName, bean) -> {
            System.out.println(String.format("%s->%s", beanName, bean));
        });
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println(context.getBean("name"));
    }

    @Test
    public void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig5.class);
    }

    @Test
    public void test6() {
        // Condtion3->Condtion2->Condtion1
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig5.class);
    }

    @Test
    public void test7() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig7.class);
        context.getBeansOfType(String.class).forEach((beanName, bean) -> {
            System.out.println(String.format("%s->%s", beanName, bean));
        });
    }

    @Test
    public void test8() throws InterruptedException {
        String beanXml = "classpath:/beans-thread.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation(beanXml);
        context.refresh();
        context.getBeanFactory().registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread() + "," + context.getBean("threadBean"));
                System.out.println(Thread.currentThread() + "," + context.getBean("threadBean"));
            }).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
