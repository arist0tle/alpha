package com.geektcp.alpha.db.es6.dao;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.db.es6.bean.StoreURL;
import com.geektcp.alpha.db.es6.model.EsQuery;
import com.geektcp.alpha.db.es6.model.EsQueryResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tanghaiyang on 2019/6/6.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "")
public class EsSearchDaoTest {

    @Autowired
    private EsSearchDao esSearchDao;

    private StoreURL storeURL;
    private static final String index = "es6_test16";
    private static final String type = "company";

    @Before
    public void init(){
        storeURL = new StoreURL();
        storeURL.setUrl("192.168.1.101:9200");
    }


    @Test
    public void searchByIds(){
        EsQuery esQuery = new EsQuery();
        EsQueryResult esQueryResult = esSearchDao.searchByIds(storeURL, esQuery);
        log.info("esQueryResult:\n{}", JSON.toJSONString(esQueryResult,true));
    }

}
