package cc.eslink.condition.demo1;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 *@ClassName MyCondition1
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/22 14:24
 *@Version 1.0
 **/
public class MyCondition1 implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return false;
//        return true;
    }
}
