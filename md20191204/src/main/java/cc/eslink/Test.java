package cc.eslink;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

/**
 *@ClassName Test
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/29 11:39
 *@Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        int a = 200;
        Integer b = 200;
        System.out.println(a == b);

// 2）两个包装类型
        Integer c = 100;
        Integer d = 100;
        System.out.println(c == d);

// 3）
        c = 200;
        d = 200;
        System.out.println(c == d);

        BigDecimal v = new BigDecimal(0.1D);
        System.out.println(v);

        BigDecimal v2 = BigDecimal.valueOf(0.1D);
        System.out.println(v2);
    }
}