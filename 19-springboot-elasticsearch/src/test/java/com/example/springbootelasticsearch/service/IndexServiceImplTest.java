package com.example.springbootelasticsearch.service;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.util.ObjectBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName: IndexServiceImplTest
 * @Package: com.example.springbootelasticsearch.service
 * @Description:
 * @Datetime: 2022/9/6 20:58
 * @author: ColorXJH
 */
class IndexServiceImplTest {
    @Autowired
    private IndexService indexService;

    @Test
    void createIndex() throws IOException {
        String indexName = "db_api_idx1";
        indexService.createIndex(indexName);

        //Assertions.assertTrue(indexService.indexExists(indexName));
        //indexService.createIndex(indexName);
        //Assertions.assertFalse(indexService.indexExists(indexName));
    }

    @Test
    void testCreateIndex() throws IOException {
        // 索引名
        String indexName = "db_api_idx2";

        // 构建setting
        Function<IndexSettings.Builder, ObjectBuilder<IndexSettings>> settingFn = sBuilder -> sBuilder
                .index(iBuilder -> iBuilder
                        // 三个分片
                        .numberOfShards("3")
                        // 一个副本
                        .numberOfReplicas("1")
                );

        // 索引字段，每个字段都有自己的property
        Property keywordProperty = Property.of(pBuilder -> pBuilder.keyword(keywordPropertyBuilder -> keywordPropertyBuilder.ignoreAbove(256)));
        Property integerProperty = Property.of(pBuilder -> pBuilder.integer(integerNumberPropertyBuilder -> integerNumberPropertyBuilder));
        Property textProperty = Property.of(pBuilder -> pBuilder.text(tBuilder -> tBuilder));

        // 构建mapping
        Function<TypeMapping.Builder, ObjectBuilder<TypeMapping>> mappingFn = mBuilder -> mBuilder
                .properties("name", keywordProperty)
                .properties("age", integerProperty)
                .properties("description", textProperty);

        // 创建索引，并指定setting和mapping
        indexService.createIndex(indexName, settingFn, mappingFn);
    }

    @Test
    void deleteIndex() throws IOException {
        String indexName = "db_api_idx1";
        indexService.deleteIndex(indexName);
    }

    @Test
    void updateIndexProperty() throws IOException {
        String indexName = "db_api_idx2";

        // 索引字段，每个字段都有自己的property
        Property keywordProperty = Property.of(pBuilder -> pBuilder.keyword(keywordPropertyBuilder -> keywordPropertyBuilder.ignoreAbove(1024)));
        Property integerProperty = Property.of(pBuilder -> pBuilder.integer(integerNumberPropertyBuilder -> integerNumberPropertyBuilder));
        Property textProperty = Property.of(pBuilder -> pBuilder.text(tBuilder -> tBuilder));

        HashMap<String, Property> propertyMap = new HashMap<>();
        propertyMap.put("name", keywordProperty);
        propertyMap.put("description", textProperty);
        propertyMap.put("address", textProperty);

        // 构建mapping
        indexService.updateIndexProperty(indexName, propertyMap);
    }

    @Test
    void getIndexList() throws IOException {
        indexService.getIndexList();
    }

    @Test
    void getIndexDetail() throws IOException {
        String indexName = "db_api_idx2";
        indexService.getIndexDetail(indexName);
    }

    @Test
    void indexExists() throws IOException {
        String indexName = "db_api_idx1";
        System.out.println(indexService.indexExists(indexName));
    }
}