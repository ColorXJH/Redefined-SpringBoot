package com.example.springbootweb;

import com.example.springbootweb.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //??????????????????????????????viewResolver,?????????????????????viewResolver?????????????????????????????????springboot??????
    //???????????????????????????viewResolver???????????????DispatcherServerlet??????doDispatch???????????????????????????????????????
    //????????????????????????????????????????????????????????????????????????debug???????????????????????????viewResolver
    //@Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }
    private static class MyViewResolver implements ViewResolver{

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}

//web??????  SpringBoot??????SpringMVC???web???????????? WebMvcAutoConfiguration ????????????????????????
    //1?????????springboot????????????????????????????????????
    //2???springboot???????????????????????????????????????????????????????????????????????????????????????????????????????????????
    //3?????????????????????
        //??????????????????;????????????springboot??????????????????????????????????????????????????????????????????????????????????????????
        //xxxAutoConfiguration?????????????????????????????????????????????
        //xxxProperties?????????????????????????????????????????????
//springboot??????????????????????????????,
    //1:??????/webjars/**???????????????????????????classpath:/META-INF/resources/webjars/????????????
        //webjars:???jar?????????????????????????????????http://www.webjars.org
        //MacBook??????????????????command+shift+4    command+shift+3
        //localhost:8080/webjars/jquery/3.3.1/src/jquery.js
    //2??????????????????????????????private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
        //Java?????????resources???????????????????????????????????????
            //localhost:8080/new1.css   ??????static????????????????????????????????????????????????https://www.jb51.net/article/175230.htm
                    //???????????????springboot?????????????????????????????????????????????????????????
                    //???@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})??????????????????????????????
    //????????????????????????????????????index.html???????????????/**??????
    //????????????**/favicon.ico???????????????????????????????????????????????????????????????????????????????????????(????????????????????????????????????????????????ctrl+f5???????????????????????????)
    //???????????????????????????????????????????????????
        //?????????????????????????????????????????????http://??????+?????????+favicon.ico??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        /*???????????????????????????
        a.???application.properties???????????????????????????????????????
        b.??????????????????????????????*/
    //???????????????1?????????????????????????????????????????????????????????????????????????????????????????????ctrl+f5????????????
            //2: ???????????????????????????????????????????????????????????????????????????ico?????????head????????????<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
                //??????????????????????????????resources???????????????????????????/favicon.ico???
                //???????????????????????????http://??????+favicon.ico??????????????????????????????????????????????????????????????????http://??????+?????????+favicon.ico????????????????????????????????????
                    //??????????????????resources???????????????????????????/favicon.ico???
                //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            //3:????????????????????????????????????????????????????????????????????????js???
            //????????????HandlerInterceptor???????????????afterCompletion?????????????????????????????????js??????
            /*String link = "<script>" +
                "var link = document.createElement('link');" +
                "link.type = 'image/x-icon';" +
                "link.rel = 'shortcut icon';" +
                "link.href = '/nascan/images/favicon.ico';" +
                "document.getElementsByTagName('head')[0].appendChild(link);" +
                "</script>";
            response.getWriter().append(link);*/
            //????????????????????????????????????????????????????????????getOutputStream() has already been called for this response
                //?????????response.getWriter().append(link);????????????????????????out??????????????????
                //https://www.cnblogs.com/jusha/p/11979734.html
//3:????????????
    //JSP???Velocity, Freemarker, Thymeleaf
    //springboot????????????thymeleaf,????????????????????????????????????
    //<dependency>
        //<groupId>org.springframework.boot</groupId>
        //<artifactId>spring-boot-starter-thymeleaf</artifactId>
    //</dependency>
    //?????????<properties>???????????????thymeleaf???????????????????????????
    //<thymeleaf.version>3.0.12.RELEASE</thymeleaf.version>
    //<thymeleaf-layout-dialect.version>3.0.0</thymeleaf-layout-dialect.version>
        //??????thymeleaf3??????layout-dialect 2   thymeleaf2??????layout-dialect1??????
    //thymeleaf??????????????????
        //public static final String DEFAULT_PREFIX = "classpath:/templates/";
        //public static final String DEFAULT_SUFFIX = ".html";
    //<html lang="en" xmlns:th="http://www.thymeleaf.org">??????thymeleaf???????????????

//????????????????????????thymeleaf????????????
    //1???th:text :?????????????????????????????????
        //th:??????html?????? ?????????html????????????
    //2??????????????????

