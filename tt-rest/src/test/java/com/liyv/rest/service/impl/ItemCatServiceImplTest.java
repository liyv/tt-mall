package com.liyv.rest.service.impl;

import com.liyv.rest.BaseTest;
import com.liyv.rest.service.ContentService;
import com.liyv.rest.service.ItemCatService;
import com.liyv.taotao.dto.content.ContentItemDTO;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.List;

public class ItemCatServiceImplTest extends BaseTest {
    @Autowired
    ItemCatService itemCatService;

    @Autowired
    ContentService contentService;

    @Test
    public void testItemCatRedis() {
        String json = itemCatService.getItemCatList();
        System.out.println(json);
    }

    @Test
    public void testContentRedis() {
        List<ContentItemDTO> list = contentService.getContentListByCategory(89L);
        System.out.println(list.size());
    }

    /**
     * 测试单机版 Redis
     */
    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("192.168.17.130", 6379);
//        jedis.set("key1", "val");
        String key1 = jedis.get("key1");
        jedis.close();
        System.out.println(key1);
    }

    @Test
    public void testSyncCache(){

    }

    /**
     * 集群版
     */
    @Test
    public void testRedisCluster() {

        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.17.130", 7001));
        nodes.add(new HostAndPort("192.168.17.130", 7002));
        nodes.add(new HostAndPort("192.168.17.130", 7003));
        nodes.add(new HostAndPort("192.168.17.130", 7004));
        nodes.add(new HostAndPort("192.168.17.130", 7005));
        nodes.add(new HostAndPort("192.168.17.130", 7006));
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(1000);
        JedisCluster cluster = new JedisCluster(new HostAndPort("192.168.17.130", 7001), config);
        String result = cluster.get("hello");
        System.out.println(result);
//        String keyVal = cluster.get("key1");
//        System.out.println(keyVal);

    }
}