package cc.eslink.condition.demo4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnvConditional(EnvConditional.Env.DEV)
public class DevBeanConfig {
    @Bean
    public String name() {
        return "我是开发环境!";
    }
}
