package cc.eslink.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *@ClassName JedisClientSingle
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/6/4 17:09
 *@Version 1.0
 **/
public class JedisClientSingle {
    /**
     * connect timed out 问题：
     * 1. 检查redis服务是否开启
     * 2. 检查是否是因为防火墙的问题
     * 3. 检查网络问题(如果在同一个局域网内几乎不会出现这个问题)
     * Jedis jedis =new Jedis(HOST,PORT,100000);
     * JedisPool pool = new JedisPool(poolConfig, HOST, PORT, 100000);
     */

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }

    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }

}
