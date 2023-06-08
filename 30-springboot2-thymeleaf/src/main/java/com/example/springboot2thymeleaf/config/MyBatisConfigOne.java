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
 * @date 2022/10/10 10:43
 */
@Deprecated
//@Configuration
//@MapperScan(basePackages = "com.example.springboot2.thymeleaf.mapper1",sqlSessionFactoryRef ="sqlSessionFactory1",sqlSessionTemplateRef ="sqlSessionTemplate1" )
public class MyBatisConfigOne {
    /*Autowired注解与Resource注解的区别
    两者的共同点
    @Resource注解和@Autowired注解都可以用作bean的注入.
    在接口只有一个实现类的时候,两个注解可以互相替换,效果相同.
    两者的不同点
    @Resource注解是Java自身的注解,@Autowired注解是Spring的注解.
    @Resource注解有两个重要的属性,分别是name和type,
    如果name属性有值,则使用byName的自动注入策略,将值作为需要注入bean的名字,
    如果type有值,则使用byType自动注入策略,将值作为需要注入bean的类型.
    如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
    即@Resource注解默认按照名称进行匹配,名称可以通过name属性进行指定，
    如果没有指定name属性，当注解写在字段上时，默认取字段名，按照名称查找,当找不到与名称匹配的bean时才按照类型进行装配。
    但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
    @Autowired注解是spring的注解,此注解只根据type进行注入,不会去匹配name.
    但是如果只根据type无法辨别注入对象时,就需要配合使用@Qualifier注解或者@Primary注解使用.
    */
    @Resource(name="dsOne")
            //@Autowired
            //@Qualifier("dsOne")
    DataSource ds1;

    @Bean
    SqlSessionFactory sqlSessionFactory1(){
        SqlSessionFactory sessionFactory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(ds1);
            //这里也可以设置mapperlocations,上方@MapperScan中就可以不用写basePackages了
            //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test1/*.xml"));
            sessionFactory = bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate1() {
        return new SqlSessionTemplate(sqlSessionFactory1());
    }

    @Bean(name = "test1TransactionManager")
    public DataSourceTransactionManager test1TransactionManager() {
        return new DataSourceTransactionManager(ds1);
    }
    @Bean(name = "test1TransactionTemplate")
    public TransactionTemplate test1TransactionTemplate(){
        return new TransactionTemplate(test1TransactionManager());
    }


    /*
    *然后采用spring的编程式事务控制，即 使用TransactionTemplate进行事务管理，然后
    @Autowired
    @Qualifier("test1TransactionTemplate")
    TransactionTemplate test1TransactionTemplate;

    @Autowired
    @Qualifier("test2TransactionTemplate")
    TransactionTemplate test2TransactionTemplate;

    接下来可以使用注解也可以使用编程方式配置事务
    注解：@Transactional(transactionManager = "test1TransactionTemplate", rollbackFor = Exception.class)
    编程：
    public void save2(){
        // execute 方法需要一个 TransactionCallBack接口，这里用 lambda的方式
        test1TransactionTemplate.execute((status1) ->{
            test2TransactionTemplate.execute((status2)->{
                try {

                    school1Mapper.insert(new School1(3,"李四",18));
                    school2Mapper.insert(new School2(3,"李四",18));
                    System.out.println("模拟异常");
                    int i = 1/0;

                }catch (Exception e){
                    e.printStackTrace();
                    status1.setRollbackOnly();  // 事务1回滚
                    status2.setRollbackOnly();  // 事务2回滚
                }
                return true; // 事务2提交
            });
            return true;    // 事务1提交
        });
    }
    * */
    //详情参考：https://blog.csdn.net/weixin_45947759/article/details/123682718
    //https://www.jianshu.com/p/86982bdc0c20?u_atoken=a95ef88c-4914-419a-9aa8-351c95dac506&u_asession=01oPCKLfpMIcexDh6mJ9gKu7vMRptpE-yjkUI-4E18WDcTWQUrMnNQSaRjaqLlhWh2X0KNBwm7Lovlpxjd_P_q4JsKWYrT3W_NKPr8w6oU7K9qCJPM-SG3Qo1jHRAjeQXILDnIvqHhvwKBSiCA9W2pPWBkFo3NEHBv0PZUm6pbxQU&u_asig=05x4JPm4vs5zdTq4YEVpzE7I8-6CJ1co86eCeRn3ygfI2bWAzwUI_6mK3I9ds1ay2Qjhd4h20MfWdGd3IXbYdXPwGH58pYW5tYsQ8ZyWkw0x0jtfUqWWB5DSwaY1W6lNml60gnk-mkoDiITSqumtmvYOykW8ciMUxML7-e3QlmGP_9JS7q8ZD7Xtz2Ly-b0kmuyAKRFSVJkkdwVUnyHAIJzVGYUl2GKGwzheLMiMSXGaK8dnKtOHiDMTSCcsHkdfW3T6aHbewJv6RwWiEQAUeSSO3h9VXwMyh6PgyDIVSG1W8czt8dy0VfF2oNqloyw5sn7E01qPfsRHyN5ozunDHp3PawKguvO29G-EDJxrvGzm9Q6S6346Jdi1KXuY_XBZs8mWspDxyAEEo4kbsryBKb9Q&u_aref=Gj86Hmj4DmxqLmdRmmUEzULzuWw%3D
}
