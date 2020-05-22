package cc.eslink.condition.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 *@ClassName MainConfig3
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 14:32
 *@Version 1.0
 **/
@Conditional(MyCondition1.class)
@Configuration
public class MainConfig3 {

    @Bean
    public String name() {
        return "路人甲Java";
    }
}
