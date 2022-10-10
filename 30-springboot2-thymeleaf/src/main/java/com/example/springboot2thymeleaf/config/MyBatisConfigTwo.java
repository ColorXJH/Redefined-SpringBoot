package com.example.springboot2thymeleaf.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/10 10:44
 */
@Deprecated
//@Configuration
//@MapperScan(basePackages = "com.example.springboot2.thymeleaf..mapper2",sqlSessionFactoryRef ="sqlSessionFactory2",sqlSessionTemplateRef ="sqlSessionTemplate2" )
public class MyBatisConfigTwo {
    @Resource(name="dsTwo")
    DataSource ds2;

    @Bean
    SqlSessionFactory sqlSessionFactory2(){
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(ds2);
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate2() {
        return new SqlSessionTemplate(sqlSessionFactory2());
    }

    @Bean(name = "test2TransactionManager")
    public DataSourceTransactionManager test2TransactionManager() {
        return new DataSourceTransactionManager(ds2);
    }

    @Bean(name = "test2TransactionTemplate")
    public TransactionTemplate test2TransactionTemplate(){
        return new TransactionTemplate(test2TransactionManager());
    }

}
