package com.example.springbootweb.config;

import com.example.springbootweb.exception.LoginOutException;
import com.example.springbootweb.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GlobalExceptionHandler
 * @Package: com.example.springbootweb.config
 * @Description: 该注解描述的类为控制全局异常处理类，在此类中定义一场处理方法
 * @Datetime: 2022/5/19 17:32
 * @author: ColorXJH
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginOutException.class)
    public String doLoginOutException(LoginOutException e){
        e.printStackTrace();
        return "redirect:/index.html";
    }
    /*自定义json 返回数据 json格式 @ResponseBody  浏览器客户端都是json数据 无法自适应*/
    /*@ResponseBody
    @ExceptionHandler(MyException.class)
    public Map<String,Object> myHandlerException(Exception e){
        Map<String,Object> map=new HashMap<>();
        map.put("code","user not exist");
        map.put("message",e.getMessage());
        return map;
    }*/
    //为了达到自适应效果，转发到/error（他是springboot默认的处理自适应的地址）
    /*@ExceptionHandler(MyException.class)
    public String myHandlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入我们自己的错误状态码 4xx/5xx,否则就无法进入错误界面的处理流程
        request.setAttribute("javax.servlet.error.status_code",500);
        //转发到/error
        return "forward:/error";
    }*/

    //为了达到自适应效果,可以通过定制ErrorAttrributes改变需要返回的内容
    @ExceptionHandler(MyException.class)
    public String myHandlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入我们自己的错误状态码 4xx/5xx,否则就无法进入错误界面的处理流程
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user not exist");
        map.put("message","MY MESSAGE");
        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }
}
