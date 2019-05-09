package com.geektcp.alpha.db.es6.admin.impl;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.db.es6.admin.EsAdminDao;
import com.geektcp.alpha.db.es6.client.EsClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanghaiyang on 2019/5/7.
 */
@Slf4j
public class EsAdminDaoImpl implements EsAdminDao {


    @Autowired
    private EsClient esClient;

    private RestClient getLowClient(String index) {
        return esClient.getRestClient();
    }

    @Override
    public boolean existsIndex(String index) {
        RestClient client = this.getLowClient(index);
        try {
            Response response = client.performRequest("HEAD", index);
//            return response.getStatusLine().getReasonPhrase().equals(EsRequestStatus.SUCCESS.getValue());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsType(String index, String type) {
        return false;
    }

    @Override
    public boolean createIndex(String index){
        try {
            Map map = new HashMap<>(); // TODO: 2019/5/8 create index
            IndexRequest request = new IndexRequest(index).source(map);
            request.opType(DocWriteRequest.OpType.CREATE);
            String source = request.source().utf8ToString();
            HttpEntity entity = new NStringEntity(source, ContentType.APPLICATION_JSON);
            Response response =  esClient.getRestClient().performRequest("PUT", index, Collections.<String, String>emptyMap(),
                    entity);
            log.info("responce: {0}", JSON.toJSONString(response,true));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteIndex(String index) {
        return false;
    }

    @Override
    public boolean createType(String index, String type) {
        return false;
    }

    @Override
    public boolean delete(String index, String type) {
        return false;
    }

}
