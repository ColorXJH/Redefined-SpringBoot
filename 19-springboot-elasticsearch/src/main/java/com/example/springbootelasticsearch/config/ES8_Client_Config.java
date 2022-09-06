package com.example.springbootelasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/6 16:25
 */
@ConfigurationProperties(prefix = "elasticsearch")
@Configuration
public class ES8_Client_Config {
    @Setter
    private String hosts;

    /**
     * 同步方式
     */
    @Bean
    public ElasticsearchClient elasticsearchClient(){
        HttpHost[] httpHosts=toHttpHost();
        // Create the RestClient
        RestClient restClient = RestClient.builder(httpHosts).build();
        // Create the transport with a Jackson mapper
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        // create the API client
        return new ElasticsearchClient(transport);
    }


    /**
     * 异步方式
     */
    @Bean
    public ElasticsearchAsyncClient elasticsearchAsyncClient() {
        HttpHost[] httpHosts = toHttpHost();
        RestClient restClient = RestClient.builder(httpHosts).build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchAsyncClient(transport);
    }

    /**
     * 解析配置的字符串hosts，转为HttpHost对象数组
     */
    private HttpHost[] toHttpHost(){
        if(!StringUtils.hasLength(hosts)){
            throw new RuntimeException("ColorXJH Say: invalid elasticsearch configuration. elasticsearch.hosts不能为空！");
        }
        //多个ip逗号隔开
        String[] hostArray=hosts.split(",");
        HttpHost[] httpHosts=new HttpHost[hostArray.length];
        HttpHost httpHost;
        for(int i = 0; i < hostArray.length; i++){
            String[] strings = hostArray[i].split(":");
            httpHost = new HttpHost(strings[0], Integer.parseInt(strings[1]), "http");
            httpHosts[i] = httpHost;
        }
        return httpHosts;
    }
}
