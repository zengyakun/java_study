package cc.eslink.condition.demo4;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TestBeanConfig.class, DevBeanConfig.class, ProdBeanConfig.class})
public class MainConfig2 {
}
