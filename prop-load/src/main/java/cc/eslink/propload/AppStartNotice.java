package cc.eslink.propload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *@ClassName AppStartNotice
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/3/25 14:14
 *@Version 1.0
 **/
@Order(0)
@Component
public class AppStartNotice implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("启动进来了");
        //读取外部配置的key
        System.out.println(environment.getProperty("abcd"));

        //读取内部配置的key
        System.out.println(environment.getProperty("config.icon"));
    }
}
