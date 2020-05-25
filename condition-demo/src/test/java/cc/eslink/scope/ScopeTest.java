package cc.eslink.scope;

import cc.eslink.scope.demo3.BeanRefreshScope;
import cc.eslink.scope.demo3.MailService;
import cc.eslink.scope.demo3.MainConfig4;
import cc.eslink.scope.demo3.RefreshConfigUtil;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 *@ClassName ScopeTest
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 18:49
 *@Version 1.0
 **/
public class ScopeTest {

    @Test
    public void test() throws InterruptedException {
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

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBeanFactory().registerScope(BeanMyScope.SCOPE_MY, new BeanMyScope());
        context.register(MainConfig3.class);
        context.refresh();

        System.out.println("从容器中获取User对象");
        User user = context.getBean(User.class); //@2
        System.out.println("user对象的class为：" + user.getClass()); //@3

        System.out.println("多次调用user的getUsername感受一下效果\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println(String.format("********\n第%d次开始调用getUsername", i));
            System.out.println(user.getUsername());
            System.out.println(String.format("第%d次调用getUsername结束\n********\n", i));
        }
    }

    @Test
    public void test3() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBeanFactory().registerScope(BeanRefreshScope.SCOPE_REFRESH, BeanRefreshScope.getInstance());
        context.register(MainConfig4.class);
        //刷新mail的配置到Environment
        RefreshConfigUtil.refreshMailPropertySource(context);
        context.refresh();

        MailService mailService = context.getBean(MailService.class);
        System.out.println("配置未更新的情况下,输出3次");
        for (int i = 0; i < 3; i++) { //@1
            System.out.println(mailService);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        System.out.println("模拟3次更新配置效果");
        for (int i = 0; i < 3; i++) { //@2
            RefreshConfigUtil.updateDbConfig(context); //@3
            System.out.println(mailService);
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }
}
