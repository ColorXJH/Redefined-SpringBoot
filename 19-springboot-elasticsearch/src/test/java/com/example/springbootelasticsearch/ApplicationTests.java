package com.example.springbootelasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private ElasticsearchClient client;

    /**
     * Description: 创建索引
     * @Author: ColorXJH
     * @Date: 2022/9/6 17:02
     * @param
     * @Return: void
     **/
    @Test
    public void createIndex() throws IOException {
       CreateIndexResponse indexResponse= client.indices().create(c->c.index("color6"));
       System.out.println(indexResponse.acknowledged());
    }

    /**
     * Description: 判断索引是否存在
     * @Author: ColorXJH
     * @Date: 2022/9/6 17:04
     * @param
     * @Return: void
     **/
    @Test
    public void testIndexExist() throws IOException {
        BooleanResponse exists=client.indices().exists(e->e.index("color6"));
        System.out.println(exists.value());
    }

    @Test
    void contextLoads() {
    }

}
