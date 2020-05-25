package cc.eslink.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 *@ClassName BeanMyScope
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 18:46
 *@Version 1.0
 **/
public class BeanMyScope implements Scope {

    public static final String SCOPE_MY = "my";

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("BeanMyScope >>>>>>>>> get:" + name); //@2
        return objectFactory.getObject(); //@3
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
