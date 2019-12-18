package cc.eslink.redis;

/**
 *@ClassName JedisClient
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/6/5 22:21
 *@Version 1.0
 **/
public interface JedisClient {

    String get(String key);

    String set(String key, String value);

    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    long del(String key);

    long hdel(String hkey, String key);
}
