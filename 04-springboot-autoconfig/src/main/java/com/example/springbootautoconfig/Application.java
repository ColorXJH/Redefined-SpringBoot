package com.example.springbootautoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//自动配置原理
    //1:springboot启动的时候加载主配置类，开启了自动配置的功能@EnableAutoConfiguration
    //2:@EnableAutoConfiguration左右是利用@Import({EnableAutoConfigurationImportSelector.class})给容器中导入一些组件，详细可以查看selectImports方法
        //List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);获取候选的配置
        //List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
            //从autoconfigure jar文件中加载META-INF/spring.factories，获取组件信息并包装成properties对象
            //从properties中获取到EnableAutoConfiguration.class类（类名）对应的值，然后把他们添加在容器中
            //每一个xxxAutoConfiguration类都是容器中的组件，都加入到容器中，用他们做自动配置
    //3:每一个自动配置类来实现自动配置功能
    //4：以HttpEncodingAutoConfiguration为例子来说明：他头上有一大批注解：如下
/*
@Configuration(  //表示这是一个配置类,以前编写的配置文件一样，也可以给容器中添加组件
        proxyBeanMethods = false
)
@EnableConfigurationProperties({ServerProperties.class}) //启用指定类（ServerProperties）的configuration properties功能，并把ServerProperties加入到ioc的容器中
    //ConfigurationProperties功能：将配置文件中对应的值和他标注的类绑定起来
@ConditionalOnWebApplication( //spring底层的@Conditional注解,根据不同的条件，如果满足指定的条件，整个配置类里面的配置就会生效 （判断当前应用是不是web应用，如果是，当前配置类生效 ）
        type = ConditionalOnWebApplication.Type.SERVLET
)
@ConditionalOnClass({CharacterEncodingFilter.class})//判断当前项目有没有这个类CharacterEncodingFilter：springmvc中进行乱码解决的过滤器
@ConditionalOnProperty( //判断配置文件中是否存在某个配置server.servlet.encoding.enabled;如果不存在，判断也是成立的===》即使配置文件中不配置server.servlet.encoding.enabled属性，也默认生效
        prefix = "server.servlet.encoding",
        value = {"enabled"},
        matchIfMissing = true
)*/
    //根据当前不同的条件判断来决定这个类是否生效，其内部有@Bean注解：给容器中添加组件 ,在只有一个有参构造器的情况下，参数的值就会从容器中拿，这些组件的属性是从对应的properties类中获取的，而properties类的属性又是和配置文件绑定的
    //5:所有在配置文件中能配置的属性都是在xxxProperties类中封装着，配置文件能配置什么就可以参照某个功能队对应的这个属性类



//精髓：
    //1:springboot启动会加载大量的自动配置类
    //2:我们看我们需要的功能有没有springboot默认写好的自动配置类，
    //3:我们再来看这个自动配置类中配置了哪些组件，如果有我们需要用的组件就不需要再配置了，如果没有需要我们自己来写
    //4:给容器中自动配置类添加组件的时候，会从properties类中获取某些属性，我们可以再配置文件中指定这些属性的值
        //xxxAuatoConfiguratiuon类:自动配置类,给容器添加组件
        //xxxProperties类：封装配置文件中相关属性


//1:@Conditional派生注解（spring注解版原生的@Conditional左右）
    //作用：必须是@Conditional指定的条件成立，才给容器添加组件，配置类里面的所有内容才生效，常见的如下
    //@ConditionalOnJava    系统的java版本是否符合要求
    //@ConditionalOnBean    容器中存在指定的bean
    //@ConditionalOnMissingBean     容器中不存在指定的bean
    //@ConditionalOnExpression      满足spel表达式
    //@ConditionalOnClass       系统中有指定的类
    //@ConditionalOnMissingClass        系统中没有指定的类
    //@ConditionalOnSingleCandidate     容器中只有一个指定的bean,或者这个bean是首选bean
    //@ConditionalOnProperty    系统中指定的属性是否有指定的值
    //@ConditionalOnResource        类路径下是否存在指定的资源
    //@ConditionalOnWebApplication      当前是web环境
    //@ConditionalOnNotWebApplication   当前不是web环境
    //@ConditionalOnJndi    JNDI存在指定项


//配置文件中 debug=true，启动springboot时会打印匹配的和未匹配的报告到控制台