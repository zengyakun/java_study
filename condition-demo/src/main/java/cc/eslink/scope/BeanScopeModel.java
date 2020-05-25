package cc.eslink.scope;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *@ClassName BeanScopeModel
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 18:35
 *@Version 1.0
 **/
public class BeanScopeModel {

    public BeanScopeModel(String beanScope) {
        System.out.println(String.format("线程:%s,create BeanScopeModel,{sope=%s},{this=%s}", Thread.currentThread(), beanScope, this));
    }
}
