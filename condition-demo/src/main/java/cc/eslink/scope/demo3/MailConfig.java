package cc.eslink.scope.demo3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件配置信息
 */
@Component
@RefreshScope //@1
public class MailConfig {

    @Value("${mail.username}") //@2
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MailConfig{" +
                "username='" + username + '\'' +
                '}';
    }
}
