package com.geektcp.alpha.db.es6.client;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.db.es6.bean.StoreURL;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanghaiyang on 2019/5/9.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "")
public class EsIndexDaoTest {


    @Autowired
    private EsIndexDao esIndexDao;

    private StoreURL storeURL;
    private static final String index = "es6_test16";
    private static final String type = "company";

    @Before
    public void init(){
        storeURL = new StoreURL();
//        storeURL.setUrl("192.168.1.101:9200");
//        storeURL.setSecurityEnabled(false);

        storeURL.setUrl("192.168.1.223:24148,192.168.1.224:24148,192.168.1.225:24148");
        storeURL.getFilePath().put("krb5.conf", "/Users/haizhi/git/gp/graph/graph-server/graph-server-es6/src/test/resources/fi/krb5.conf");
        storeURL.getFilePath().put("jaas.conf", "/Users/haizhi/git/gp/graph/graph-server/graph-server-es6/src/test/resources/fi/jaas.conf");
        storeURL.getFilePath().put("user.keytab", "/Users/haizhi/git/gp/graph/graph-server/graph-server-es6/src/test/resources/fi/user.keytab");
        storeURL.setSecurityEnabled(true);
    }

    @Test
    public void testConnect(){
        esIndexDao.testConnect(storeURL);
    }

    @Test
    public void existsIndex(){
        boolean ret = esIndexDao.existsIndex(storeURL, index);
        log.info("existsIndex: {0}", ret);
    }

    @Test
    public void existsType(){
        boolean ret = esIndexDao.existsType(storeURL, index,type);
        log.info("existsType: {0}", ret);
    }

    @Test
    public void createIndex(){
        boolean ret = esIndexDao.createIndex(storeURL,index);
        log.info("createIndex: {0}", ret);
    }

    @Test
    public void deleteIndex(){
        boolean ret = esIndexDao.deleteIndex(storeURL,index);
        log.info("deleteIndex: {0}", ret);
    }

    @Test
    public void createType(){
        boolean ret = esIndexDao.createType(storeURL,index,type);
        log.info("createType: {0}", ret);
    }

    @Test
    public void bulkUpsert(){
        List<Source> sourceList = new ArrayList<>();
        Source source = new Source();
        source.setId("200");
        Map<String,Object> map = new HashMap<>();
        map.put("name","sdsdf");
        map.put("reg_address", "shenzhen");
        source.setSource(map);
        sourceList.add(source);
        LOG.info("sourceList: {0}", JSON.toJSONString(sourceList,true));
        CudResponse ret = esIndexDao.bulkUpsert(storeURL,index + "." + type, type,sourceList);
        LOG.info("bulkUpsert:\n{0}", JSON.toJSONString(ret,true));
    }

    /*
    * do not support
    * */
//    @Test
//    public void bulkScriptUpsert(){
//        List<ScriptSource> sources = new ArrayList<>();
//        ScriptSource scriptSource = new ScriptSource();
//
//        List<Field> fieldList = new ArrayList<>();
//        sources.add(scriptSource);
//        scriptSource.setNormalFields(fieldList);
//        scriptSource.setId("200");
//
//        Field field = new Field();
//        field.setName("name");
//        field.setValue("nagle");
//        field.setUpdateMode(UpdateMode.DECREMENT);
//        fieldList.add(field);
//
//        CudResponse ret = esIndexDao.bulkScriptUpsert(storeURL,index,type,sources);
//        LOG.info("bulkScriptUpsert:\n{0}", JSON.toJSONString(ret,true));
//    }

    @Test
    public void delete(){
        List<Source> sourceList = new ArrayList<>();
        Source source = new Source();
        source.setId("100");
        Map<String,Object> map = new HashMap<>();
        map.put("name","sdsdf");
        map.put("age", 13);
        source.setSource(map);
        sourceList.add(source);
        CudResponse ret =  esIndexDao.delete(storeURL, index, type, sourceList);
        LOG.info("delete:\n{0}", JSON.toJSONString(ret,true));
    }

}
