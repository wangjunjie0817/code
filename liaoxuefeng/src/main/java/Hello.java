import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;


/**
 * @author WANGJJ
 * @date 2020/05/15
 */
public class Hello {

    // 初始化commnon-pool连接池, 并设置相关参数
    public static JedisCluster jedisCluster;

    static {
        // 初始化所有节点(例如6个节点)
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 6379));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 6380));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 6381));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 6382));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 6383));
        jedisClusterNode.add(new HostAndPort("127.0.0.1", 6384));
        // 初始化JedisCluster
        jedisCluster = new JedisCluster(jedisClusterNode, 1000, 1000, 5, new GenericObjectPoolConfig());
    }

    interface Test{
        default void print(){
            System.out.println(1);
        }
    }

    static class TestImpl implements Test{

    }

    public static void main(String[] args) {

        TestImpl test = new TestImpl();
        test.print();

    }


}
