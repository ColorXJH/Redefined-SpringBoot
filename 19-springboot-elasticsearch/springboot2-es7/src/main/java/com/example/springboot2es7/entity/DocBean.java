package com.example.springboot2es7.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 10:19
 */
@Data
@Document(indexName = "color")
public class DocBean {
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String firstCode;
    @Field(type = FieldType.Keyword)
    private String secondCode;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Integer)
    private Integer type;

    public DocBean(Long id, String firstCode, String secondCode, String content, Integer type) {
        this.id = id;
        this.firstCode = firstCode;
        this.secondCode = secondCode;
        this.content = content;
        this.type = type;
    }
}