//springmvc ???????????????
    //1???springboot??????????????????springmvc,????????????spring ??????????????????springboot????????????web???????????????
    //2??????????????????xxxViewResolver:??????????????????????????????????????????????????????????????????view???,????????????????????????????????????????????????????????????
        //ContentNegotiatingViewResolver
        //??????????????????????????????????????????????????????????????????????????????????????????ContentNegotiatingViewResolver???????????????????????????
    //3:???????????????webjars
    //4?????????????????????index.html
    //5:convert??????????????????????????????  18(??????)-???integer
    //6???Formatter:???????????? 2021-12-01-???Date
    //7: httpMessageConverters:springmvc????????????http?????????????????????User--json;
        //????????????????????????????????????HttpMessageConverters
        //????????????????????????converters,????????????????????????????????????@Bean..???
    //8: messageCodeResolver??????????????????????????????
    //9???ConfigurableWebBindingInitializer????????????WebBindingInitializer???web??????????????????????????????===???javaBean???:initBinder
        //?????????????????????
//??????springmvc
    //<!--springmvc?????????????????????-->
    //<mvc:view-controller path="/hello" view-name="success" />
    //<!--springmvc???????????????-->
    //<mvc:interceptors>
    //<mvc:interceptor>
    //<!--?????????????????????-->
    //<mvc:mapping path="/hello"/>
    //<!--????????????????????????-->
    //<bean></bean>
    //</mvc:interceptor>
    //</mvc:interceptors>
    //????????????????????????@Configuration???????????????????????????????????????????????????WebMvcConfigurerAdapter,??????????????????@EnableWebMvc??????
    //?????????????????????????????????????????????????????????
        //?????????1???WebMvcAutoConfiguration???springMVC??????????????????
            //2 WebMvcAutoConfiguration?????????????????????WebMvcAutoConfigurationAdapter??????????????????WebMvcConfigurerAdapter?????????????????????????????????????????????
                //@Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class}),??????????????????????????????????????????????????????????????????????????????WebMvcConfigurer
                    //@Autowired(
                    //        required = false
                    //)
                    //public void setConfigurers(List<WebMvcConfigurer> configurers) {
                    //    if (!CollectionUtils.isEmpty(configurers)) {
                    //        this.configurers.addWebMvcConfigurers(configurers);
                    //    }
                    //
                    //}
                //3????????????????????????WebMvcConfigurer?????????????????????
                //4: ?????????????????????????????????
            //???????????????springmvc??????????????????????????????????????????????????????
        //@EnableWebMvc?????????????????????????????????springmvc,springboot???springmvc?????????????????????????????????????????????????????????
        //???????????????????????????????????????@EnableWebMvc

        //????????????????????????@EnableWebMvc?????????????????????????????????
            //1????????????????????????????????????@Import({DelegatingWebMvcConfiguration.class})???????????????WebMvcConfigurationSupport?????????????????????WebMvcAutoConfiguration????????????????????????
        /*@Configuration
        public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {*/
        //?????????????????????????????????
        /*@Configuration(
                proxyBeanMethods = false
        )
        @ConditionalOnWebApplication(
                type = ConditionalOnWebApplication.Type.SERVLET
        )
        @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
        @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
        @AutoConfigureOrder(-2147483638)
        @AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
        public class WebMvcAutoConfiguration {*/
        //??????????????????????????? @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})???????????????????????????
        //?????????WebMvcConfigurationSupport??????springmvc??????????????????

//????????????springboot???????????????
    //1???springboot????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????@ConditionalOnMissingBean...???
    //2???@Configuration ????????????
    //3:???springboot?????????????????????xxxConfigurer??????????????????????????????
    //4:???springboot??????????????????xxxCustomizer??????????????????????????????



