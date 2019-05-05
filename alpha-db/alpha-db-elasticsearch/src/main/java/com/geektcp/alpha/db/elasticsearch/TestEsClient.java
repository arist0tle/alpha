package com.geektcp.alpha.db.elasticsearch;

import com.geektcp.alpha.db.elasticsearch.rest.client.EsRestClient;
import org.elasticsearch.client.RestClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by tanghaiyang on 2019/5/5.
 */
@SpringBootApplication
@EnableCaching
@ServletComponentScan
@EnableScheduling
@EnableJpaAuditing
@EnableJpaRepositories("com.geektcp.alpha")
@EntityScan("com.geektcp.alpha")
@ComponentScan("com.geektcp.alpha")
public class TestEsClient {

    @Autowired
    EsRestClient esRestClient;

    @Test
    public void example(){
        RestClient restClient = esRestClient.getClient("192.168.1.50,192.168.1.51,192.168.1.52:9200");
        RestClient restClient2 = esRestClient.getClient("192.168.1.50,192.168.1.51,192.168.1.52:9200");
        Assert.assertNotNull(restClient);
        Assert.assertNotNull(restClient2);
        assert restClient == restClient2;

        restClient = esRestClient.getClient("elasticsearch://192.168.1.50,192.168.1.51,192.168.1.52:9300");
        Assert.assertNotNull(restClient);

        restClient = esRestClient.getClient("elasticsearch:http//192.168.1.50,192.168.1.51,192.168.1.52:9200");
        Assert.assertNotNull(restClient);
    }





}
