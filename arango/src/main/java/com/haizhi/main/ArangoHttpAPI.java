package com.haizhi.main;

/* Created by Haiyang on 2017/6/6. */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haizhi.utitl.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArangoHttpAPI {
    private static String username = "root";
    private static String password = "123456";

    private static String url = "http://43.247.69.127:18530";
    private static String uri = "";

    public static void main(String[] args) throws Exception {
//        arangoPost();
//        arangoGet();
        cleanCollections("testCollectionBasics");
        insertMultiDocument();

    }


    public static void arangoPost() throws Exception{
        // post方法可以创建用户
//        String uri = "/_api/user";
//        String json_post = "{\n" +
//                "\"user\" : \"admin@example\",\n" +
//                "\"passwd\" : \"secure\"\n" +
//                "}";

//        String uri = "_api/gharial/graph1/vertex/male";
//        String json_post = "{\"name2\" : \"Francis2\"\n}";

//        JSONObject jsonObject = new JSONObject();


        // 查询表所有数据
//        http://10.10.10.171:18530/_db/nagle/_api/simple/all-keys
//        String uri = "/_db/nagle/_api/simple/all-keys";
//        jsonObject.put("collection", "vertex_tb_city");
//        jsonObject.put("type", "key");
//        String result = HttpUtil.restfulPut(
//                url + uri,
//                jsonObject.toJSONString()
//        );

        // 创建表
//        String uri = "/_db/nagle/_api/collection";
//        jsonObject.put("name", "testCollectionBasics");
//        String result = HttpUtil.restfulPatch(
//                url + uri,
//                jsonObject.toJSONString()
//        );

        // 插入数据
//        String uri = "/_db/nagle/_api/document/testCollectionBasics";
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("Hello11", "World11---111");
//
//        JSONObject jsonObject2 = new JSONObject();
//        jsonObject2.put("Hello22", "World22----222");
//
//        jsonArray.add(jsonObject1);
//        jsonArray.add(jsonObject2);
//
//        log.info("url + uri: " + url + uri);
//        log.info(" jsonArray: " +  jsonArray.toJSONString());
//        String result = HttpUtil.restfulPost(
//                url + uri,
//                jsonArray.toJSONString()
//        );


        // 更新数据
        String uri = "/_db/nagle/_api/document/testCollectionBasics/6102170";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("Hello11", "World11---111");

        log.info("url + uri: " + url + uri);
        log.info(" jsonArray: " +  jsonArray.toJSONString());
        String result = HttpUtil.restfulPatch(
                url + uri,
                jsonObject1.toJSONString()
        );

    }


    public static void arangoGet() throws Exception{
        // 查询单个文档
        uri = "/_db/test_db/_api/document/users/8830099";
        uri = "/_db/test_db/_api/collection/users/properties";
        uri = "/_db/test_db/_api/document/users/8830099";

        // 需要root账号
        uri = "/_api/version?details=true";

        // 图信息
        uri = "/_api/gharial";

        // 表的元数据信息
        uri = "/_api/replication/inventory";

        // 检查集群状态
        uri = "/_admin/cluster-test";

        // 查看日志
        uri = "/_admin/log";

        // 获取日志级别
        uri = "/_admin/log/level";

        // 获取统计信息
        uri = "/_admin/statistics";
        uri = "/_admin/statistics-description";

        uri = "/_api/endpoint";

        // get方法查看用户
        uri = "/_api/user";

        // 查看arango集群任务
        uri = "/_api/tasks";

        // 查看 write ahead log 信息
        uri = "/_admin/wal/properties";

        String username = "root";
        String password = "123456";
        String result = HttpUtil.restfulGet(url + uri, username, password);
        System.out.println(result);

    }

    public static void cleanCollections(String collectionName) throws Exception{
        uri = "/_db/nagle/_api/collection/" + collectionName + "/truncate";
        uri = "/_db/nagle/_api/collection/testCollectionBasics/truncate";
        log.info("url + uri: " + url + uri);
        String result = HttpUtil.restfulPut(url + uri);

        log.info("result: " + result);
    }

    public static void insertMultiDocument() throws Exception {
        String uri = "/_db/nagle/_api/document/testCollectionBasics";
        String payload = "[";
        String file_path = "data/inner.txt";
        List<String> arrayList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(file_path)));
        String line = "";
        while ((line = br.readLine()) != null) { arrayList.add(line); }

        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i<arrayList.size(); i++){
            JSONObject jsonObject = JSONObject.parseObject(arrayList.get(i));
            jsonArray.add(jsonObject);
        }

        payload = jsonArray.toJSONString();
        log.info("url + uri: " + url + uri);
        String result = HttpUtil.restfulPost(
                url + uri,
                payload
        );

        log.info("result: " + result);
    }

    public static void createCollections() throws Exception{
        JSONObject jsonObject = new JSONObject();
        String uri = "/_db/nagle/_api/collection";
        jsonObject.put("name", "testCollectionBasics");
        String result = HttpUtil.restfulPatch(
                url + uri,
                jsonObject.toJSONString()
        );

    }
}