//2:?????????
    //springmvc????????????
        //1:???????????????????????????
        //2:??????ResourceBoundleMessageSource???????????????????????????
        //3:???????????????fmt:message(jsp????????????)
    //springboot????????????
        //1??????????????????????????????,??????????????????????????????????????????(???????????????????????????_????????????.properties ????????????)
        //2:springboot??????????????????????????????????????????????????????
            //??????private String basename = "messages";??????????????????????????????????????????????????????messages.properties?????????
            /*@Configuration(
                    proxyBeanMethods = false
            )
            @ConditionalOnMissingBean(
                    name = {"messageSource"},
                    search = SearchStrategy.CURRENT
            )
            @AutoConfigureOrder(-2147483648)
            @Conditional({org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration.ResourceBundleCondition.class})
            @EnableConfigurationProperties
            public class MessageSourceAutoConfiguration {*/


            /*@Bean
            public MessageSource messageSource(MessageSourceProperties properties) {
                ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
                if (StringUtils.hasText(properties.getBasename())) {
                    messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
                }*/
        //3:??????????????????????????????
            //?????????jsp????????????fmt:message??????
            //??????????????????thymeleaf:MessageExpression??????????????? #{}
                //idea???????????????????????????????????????????????????jdk??????????????????????????????setting,??????project structure?????????????????????setting-??? new project setrup
                //?????????settings ???structure??????
        //4:???????????????????????????????????????????????????????????????
            //?????????jav?????????Local(??????????????????)???springmvc????????????LocalResolver?????????????????????????????????springboot??????springmvc????????????resolver,????????????????????????
            //LocaleResolver,???????????????????????????????????????????????????????????????????????????????????????????????????????????????request.getLocale()??????????????????????????????Locale???????????????
        //5:?????????????????????????????????????????????????????????????????????????????????(MyLocaleResolver)

        //3:??????
            //????????????????????????????????????????????????
               //1):???????????????????????????  spring.thymeleaf.cache=false
               //2):?????????????????????ctrl+F9,????????????
            //???????????????????????????
                //<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
            //?????????????????????????????????????????????????????????????????????url?????????????????????????????????????????????????????????

        //4:crud????????????
            //???????????????1???restful crud     uri????????? /????????????/????????????  ???????????????http??????????????????????????????crud??????
            //????????? ?????? ???????????????deleteEmp?id=1   restful: emp/{id}--delete????????????
            //???????????????thymeleaf????????????????????????
                //?????????????????????th:fragment="topbar"
                //??????????????????????????????<div th:insert="~{commons/bar::topbar}"></div> ==???<div th:insert="commons/bar::topbar"></div>
                    //????????????????????????  ?????? ?????????::?????????
                    //insert replace include ?????????????????????  1?????????????????????????????????????????? 2??????????????????????????????????????? 3?????????????????????????????????????????????????????????
                    //??????????????????????????????[[~{}]], [(~{})]
        //?????????????????????????????????

        //??????????????????
            //1:springboot???????????????????????????
                //?????????????????????????????????????????????
                //???????????????????????????????????????json??????
            //2:?????????????????????ErrorMvcAutoConfiguration:??????????????????????????????
                //??????????????????????????????????????????
                    //1:DefaultErrorAttributes
                        //?????????????????????????????????
                    //2:BasicErrorController
                        //@RequestMapping({"${server.error.path:${error.path:/error}}"}),????????????/error??????
                    //3:ErrorPageCustomer
                        //private String path="/error",???????????????????????????error?????????????????????=???????????????servlet???web.xml???????????????????????????
                    //4:DefaultErrorViewResolver
                //?????????????????????4XX??????5XX??????????????????ErrorPageCustomer?????????????????????????????????????????????,????????????/error??????????????????BasicErrorController?????????
                    //errorHtml/error??????????????????html?????????json?????????????????????????????????????????????accept???text/html,??????????????????Accept: */*?????????springboot????????????
                    //1:????????????????????????????????????DefaultErrorViewResolver?????????-?????????springboot????????????error/viewname(error/404)
                        //??????????????????????????????????????????????????????????????????errorViewName?????????????????????
                        //??????????????????????????????????????????????????????????????????viewName???????????????
        //2:????????????????????????
             //1:????????????????????????
                //1:??????????????????????????????error/????????? ??????????????????????????????????????????.html ??????????????????????????????error??????????????????????????????????????????????????????????????????????????????
                            //??????4XX/5XX??????????????????????????????4XX/5XX.html??????
                            //2:????????????????????????
                            //timestamp ?????????  status ????????? error ????????????  exception ???????????? message ???????????? errors jsr303????????????????????????????????????BindingResults???
                //2:??????????????????????????????????????????????????????????????????????????????????????????????????? static???
                //3:??????????????????????????????????????????springboot?????????????????????
            //2:?????????????????????json???????????????????????????????????????
                /*?????????json ???????????? json?????? @ResponseBody*//*
                 @ResponseBody
                    @ExceptionHandler(MyException.class)
                    public Map<String,Object> myHandlerException(Exception e){
                        Map<String,Object> map=new HashMap<>();
                        map.put("code","user not exist");
                        map.put("message",e.getMessage());
                     return map;
                    }*/
                    //?????????????????????????????? ???????????????????????????json??????
                //????????????????????????????????????????????????
                    //1??????????????????????????????/error???????????????BasicErrorController????????????????????????????????????????????????getErrorAttributes?????????
                    //(???AbstractErrorController(ErrorController)???????????????)
                    //????????????errorController????????????????????????AbstractErrorController??????????????????????????????error/errorHtml?????????????????????????????????????????????

                    //2:????????????????????????????????????json????????????????????????????????????errorAttributes.getErrorAttributes??????
                        //?????????DefaultErrorAttributes.getErrorAttributes()??????????????????????????????
                        //?????????ErrorAttributes
                    /*@Component
                    public class MyErrorAttributes extends DefaultErrorAttributes {

                        //?????????map???????????????json???????????????????????????
                        @Override
                        public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                            Map<String, Object>map=super.getErrorAttributes(webRequest,options);
                            map.put("company","colorXJH");
                            //???????????????????????????????????????
                            Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
                            map.put("ext",ext);
                            return map;
                        }
                    }*/
                    //?????????????????????????????????????????????request????????????
                    //???????????????????????????,??????????????????ErrorAttrributes???????????????????????????
                    /*@ExceptionHandler(MyException.class)
                    public String myHandlerException(Exception e, HttpServletRequest request){
                        Map<String,Object> map=new HashMap<>();
                        //???????????????????????????????????? 4xx/5xx,????????????????????????????????????????????????
                        request.setAttribute("javax.servlet.error.status_code",500);
                        map.put("code","user not exist");
                        map.put("message","MY MESSAGE");
                        request.setAttribute("ext",map);
                        //?????????/error
                        return "forward:/error";
                    }*/

                    //http://localhost:8080/crud/myhello?user=aaa



        //?????????????????????servlet
        //ConfigurableEmbeddedServletContainer
        //EmbeddedServletContainerCustomizer
        //??????servlet,Filter,Listener
            //??????????????????web?????????????????? src/main???????????????webapp?????????????????????webinf,?????????web.xml,???????????????????????????????????????web.xml???
            //??????springboot????????????jar??????????????????????????????servlet???????????????springboot???web???????????????web.xml???????????????????????????????????????
            //springboot?????????????????????springmvc???????????????????????????springmvc??????????????????dispatcherServlet???????????????????????????DispatcherServletAutoConfiguration???
            //??????????????? / ???????????????????????????????????????????????????jsp??????  /*?????????jsp?????????????????????server.servletPath????????????springmvc??????????????????????????????????????????
                //ServletRegistrationBean
                //FilterRegistrationBean
                //ServletListenerRigistrationBean
        //????????????servlet??????Jetty(?????????????????????) Undertow(?????????jsp)
            //?????????+F4=??????????????????????????  ctrl+H????????????

        //springboot??????????????????????????????Servlet?????????tomcat???
            //??????1????????????????????????servlet?????????????????????????????????servlet?????????config????????????xml????????????????????????????????????????????????
                //??????springboot??????????????????server??????????????????ServerProperties???????????????????????????????????????????????????
                //????????????EmbeddedServletContainerCustomizer???????????????servlet??????????????????????????????servlet???????????????
            //2:springboot????????????????????????servlet?????????
                //?????????servlet????????????????????????
                    //springboot_autoconfigure.jar?????????web???????????????embeded???????????????EmbeddedWebServerFactoryCustomizerAutoConfiguration???????????????
                    // @ConditionalOnClass({Tomcat.class, UpgradeProtocol.class})
                    //?????????????????????????????? idea->view->tool-windows->structure
                //?????????????????????????????????????????????????????????=???1?????????serverProperties???????????? 2??????EmbededServletContainerCustomizer?????????servlet??????
                //????????????resource???????????????
            //?????????servlet????????????
                //????????????????????????,???????????????jar???
                //????????????????????????jsp,????????????????????????????????????????????????ServerProperties,?????????????????????????????????????????????Servlet????????????????????????EmbededServletContainerFactory??????
            //?????????servlet???????????????????????????Tomcat->??????war??????????????????





