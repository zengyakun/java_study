package cc.eslink.spring.lookup;

import cc.eslink.spring.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@ClassName App
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/30 19:37
 *@Version 1.0
 **/
public class App {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:application.xml");

        FruitPlate fp1 = (FruitPlate) app.getBean("fruitPlate1");
        FruitPlate fp2 = (FruitPlate) app.getBean("fruitPlate2");

        fp1.getFruit();
        fp2.getFruit();
    }
}
