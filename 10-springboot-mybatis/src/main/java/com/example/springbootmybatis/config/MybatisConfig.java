package com.example.springbootmybatis.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/7 11:14
 */
@MapperScan(value="com.example.springbootmybatis.mapper")
@org.springframework.context.annotation.Configuration
public class MybatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
      return new ConfigurationCustomizer(){
          @Override
          public void customize(Configuration configuration) {
              configuration.setMapUnderscoreToCamelCase(true);
          }
      };
    }
}
