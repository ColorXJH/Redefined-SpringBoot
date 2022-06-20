package com.example.springbootcache.service;

import com.example.springbootcache.bean.Employee;
import com.example.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: EmployeeService
 * @Package: com.example.springbootmybatis.service
 * @Description:
 * @Datetime: 2022/6/18 17:02
 * @author: ColorXJH
 */
@Service
public class EmployeeService {
    //@autowired和@resource注解的区别是什么？
    //区别：1、@Autowired注解由Spring提供，只按照byType注入；@resource注解由J2EE提供，默认按照byName自动注入。
    //2、@Autowired默认按类型进行装配，@Resource默认按照名称进行装配。

    //1、@Autowired
    //由Spring提供，只按照byType注入

    //2、@Resource
    //由J2EE提供，默认按照byName自动注入
    //@Resource有两个重要的属性：name和type
    //Spring将@Resource注解的name属性解析为bean的名字，type属性则解析为bean的类型。
    //所以如果使用name属性，则使用byName的自动注入策略，而使用type属性则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。



    @Resource
    //这里如果使用@Autowired会报错没有识别到类，@Resourece则不会，因为她表示资源不是表示组件
    EmployeeMapper employeeMapper;

    //key = "#id" 等价于“#root.args[0]” ,#a0,#p
    @Cacheable(cacheNames = "emp",key = "#id",condition = "#id>0",unless = "#result==null") //缓存id>0的，，结果大于0的不缓存
                //将方法的结果进行缓存 几个属性：cacheName/value指定缓存的名字，cacheManager管理多个Cache组件的，对缓存的crud操作在cache组件中，
                //每一个缓存组件有一个唯一名字
                //key: 缓存数据时使用的key,可以用它来指定（缓存数据是使用k-v形式），默认使用方法参数的值，或者编写spel表达式
                //可用的spEL表达式有下方这些
                //keyGenerator: key的生成器，默认使用参数的值作为key,也可以自己指定生成器生成组件id
                    //key/keyGenerator:二选一使用
                //cacheManager:指定缓存管理器
                //cacheResolver:指定缓存解析器。与上面二选一用一个
                //condition:指定符合条件下才缓存
                //unless:否定缓存，当unless指定的条件维true,方法的返回值不会被缓存，可以获取到结果进行判断
                //sync:是否使用异步模式
        //原理：1：自动配置类CacheAutoConfiguration
            //2:缓存得配置类：org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
            //。。。自动配置类CacheAutoConfiguration的@Import({CacheAutoConfiguration.CacheConfigurationImportSelector.class, CacheAutoConfiguration.CacheManagerEntityManagerFactoryDependsOnPostProcessor.class}) selectImports
            //3：哪个配置类默认生效：SimpleCacheConfiguration
            //4:给容器中注册了一个CacheManager,ConcurrentMapCacheManager
            //5:可以获取和创建ConcurrentMapCahce类型的缓存组件，他的作用将数据保存在CocurrentMap中
        //运行流程
            //1：防范运行之前先去查cache组件，按照cacheNames指定的名字获取（CacheManager先获取相应的缓存），第一次获取缓存没有会自动创建
            //2：去cache中查找缓存内容，使用一个key,默认就是方法的参数，key是按照某种策略生成的，默认时使用KeyGenetaror接口实现类生成
            //3：没有查到缓存就调用目标方法
            //4：将目标方法返回的结果，放进缓存中
     //核心：1：使用CacheManager[ConcurrentMapCacheManager]按照名字得到Cache[CurrentMapCache]组件
          //2：key使用keyGenerator生成的，默认是SimpleKeyGenetator

    public Employee getEmp(Integer id){
        System.out.println("查询-----------");
        Employee employeeById = employeeMapper.getEmployeeById(id);
        return employeeById;
    }
}

//spel表达式  名字       位置          描述              示例
    //methodName    root object 当前被调用的方法名    #root.methodName
    //method     root object    当前被调用的方法      #root.method.name
    //target     root object    当前被调用的目标对象  #root.target
    //targetClass root object   当前被调用的目标对象类 #root.targetClass
    //args       root object    当前被调用方法的参数列表 #root.args[0]
    //caches     root object    当前方法调用使用的缓存列表（如 @Cacheable(value={"cache1",#root.cache[0].name,"cache2"})）则有两个cache
    //argumentName evaluation context 方法参数的名字，可以直接#参数名，也可以使用#p0或者#a0的形式，0代表参数的索引   #iban #a0 #p0
    //result     evaluation context  方法执行后的返回值（仅当方法执行后的判断有效，如‘unless’,'cache put'的表达式，‘cache evict’的表达式，beforeInvocation=false）  #result
