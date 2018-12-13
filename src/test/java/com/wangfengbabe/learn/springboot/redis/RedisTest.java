package com.wangfengbabe.learn.springboot.redis;

import com.wangfengbabe.learn.springboot.redis.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author wangfeng04
 * @Date 2018/12/12 14:41
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testGet() {
        String o = redisUtils.get("hello", String.class);
        System.out.println(o);
        boolean set = redisUtils.set("hello", "你好");
        System.out.println(set);

    }

}
