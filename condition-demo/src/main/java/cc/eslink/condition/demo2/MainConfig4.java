package cc.eslink.condition.demo2;

import cc.eslink.condition.demo1.MyCondition1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 *@ClassName MainConfig4
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 14:49
 *@Version 1.0
 **/
@Configuration
public class MainConfig4 {
    @Conditional(MyCondition1.class) //@1
    @Bean
    public String name() {
        return "路人甲Java";
    }

    @Bean
    public String address() {
        return "上海市";
    }
}
