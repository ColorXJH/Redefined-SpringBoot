package com.example.springboot2thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    void contextLoads() {
        //jdbcTemplate.queryForObject("SELECT * FROM test.employee")
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM test.employee");
        System.out.println(maps);
        log.info("数据源类型{}",dataSource.getClass());
    }

}
