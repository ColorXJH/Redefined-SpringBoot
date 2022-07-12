package com.example.springbooteasyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.springbooteasyexcel.bean.EmpData;
import com.example.springbooteasyexcel.bean.Employee;
import com.example.springbooteasyexcel.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName: TestController
 * @Package: com.example.springbooteasyexcel.controller
 * @Description:
 * @Datetime: 2022/7/12 21:08
 * @author: ColorXJH
 */
@RestController
public class TestController {
    @Autowired
    public EmployeeDao dao;

    @GetMapping("/simpleRead")
    public String  simpleRead(){
        String filename="E:\\software\\git\\gitWorkSpace\\Redefined-SpringBoot\\16-springboot-easyexcel\\src\\main\\resources\\TEST.xlsx";
        EasyExcel.read(filename, Employee.class,new ReadListener<Employee>(){
            public static final int BATCH_COUNT=1000;
            private List<Employee> cacheList= ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            private void saveData(List<Employee> list){
                dao.insertBatch(list);
            }
            @Override
            public void invoke(Employee data, AnalysisContext context) {
                cacheList.add(data);
                if(cacheList.size()>=BATCH_COUNT){
                    saveData(cacheList);
                    cacheList=ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData(cacheList);
            }
        }).sheet().doRead();
        return "插入完成";
    }

    @GetMapping("/repeatedWrite")
    public String  repeatedWrite(){
        System.out.println("9999-----------");
        String filename="E:\\software\\git\\gitWorkSpace\\Redefined-SpringBoot\\16-springboot-easyexcel\\src\\main\\resources\\TEST2.xlsx";
        int count=dao.queryCount();
        int loop=(int)Math.ceil(count/1000)+1;
        int start=1; int end=1;
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(filename, Employee.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            for(int i=1;i<=loop;i++){
                System.out.println("----------------"+i);
                end=500+start;
                List<Employee>data=dao.queryDataByPage(start,end);
                excelWriter.write(data, writeSheet);
                start=end+1;
            }
            start=end=1;
            return "读取完成";
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }

        }

    }

    /**
     * 文件下载
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).sheet("模板").doWrite(queryData());
    }


    /**
     * 文件上传
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Employee.class, new ReadListener<Employee>(){
            public static final int BATCH_COUNT=1000;
            private List<Employee> cacheList= ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            private void saveData(List<Employee> list){
                dao.insertBatch(list);
            }
            @Override
            public void invoke(Employee data, AnalysisContext context) {
                cacheList.add(data);
                if(cacheList.size()>=BATCH_COUNT){
                    saveData(cacheList);
                    cacheList=ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData(cacheList);
            }
        }).sheet().doRead();
        return "success";
    }



    private List<Employee>queryData(){
        return dao.queryData();
    }
}
