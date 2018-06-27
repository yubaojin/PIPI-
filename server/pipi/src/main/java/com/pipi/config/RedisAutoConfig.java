package com.pipi.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 * 12)
@EnableCaching
public class RedisAutoConfig extends CachingConfigurerSupport {

  
  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
      RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
      //设置缓存过期时间
      //Map<String, Long> expires = new HashMap<>();
      //expires.put("12h",3600 * 12L);
      //expires.put("1h",3600 * 1L);
      //expires.put("10m",60 * 5L);
      //rcm.setExpires(expires);
      
      //rcm.setDefaultExpiration(10); 	//秒
      return rcm;
  }
  /*@Bean
  public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
    redisTemplate.setConnectionFactory(factory);
    RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
    redisTemplate.setKeySerializer(stringRedisSerializer);
    return redisTemplate;
  }*/
  
  @Bean
  public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
     RedisTemplate<Object, Object> template = new RedisTemplate<>();
     template.setConnectionFactory(connectionFactory);

     //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
     Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

     ObjectMapper mapper = new ObjectMapper();
     mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
     mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
     serializer.setObjectMapper(mapper);

     template.setValueSerializer(serializer);
     //使用StringRedisSerializer来序列化和反序列化redis的key值
     template.setKeySerializer(new StringRedisSerializer());
     template.afterPropertiesSet();
     return template;
  }
  
}