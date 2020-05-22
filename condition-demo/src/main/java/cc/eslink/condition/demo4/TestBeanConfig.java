package cc.eslink.condition.demo4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnvConditional(EnvConditional.Env.TEST)
public class TestBeanConfig {
    @Bean
    public String name() {
        return "我是测试环境!";
    }
}
