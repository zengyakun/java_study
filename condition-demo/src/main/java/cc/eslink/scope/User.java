package cc.eslink.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *@ClassName User
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 18:51
 *@Version 1.0
 **/
@Component
@MyScope
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {

    private String username;

    public User() {
        System.out.println("---------创建User对象" + this); //@2
        this.username = UUID.randomUUID().toString(); //@3
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
