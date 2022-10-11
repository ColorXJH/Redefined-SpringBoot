package com.example.springboot2thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class ApplicationTests {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisConnectionFactory factory;

    @Test
    void contextLoads() {
        //jdbcTemplate.queryForObject("SELECT * FROM test.employee")
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM test.employee");
        System.out.println(maps);
        log.info("数据源类型{}",dataSource.getClass());
    }
    @Test
    void test2(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("hellos","wordsX");
        String msg=stringStringValueOperations.get("hellos");
        System.out.println(msg);
        System.out.println(factory.getClass());
    }
    //junit5对比junit有了重大的升级，j5=junit plantform + jupiter enginee+junit vangatee
    //平台+引擎+向下兼容低版本
    //junit类具有spring的功能，比如autowired,transactional(测试完成后会自动回滚)
    //
}
