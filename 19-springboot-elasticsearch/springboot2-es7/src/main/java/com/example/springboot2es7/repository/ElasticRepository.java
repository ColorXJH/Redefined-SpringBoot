package com.example.springboot2es7.repository;

import com.example.springboot2es7.entity.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 10:29
 */
public interface ElasticRepository extends ElasticsearchRepository<DocBean,Long> {
    //默认的注释
    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\": \"?\" }}} }")
    Page<DocBean>findByContent(String content, Pageable pageable);
    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"firstCode.keyword\" : \"?\"}}}}")
    Page<DocBean>findByFirstCode(String firstCode,Pageable pageable);
    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"secordCode.keyword\" : \"?\"}}}}")
    Page<DocBean>findBySecondConde(String secondCode,Pageable pageable);
}
