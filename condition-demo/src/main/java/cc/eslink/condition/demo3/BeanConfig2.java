package cc.eslink.condition.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 *@ClassName BeanConfig1
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 14:55
 *@Version 1.0
 **/
@Configuration
public class BeanConfig2 {

//    @Conditional(OnMissingBeanCondition.class)
    @Bean
    public IService service2() {
        return new Service2();
    }
}
