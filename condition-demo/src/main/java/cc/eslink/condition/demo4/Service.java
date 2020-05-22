package cc.eslink.condition.demo4;


import org.springframework.context.annotation.Conditional;

@Conditional(EnvCondition.class)
public class Service {
}
