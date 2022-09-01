package com.example.springbootcache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * @ClassName: MyRedisConfig
 * @Package: com.example.springbootcache.config
 * @Description:
 * @Datetime: 2022/8/31 22:37
 * @author: ColorXJH
 */
@Configuration
public class MyRedisConfig {
    //方式一;将默认的redisTemplate重新构造

    /*//springboot2针对基于API方式的RedisTemplate进行了自定义序列化方式的改进,这种自定义的RedisTemplate对于基于注解的Redis缓存来说，是没有作用的
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 创建一个JSON格式序列化对象，对缓存数据的key和value进行转换
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 设置RedisTemplate模板api序列化方式为json
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }


    //springboot2版本针对基于注解的Redis缓存机制和自定义序列化方式
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory){
        RedisCacheConfiguration redisCacheConfiguration=//创建Redis的配置对象
                RedisCacheConfiguration.defaultCacheConfig()//defaultCacheConfig()方法是用来整合Redis的
                        .entryTtl( Duration.ofHours(1) )//设置缓存的有效时间及自动更新策略,大家可以自行设置缓存的时间
                        .disableCachingNullValues()//禁用空值
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer( new GenericJackson2JsonRedisSerializer(  ) ));
        // 使用GenericJackson2JsonRedisSerializer来序列化和反序列化redis的value值
        return RedisCacheManager.builder(factory).cacheDefaults( redisCacheConfiguration ).build();
    }*/


    //方式二：定义自己的特殊的myRedisTemplate,不覆盖默认的
    @Bean //bean 的 name 默认为方法名
    public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        //设置redis模板
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //为redis模板传入连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        //设置json的序列化
        Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<>(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        ser.setObjectMapper(om);
        //为redis模板设置默认的序列化方式
        template.setDefaultSerializer(ser);
        //将设置好的模板设置到容器中
        return template;
    }

    @Bean //传入上面定义好序列化方式的redis模板
    //可以配置多对xxxRedisTemplate xxxredisCacheManager配合使用
    //需要在缓存中指明使用哪个缓存管理器以及在redis操作时指明使用哪个模板
    //因为缓存管理器需要自动注入到容器中，所以多个无法自动注入，需要为其中一个定义@Primary注解，表明优先使用
    public RedisCacheManager redisCacheManager(RedisTemplate<Object,Object> myRedisTemplate){
        //2.0以上开始就需要传入一个redisCacheWriter参数，并传入上面写的模板对象的连接工厂   内部也是为其设置连接工厂和sleeptime
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(myRedisTemplate.getConnectionFactory());
        //在redis缓存配置文件中传入刚定义好的缓存配置myRedisTemplate.getValueSerializer()
        //RedisCacheConfiguration.defaultCacheConfig().usePrefix(false) //可以不配置默认使用key前缀
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(myRedisTemplate.getValueSerializer()));
        //将定义好的配置文件放入Redis缓存管理器，放入容器中。
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }


}
