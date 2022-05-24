package com.example.springbootweb.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @ClassName: MyErrorAttributes
 * @Package: com.example.springbootweb.config
 * @Description:
 * @Datetime: 2022/5/24 18:38
 * @author: ColorXJH
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    //返回的map就是页面和json能获取到的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object>map=super.getErrorAttributes(webRequest,options);
        map.put("company","colorXJH");
        //我们的异常处理器携带的数据
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        map.put("ext",ext);
        return map;
    }
}
