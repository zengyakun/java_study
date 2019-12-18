package cc.eslink.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;

/**
 *@ClassName JedisClientCluster
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/6/5 22:19
 *@Version 1.0
 **/
public class JedisClientCluster implements JedisClient {

    private static final String HOST = "192.168.2.128";

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey, key);
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey, key, value);
    }

    @Override
    public long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public long hdel(String hkey, String key) {
        return jedisCluster.hdel(hkey, key);
    }

    public static void main(String[] args) throws IOException {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(HOST, 6000));
        nodes.add(new HostAndPort(HOST, 6001));
        nodes.add(new HostAndPort(HOST, 6002));
        nodes.add(new HostAndPort(HOST, 6003));
        nodes.add(new HostAndPort(HOST, 6004));
        nodes.add(new HostAndPort(HOST, 6005));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("cluster-key", "cluster-value");
        System.out.println("集群测试 ： " + cluster.get("cluster-key"));
        cluster.close();
    }
}
