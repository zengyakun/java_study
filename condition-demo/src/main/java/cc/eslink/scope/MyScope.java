package cc.eslink.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

/**
 *@ClassName MyScope
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 18:51
 *@Version 1.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(BeanMyScope.SCOPE_MY) //@1
//@RefreshScope
public @interface MyScope {

    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;//@2
}
