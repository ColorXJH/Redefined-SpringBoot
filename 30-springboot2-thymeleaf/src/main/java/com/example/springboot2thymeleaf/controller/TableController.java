package com.example.springboot2thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot2thymeleaf.bean.MPUser;
import com.example.springboot2thymeleaf.mapper.UserMapper;
import com.example.springboot2thymeleaf.service.TableService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.Queue;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/28 11:11
 */
@Controller
public class TableController {
    @Autowired//报红不影响
    JdbcTemplate jdbcTemplate;

    @Autowired
    TableService tableService;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/basic_table")
    public String basicTable(Integer a){
        int x=10/a;
        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        List<MPUser>list=tableService.list();
        //分页查询数据
        Page<MPUser>userPage=new Page<>(pn,3);
        //分页查询结果
        Page<MPUser> page = tableService.page(userPage, null);
        model.addAttribute("users",page);
        //每次查询一次，增加以下计数，这个是metrics的监控
        counter.increment();
        return "table/dynamic_table";
    }
    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }
    @GetMapping("/editable_table")
    public String editable_table(){
        return "table/editable_table";
    }

    @ResponseBody
    @GetMapping("/queryTable")
    public Object queryTable(){
        return  jdbcTemplate.queryForList("SELECT * FROM test.employee");
    }

    @GetMapping("/test123")
    public void test1(){
        System.out.println(("----- selectAll method test ------"));
        List<MPUser> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
    @GetMapping("/user/delete/{id}")
    public String userDelete(@PathVariable("id")Integer id, @RequestParam("pn")Integer pn, RedirectAttributes ra){
        tableService.removeById(id);
        //方法1：
        //return "redirect:/dynamic_table?pn="+pn;
        //方法2：,添加RedirectAttributes
        ra.addAttribute("pn",pn);//参数以url的方式添加进去
        return "redirect:/dynamic_table";
    }


    Counter counter;
    //构造器的方式注入metrics
    @Autowired(required = false)//不加这个注解也行，其实这个属于idea的检测级别问题
    public TableController(MeterRegistry registry){
        counter = registry.counter("TableController.count");
    }
    //除了在构造器中传入MeterRegistry，也可以使用下面的写法(在有@Configuration注解类中书写)
    @Bean
    MeterBinder queueSize(Queue queue){
        return (registry)-> Gauge.builder("queueSize",queue::size).register(registry);
    }
}
