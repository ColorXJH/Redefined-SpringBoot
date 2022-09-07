package com.example.springboot2es7.service;

import com.example.springboot2es7.entity.DocBean;
import com.example.springboot2es7.repository.ElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 10:38
 */
@Service("elasticService")
public class ElasticServiceImpl  implements IElasticService {
    //两种方式操作数据，
    //第一中是模板template方式，
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    //第二种是类似jpa-repository方式
    @Autowired
    private ElasticRepository elasticRepository;

    private Pageable pageable = PageRequest.of(0,10);

    @Override
    public void createIndex() {
        elasticsearchTemplate.indexOps(DocBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.delete(index, IndexCoordinates.of(index));
    }

    @Override
    public void save(DocBean docBean) {
        elasticRepository.save(docBean);
    }

    @Override
    public void saveAll(List<DocBean> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<DocBean> findAll() {
        return elasticRepository.findAll().iterator();
    }

    @Override
    public Page<DocBean> findByContent(String content) {
        return elasticRepository.findByContent(content,pageable);
    }

    @Override
    public Page<DocBean> findByFirstCode(String firstCode) {
        return elasticRepository.findByFirstCode(firstCode,pageable);
    }

    @Override
    public Page<DocBean> findBySecordCode(String secordCode) {
        return elasticRepository.findBySecondConde(secordCode,pageable);
    }

    @Override
    public Page<DocBean> query(String key) {
        return elasticRepository.findByContent(key,pageable);
    }
}
