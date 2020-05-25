package cc.eslink.scope.demo3;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;

public class BeanRefreshScope implements Scope {

    public static final String SCOPE_REFRESH = "refresh";

    private static final BeanRefreshScope INSTANCE = new BeanRefreshScope();

    //来个map用来缓存bean
    private ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>(); //@1

    private BeanRefreshScope() {
    }

    public static BeanRefreshScope getInstance() {
        return INSTANCE;
    }

    /**
     * 清理当前
     */
    public static void clean() {
        INSTANCE.beanMap.clear();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object bean = beanMap.get(name);
        if (bean == null) {
            bean = objectFactory.getObject();
            beanMap.put(name, bean);
        }
        return bean;
    }

    @Override
    public Object remove(String s) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

}
