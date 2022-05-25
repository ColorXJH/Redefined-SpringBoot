package com.example.springbootwebjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
//一般的war包的web文件的目录结构是有src下有java,resources，还应该有webapp文件夹，我们可以自己添加，也可以使用project-structure生成
        //project-structure生成文件夹步骤如下：来到该项目的project-structure下，来到modules模块,右边web模块下面有一个Web Resources Directory(web资源，目录)
        //双击路径（里面显示默认就是在当前项目的src/main/webapp路径下生成），点击ok=>yes就可以
        //Web Resources Directory上放有一个Deployment Descriptors(部署描述符，就是生成web.xml文件的地方)，右击加号“+”，生成web.xml文件，注意修改路径，生成
        //在当前创建的额webapp文件夹下面的WEB-INF文件夹下=》project-name\src\main\webapp\WEB-INF\web.xml

//如何打成war包启动》
    //1:将项目打成war包，拿出去放入外部容器使用
    //2:将项目打成war包，将外部容器整合到idea中
        //1:在运行窗口这点击Edit Configuration,来到编辑页面，左上方看到是springboot应用，这是不行的，在左上方点击“+”，选择Tomcat Server 表示加入外部的服务器
        //2:选择Local表示本地的服务器，进入新的页面，最上方给Tomcat服务器命名，然后Server页签点击右边Configure,指定tomcat路径
        //3:在Deployment页签（部署页签）右边点击“+”，添加我们要部署的哪个项目，先择artifact,然后选择我们的war项目，然后apply,注意文件访问application-context可以自行设置
            //如果配置之后启动出现tomcat控制台乱码，
                //1:修改IDEA中setting的设置Editor——File Encodings 修改编码格式 Encoding设置为UTF-8
                //2:修改本地apache-tomcat-9.0.50\conf\logging.properties文件，修改如下参数：其他Encoding设置为UTF-8,控制台的Encoding设置为GBK
                    //当所有都是UTF-8时，IDEA默认字符集是GBK会出现中文字符集错误，导致乱码问题，而我们对于控制台打印使用的字符集设置为GBK，与windows环境下IDEA的默认字符集一致，因此可以解决。

            //1:创建一个war项目（利用好idea创建好war项目的目录结构，包含webapp, WEB-INF web.xml）
            //2:将嵌入式的tomcat指定为provided
            //3:必须编写一个SpringBootServletInitializer的实现类，重写其中的configure方法
                //return application.sources(Application.class);//调用application.source方法，传入springboot的主程序类
            //4:启动服务器就可以使用了


    //启动原理
       //1jar包方式：执行SpringBoot主类的main方法，启动ioc容器，创建嵌入式的servlet容器
       //2war包方式：启动外部服务器，服务器启动springboot应用，springboot再启动ioc容器，服务器为什么能启动springboot应用？如下：
        //1:Servlet3.0标准ServletContainerInitializer扫描所有的jar包中的META-INF/services/javax.servlet.ServletContainerInitializer文件指定的类并加载
                                                           //spring web jar包下有这个，里面是：org.springframework.web.SpringServletContainerInitializer，应用启动就启动这个类
              //SpringServletContainerInitializer将@HandlesTypes(WebApplicationInitializer.class)标注的所有这个类型的类都传入到onStartup方法中的第一个参数中set集合中
              //为这些WebApplicationInitializer类型的类创建实例
              //每一个WebApplicationInitializer都调用自己的onStartUp方法
                    //WebApplicationInitializer接口的实现有抽象类SpringBootServletInitializer，抽象类有子类ServletInitializer,这个类就是我们war项目创建的类
                    //相当于我们项目的ServletInitializer类会被创建对象并执行onStartUp方法
                        //ServletInitializer父类的onStartUp方法会创建createRootApplicationContext（创建容器），其中调用了configure方法，而我们的子类ServletInitializer重写了该方法
                        //将我们springboot的主程序类传入进来，接下来builder帮我们创建了一个springboot应用：SpringApplication application = builder.build();
                        //并启动：return run(application);--》然后启动ioc容器：refreshContext(context);
        //2:加载spring-web 包下的SpringServletContainerInitializer
        //3:扫描@HandleType(WebApplicationInitializer)
        //4：加载SpringBootServletInitializer并运行onStartUp方法
        //5:加载@SpringBootApplication主类，启动容器