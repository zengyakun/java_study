package cc.eslink.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@ClassName App
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/28 13:54
 *@Version 1.0
 **/
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext();
        System.out.println("context 启动成功");
        MessageService messageService = context.getBean(MessageService.class);
        System.out.println(messageService.getMessage());
    }
}
