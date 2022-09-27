package com.example.springboot2web.converter;

import com.example.springboot2web.bean.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 自定义converter
 * @date 2022/9/27 9:57
 */
public class MyConverter implements HttpMessageConverter<Person> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }
    /*
     * Description: 服务器要统计所有MessageConverter都能写出哪些类型
     * @Author: ColorXJH
     * @Date: 2022/9/27 10:09
     * @param null
     * @Return:
     **/
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/color");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return HttpMessageConverter.super.getSupportedMediaTypes(clazz);
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
    /**
     * Description: 自定义协议数据的写出
     * @Author: ColorXJH
     * @Date: 2022/9/27 10:10
     * @param person
     * @param contentType
     * @param outputMessage
     * @Return: void
     **/
    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String data=person.getUserName()+";"+person.getAge()+";"+person.getBirth()+":"+person.getPet();
        //写出
        OutputStream body = outputMessage.getBody();
        body.write(data.getBytes());
    }
}
