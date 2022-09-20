# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#using.devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


## springboot一些注解

* @Configuration
> @Configuration 告诉springboot这是一个配置类===配置文件
> proxyBeanMethods方法默认为true,外部调用代理对象内部方法多次返回单实例对象
> Full/Lite:
> 配置类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
> 配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式
* @Bean
> @Bean 标注这个类被IOC容器管理
* @ImportResource
> @ImportResource(locations = {"classpath:beans.xml"})//加载外部资源
* @Import(User.class,Pet.class)
> 给容器中自动创建组件(调用类的无参构造器)，默认组件的名字是全类名：com.xx.xx.Pig
* @Conditional
> 条件装备，有很多派生注解
* @ConfigurationProperties+@Component
> 配置属性，配置绑定，包含属性prefix,表示属性前缀，只有在容器中的组件才会拥有springboot提供的强大功能
> @ConfigurationProperties+@Component配置导入
* @EnableConfigurationProperties+@ConfigurationProperties
> 开启配置类自动绑定功能，并自动导入bean到容器中,注意只能绑定application.properties文件
* @SpringBootApplication
> 该注解由其他多个注解合成而成：@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
excludeFilters = {@Filter(
type = FilterType.CUSTOM,
classes = {TypeExcludeFilter.class}
), @Filter(
type = FilterType.CUSTOM,
classes = {AutoConfigurationExcludeFilter.class}
)}
)
* @SpringBootConfiguration
> 该注解表示配置类
* @ComponentScan
> 该注解表示包扫描
* @EnableAutoConfiguration 
> 该注解包含 @@AutoConfigurationPackage:利用Register给容器中导入一些列组件，将指定的一个包下的所有组件都导入进来
* @Import({AutoConfigurationImportSelector.class})
> getAutoConfigurationEntry利用这个方法给容器中批量导入==》this.getCandidateConfigurations(annotationMetadata, attributes);
> 获取所有需要导入到容器中的配置类
> loadFactoryNames:该方法加载所有配置文件META-INF/spring.factories文件中的配置类
> spring-boot-autoconfigure-2.7.3.jar包中的配置文件，
> xxxAutoConfiguration---》很多组件---》xxxProperties类加载配置---》application.properties修改配置