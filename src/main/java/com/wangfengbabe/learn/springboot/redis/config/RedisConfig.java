package com.wangfengbabe.learn.springboot.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author wangfeng04
 * @Date 2018/12/12 14:07
 * @Version 1.0
 **/

/**
 * Redis缓存配置类
 *
 * @author szekinwin
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        redisSerializer.setObjectMapper(mapper);
        template.setValueSerializer(redisSerializer);

        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }

//    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//
//        template.setConnectionFactory(factory);
//
//    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//
//    ObjectMapper om = new ObjectMapper();
//
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//    // key采用String的序列化方式
//
//        template.setKeySerializer(stringRedisSerializer);
//
//    // hash的key也采用String的序列化方式
//
//        template.setHashKeySerializer(stringRedisSerializer);
//
//    // value序列化方式采用jackson
//
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//
//    // hash的value序列化方式采用jackson
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//        template.afterPropertiesSet();
//
//        return template;


}

