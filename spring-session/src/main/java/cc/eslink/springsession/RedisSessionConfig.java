package cc.eslink.springsession;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *@ClassName RedisSessionConfig
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/3/17 16:51
 *@Version 1.0
 **/
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
