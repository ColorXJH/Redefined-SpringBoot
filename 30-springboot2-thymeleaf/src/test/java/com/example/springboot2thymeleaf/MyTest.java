package com.example.springboot2thymeleaf;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

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

     @Test
     @DisplayName("数组断言")
     void testArray(){
        Assertions.assertArrayEquals(new int[]{2,1},new int[]{1,2},"数组内容不相等");

     }

     @Test
     @DisplayName("组合断言")
     void combineAll(){
         //里面的两个断言都成功，外面的才算成功
         Assertions.assertAll("test",()->{
             Assertions.assertTrue(true&&true,"结果不为true");
         },()->{
             Assertions.assertEquals(1,2,"结果不是预期的");
         });
     }

     @Test
     @DisplayName("异常断言")//断言这里会抛出异常，如果没有抛出异常，说明业务逻辑不对
     void exceptionAssert(){
         Assertions.assertThrows(ArithmeticException.class,()->{
             int i=10/0;
         },"业务逻辑居然正常运行了？，这是不对的");
     }

    @Test
    @DisplayName("超时断言")
    void timeOutAssert(){
        Assertions.assertTimeout(Duration.ofMillis(1000),()->{
           Thread.sleep(2000);
        },"超时了");
    }

    @Test
    @DisplayName("快速失败")
    void testFailFast(){
        if(2==2){
            Assertions.fail("测试失败");
        }

    }
    //项目一个模块开发完成后，需要书写单元测试类测试功能，然后对于整个类运行单元测试或者在maven中运行test，就会在控制台
    //生成测试报告，方便查看是否能测试报告


    //前置条件：断言会导致失败，前置条件则如果不通过则会终止执行而不是判定失败

    @Test
    @DisplayName("测试前置条件")
    void testAssumptions(){
        //在这里判断一下是否为true，如果前置条件为true,则继续往下走，否则终止执行
        //测试结果类似于京用掉了@Disabled
        Assumptions.assumeTrue(false,"结果不是true");
        System.out.println("11111");
    }


    //参数化测试:根据入参的不同来多多次执行测试，省去书写的荣冗余代码
        //@ValueSource:为参数化测试指定入参来源，支持八大基础类型以及Class类型
        //@NullSource:表示为参数化测试提供一个null的入参
        //@EnumSource,@CsvFileSource
        //@MethodSource:表示读取指定方法的返回值作为参数化测试入参（注意方法返回需要是一个流）

    //参数化测试的注解
    @DisplayName("参数化测试")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void  parameterTest(int i){
        System.out.println(i);//同时测试5个值
    }
    @DisplayName("参数化测试")
    @ParameterizedTest
    @MethodSource("stringProvicer")
    void  parameterTest2(String  i){
        System.out.println(i);//同时测试5个值
    }


    static Stream<String> stringProvicer(){
        return Stream.of("apple","banana");
    }
}
