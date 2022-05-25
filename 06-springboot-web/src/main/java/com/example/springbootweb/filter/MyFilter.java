package com.example.springbootweb.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName: MyFilter
 * @Package: com.example.springbootweb.filter
 * @Description:
 * @Datetime: 2022/5/25 15:33
 * @author: ColorXJH
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("my filter process");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
