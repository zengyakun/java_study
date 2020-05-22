package cc.eslink.condition.demo3;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 *@ClassName OnMissingBeanCondition
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 14:53
 *@Version 1.0
 **/
public class OnMissingBeanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //从容器中获取IService类型bean
        Map<String, IService> serviceMap = beanFactory.getBeansOfType(IService.class);
        //判断serviceMap是否为空
        return serviceMap.isEmpty();
    }

}
