package com.example.springboot2web.config;

import com.example.springboot2web.bean.Pet;
import com.example.springboot2web.converter.MyConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/23 9:45
 */
//@Configuration(proxyBeanMethods = false)//无依赖，快速加载
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*自定义表单rest风格传递参数方法名*/
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter=new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return  methodFilter;
    }

    //适配浏览器的修改内容协商类型
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //Map<String, MediaType> mediaTypes
        Map<String, MediaType> mediaTypes=new HashMap<>();
        mediaTypes.put("json",MediaType.APPLICATION_JSON);
        mediaTypes.put("xml",MediaType.APPLICATION_XML);
        mediaTypes.put("color",MediaType.parseMediaType("application/color"));
        //基于参数
        ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
        //基于请求头的
        HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
        configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy,headerContentNegotiationStrategy));
    }

    //扩展消息协商机制(post-man方式)
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MyConverter());
    }

    //1:自定义UrlPathHelper，设置helper.setRemoveSemicolonContent(false);不移除分号后面的内容
    //这时候矩阵变量才会生效
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper helper=new UrlPathHelper();
        helper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(helper);
    }

    //自定义转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class,Pet.class,(x)->{
            if(x!=null){
                Pet pet=new Pet();
                pet.setName(x.split(",")[0]);
                pet.setAge(Integer.parseInt(x.split(",")[1]));
                return pet;
            }
            return null;
        });
    }

    //2:容器中放一个WebMvcConfigurer,重写其方法
    //@Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper helper=new UrlPathHelper();
                helper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(helper);
            }
        };
    }
}
