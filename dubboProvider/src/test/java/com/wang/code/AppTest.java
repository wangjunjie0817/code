package com.wang.code;

import static org.junit.Assert.assertTrue;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
            .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        client.start();

        GetChildrenBuilder children = client.getChildren();
        List<String> x = children.forPath("/");
        System.out.println(x);
        for (String name: x){
            client.delete().
                guaranteed().
                deletingChildrenIfNeeded().
                forPath("/" + name.trim());
        }

    }
}
