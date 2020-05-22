package cc.eslink.condition.demo3;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *@ClassName MainConfig1
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 14:57
 *@Version 1.0
 **/
@Configuration
@Import({BeanConfig1.class, BeanConfig2.class})
public class MainConfig1 {
}
