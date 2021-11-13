package com.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: ColorXJH
 * @CreateDate: 2021/11/13 16:37
 * @Version: 1.0
 */
@SpringBootApplication //来标注一个主程序类--springboot应用
    //内部包含@SpringBootConfiguration:springboot的配置类，内部包含@Configuration:一个配置类注解，等同于一个配置文件，配置类内部是包含一个@Component组件注解
    //@EnableAutoConfiguration；开启自动配置功能，以前需要我们配置的东西springboot帮我们自动配置了，该注解告诉springboot打开自动配置，这样配置才能生效
    //@AutoConfigurationPackage:自动配置包 内部包含@import注解（spring的底层注解），给容器导入一个组件@Import({Registrar.class})
    //将主配置类（@SpringBootApplication标注的类）的所在包及其子包中的所有组件扫描到spring容器中
    //@Import(EnableAutoConfigurationImportSelector.class),给容器中导入组件，EnableAutoConfigurationImportSelector：导入组件的选择器
        //将所有需要的组件以全类名的方式返回，这些组件就会被添加到容器中：selectImports方法，最终会给容器中导入非常多的自动配置类（xxxAutoConfiguration）,就是给容器中导入
        //这个场景所需要的所有组件，并配置好这些组件，有了自动配置类就免去了手动编写配置注入功能组件等工作
        //List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader())
        //Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
        //springboot在启动的时候从类路径下的meta-inf/spring.factories中获取EnableAutoConfiguration指定的值！！！，将这些值作为自动配置类导入到容器中，自动配置类就生效了，就能帮我们进行自动配置
        //以前我们需要自己配置的东西，自动配置类都帮我们做了（这些自动配置类都在spring-boot-autoconfiguration jar包下） j2ee的整体解决方案
public class MyApplication {
    public static void main(String[] args) {
        //启动spring应用
        SpringApplication.run(MyApplication.class,args);
    }
}
