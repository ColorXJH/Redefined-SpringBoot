package com.example.springboot2web.config;

import com.example.springboot2web.bean.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

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
