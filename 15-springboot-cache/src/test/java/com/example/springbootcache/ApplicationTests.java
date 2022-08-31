package com.example.springbootcache;

import cn.hutool.json.JSONUtil;
import com.example.springbootcache.bean.Employee;
import com.example.springbootcache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootTest
class ApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象

    @Autowired
    RedisTemplate myRedisTemplate;//name 默认为：myRedisTemplate

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v字符串

    /*
     * Description:测试redis 操作String list set  hash ZSet
     * @Author: ColorXJH
     * @Date: 2022/8/31 21:43
     * @param null
     * @Return: 
     **/
    @Test
    public void test01(){
        System.out.println(stringRedisTemplate.opsForValue().get("msg"));
        stringRedisTemplate.opsForValue().append("msg","Color6");
        System.out.println(stringRedisTemplate.opsForList().range("mylist",0,10));
        stringRedisTemplate.opsForList().leftPush("mylist","color6");
        System.out.println(stringRedisTemplate.opsForSet().members("myset"));
        stringRedisTemplate.opsForSet().add("myset","color6");
        System.out.println(stringRedisTemplate.opsForHash().values("myhash"));
        stringRedisTemplate.opsForHash().put("myhash","h3","color6");
        System.out.println(stringRedisTemplate.opsForZSet().range("myzset",0,10));
        stringRedisTemplate.opsForZSet().add("myzset","color6",5);
    }

    @Test
    public void test02(){
        Employee employeeById = employeeMapper.getEmployeeById(1);
        //默认使用jdk序列化机制
        redisTemplate.opsForValue().set("emp-01",employeeById);
        System.out.println(redisTemplate.opsForValue().get("emp-01"));
        //将数据以json方式保存
        //1:自己将数据转为json(工具或者toString())
        Employee employeeById2 = employeeMapper.getEmployeeById(1);
        redisTemplate.opsForValue().set("emp-02", JSONUtil.toJsonStr(employeeById2));
        System.out.println(redisTemplate.opsForValue().get("emp-02"));
        //2:redisTemplate修改序列化规则
        Employee employeeById3 = employeeMapper.getEmployeeById(3);
        myRedisTemplate.opsForValue().set("emp-03", employeeById3);
    }

    @Test
    void contextLoads() {
        Employee employeeById = employeeMapper.getEmployeeById(1);
        System.out.println(employeeById);
    }

}
