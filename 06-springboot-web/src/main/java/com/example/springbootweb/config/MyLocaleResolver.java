package com.example.springbootweb.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @ClassName: MyLocaleResolver
 * @Package: com.example.springbootweb.config
 * @Description:
 * @Datetime: 2022/2/23 22:05
 * @author: ColorXJH
 */
/*
 * 功能描述: 可以在连接上携带区域信息 ：th:href="@{/index.html?l=zh_CN}" th:href="@{/index.html?l=en_US}"
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l=request.getParameter("l");
        Locale locale=Locale.getDefault();//如果没有获取到参数则使用系统自带的语言
        if(!StringUtils.isEmpty(l)){
          String[]split=  l.split("_");
            //第一个参数为语言代码，第二个参数为国家代码
            locale=new Locale(split[0],split[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
