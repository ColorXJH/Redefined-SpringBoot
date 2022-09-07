package com.example.springboot2es7.service;

import com.example.springboot2es7.entity.DocBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 10:37
 */
public interface IElasticService {
    void createIndex();

    void deleteIndex(String index);

    void save(DocBean docBean);

    void saveAll(List<DocBean> list);

    Iterator<DocBean> findAll();

    Page<DocBean> findByContent(String content);

    Page<DocBean> findByFirstCode(String firstCode);

    Page<DocBean> findBySecordCode(String secordCode);

    Page<DocBean> query(String key);
}
