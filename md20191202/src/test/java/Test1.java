import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *@ClassName Test1
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/27 18:04
 *@Version 1.0
 **/
public class Test1 {

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext();


    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put(null, null);
        map.put(null, "b");
        map.put("c", null);
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println("key:" + key);
        }
    }
}
