package com.example.springbootelasticsearch.service;

import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.example.springbootelasticsearch.entity.UserVO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;

import com.alibaba.fastjson.JSON;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName: DocumentDemoServiceImplTest
 * @Package: com.example.springbootelasticsearch.service
 * @Description:
 * @Datetime: 2022/9/6 21:03
 * @author: ColorXJH
 */
class DocumentDemoServiceImplTest {
    private final static String INDEX_NAME = "db_api_idx_uservo";

    @Autowired
    private DocumentDemoService documentDemoService;

    @Test
    void createByFluentDSL() throws Exception {
        // 构建文档数据
        UserVO userVO = new UserVO();
        userVO.setId(1L);
        userVO.setUserName("赵云2");
        userVO.setAge(11);
        userVO.setCreateTime(new Date());
        userVO.setUpdateTime(new Date());
        userVO.setEmail("ss.com");
        userVO.setVersion(1);
        userVO.setHeight(12D);

        // 新增一个文档
        IndexResponse response = documentDemoService.createByFluentDSL(INDEX_NAME, userVO.getId().toString(), userVO);

        System.out.println("response.forcedRefresh() -> " + response.forcedRefresh());
        System.out.println("response.toString() -> " + response.toString());
    }

    @Test
    void createByBuilderPattern() throws Exception {
        // 构建文档数据
        UserVO userVO = new UserVO();
        userVO.setId(2L);
        userVO.setUserName("赵云2");
        userVO.setAge(12);
        userVO.setCreateTime(new Date());
        userVO.setUpdateTime(new Date());
        userVO.setEmail("ss.com");
        userVO.setVersion(1);
        userVO.setHeight(12D);

        // 新增一个文档
        IndexResponse response = documentDemoService.createByBuilderPattern(INDEX_NAME, userVO.getId().toString(), userVO);

        System.out.println("response.toString() -> " + response.toString());

    }

    @Test
    void createByJson() throws Exception {
        // 构建文档数据
        UserVO userVO = new UserVO();
        userVO.setId(3L);
        userVO.setUserName("赵云3");
        userVO.setAge(13);
        userVO.setCreateTime(new Date());
        userVO.setUpdateTime(new Date());
        userVO.setEmail("ss.com");
        userVO.setVersion(1);
        userVO.setHeight(12D);

        // 新增一个文档
        IndexResponse response = documentDemoService.createByJson(INDEX_NAME, userVO.getId().toString(), JSON.toJSONString(userVO));

        System.out.println("response.toString() -> " + response.toString());

    }

    @Test
    void createAsync() {
        // 构建文档数据
        UserVO userVO = new UserVO();
        userVO.setId(4L);
        userVO.setUserName("赵云4");
        userVO.setAge(14);
        userVO.setCreateTime(new Date());
        userVO.setUpdateTime(new Date());
        userVO.setEmail("ss.com");
        userVO.setVersion(1);
        userVO.setHeight(12D);

        documentDemoService.createAsync(INDEX_NAME, userVO.getId().toString(), userVO, new BiConsumer<IndexResponse, Throwable>() {
            @Override
            public void accept(IndexResponse indexResponse, Throwable throwable) {
                // throwable必须为空
                Assertions.assertNull(throwable);
                // 验证结果
                System.out.println("response.toString() -> " + indexResponse.toString());
            }

        });
    }

    @Test
    void bulkCreate() throws Exception {
        int start = 5;
        int end = 10;

        // 构造文档集合
        List<Object> list = new ArrayList<>();
        for (int i = 5; i <= 7; i++) {
            UserVO userVO = new UserVO();
            userVO.setId(Long.valueOf(i));
            userVO.setUserName("赵云batch" + i );
            userVO.setHeight(1.88D);
            userVO.setAge(10 + i);
            userVO.setCreateTime(new Date());
            list.add(userVO);
        }

        // 批量新增
        BulkResponse response = documentDemoService.bulkCreate(INDEX_NAME, list);
        List<BulkResponseItem> items = response.items();
        for (BulkResponseItem item : items) {
            System.out.println("BulkResponseItem.toString() -> " + item.toString());
        }
    }

    @Test
    void getById() throws IOException {
        Long id = 1L;
        Object object = documentDemoService.getById(INDEX_NAME, id.toString());

        System.out.println("object ->" + object);
        // 无法直接强转，会报错
        //UserVO userVO = (UserVO) object;
        //System.out.println("userVO ->" + object);
    }

    @Test
    void getObjectNodeById() throws IOException {
        Long id = 1L;
        ObjectNode objectNode = documentDemoService.getObjectNodeById(INDEX_NAME, id.toString());

        Assertions.assertNotNull(objectNode);
        System.out.println("id ->" + objectNode.get("id").asLong());
        System.out.println("userName ->" + objectNode.get("userName").asText());

    }

    @Test
    void deleteById() {
    }

    @Test
    void bulkDeleteByIds() {
    }
}