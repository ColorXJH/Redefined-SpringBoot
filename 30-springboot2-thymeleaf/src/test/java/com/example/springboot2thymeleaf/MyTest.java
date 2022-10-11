package com.example.springboot2thymeleaf;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/11 15:31
 */
@SpringBootTest
@DisplayName("我的junit5功能测试类")
public class MyTest {//在类上运行单元测试，则所有的测试方法都会跑一遍
    @DisplayName("测试displayName注解")
    @Test//注意junit5的@test和junit4的@test并不是一样的包
    void testJunit(){
        System.out.println("1");
    }
    @Test
    void    testJunit2(){
        System.out.println("2");
    }
    @Disabled
    @Test
    void test3(){
        System.out.println("3");
    }

    @Test
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    void test4() throws InterruptedException {
        Thread.sleep(600);
    }
    @RepeatedTest(5)
    void test5(){
        System.out.println("repeat");
    }

    @BeforeEach
    void testBeforeEach(){
        System.out.println("每个测试方法运行前都要执行");
    }

    @AfterEach
    void testAfterEach(){
        System.out.println("每个测试方法运行后都要执行");
    }

    @BeforeAll
    static void testBeforeAll(){
        System.out.println("所有测试方法运行前执行");
    }

    @AfterAll
    static void testAfterAll(){
        System.out.println("所有测试方法运行后执行");
    }

    //junit5常用的测试
        //@ParameterizedTest:表示方法是参数化测试
        //@RepeatedTest:表示方法重复执行
        //@DisplayName:为测试类或者测试方法设置展示名称
        //@BeforeEach:表示在每个单元测试之前执行
        //@AfterEach:表示在每个单元测试之后执行
        //@BeforeAll:表示在所有单元测试之前执行
        //@AfterAll:表示在所有单元测试之后执行
        //@Tag:表示单元测试类别,类似于Junit4中的@Categories
        //@Disabled:表示测试类或者测试方法不执行,类似于Junit4中的@Ignore
        //@Timeout:表示测试方法运行如果超出了指定时间将会返回错误
        //@ExtendWith:为测试类或者测试方法提供扩展类引用，
            // @SpringBootTest是一个符复合注解，里面就有@ExtendWith({SpringExtension.class})
            // 表示继承spring的功能，可以在该类中自动注入，获取到@Autowired的值，不然取值为null

    //断言机制：断言是测试方法的核心部分，用力啊对测试需要满足的条件进行验证
        //简单断言，数组断言，组合断言，异常断言，超时断言，快速失败


     @DisplayName("测试简单断言")//断言失败后需代码无法执行
     @Test
     void testSimpleAssertions(){
        int cal=cal(2,3);
        //Assertions.assertEquals(5,cal);// 参数：a:期望值 b:实际值
         Assertions.assertEquals(5,cal,"业务逻辑计算失败");
         Object o1=new Object();
         Object o2=new Object();
         Assertions.assertSame(o1,o2,"两个对象不一样");
     }
     int cal(int i,int j){
        return i+j;
     }
}
