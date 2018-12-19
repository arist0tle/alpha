package com.haizhi.utitl;

/* Created by Haiyang on 2017/6/6. */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haizhi.utitl.HttpUtil;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArangoHttpUtitl {
    private static String username = "root";
    private static String password = "123456";

//    private static String url = "http://43.247.69.127:18530";
    private static String url = "http://98.10.65.72:18530/_db/poc_data_lanzhou";
//    private static String url = "http://98.11.57.66:18530/_db/poc_data_lanzhou";

    private static String uri = "";

//    public static void main(String[] args) throws Exception {
////        arangoPost();
////        arangoGet();
//        String tableName = "testCollectionBasics";
//        createCollection(tableName, "vetex");
//        cleanCollection(tableName);
//        insertMultiDocument(tableName);
//
//    }


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
        String uri = "/_api/document/testCollectionBasics/6102170";
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
        uri = "/_api/document/users/8830099";
        uri = "/_api/collection/users/properties";
        uri = "/_api/document/users/8830099";

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

    public static void cleanCollection(String collectionName) throws Exception{
        uri = "/_api/collection/" + collectionName + "/truncate";
        uri = "/_api/collection/testCollectionBasics/truncate";
        log.info("url + uri: " + url + uri);
        String result = HttpUtil.restfulPut(url + uri);

        log.info("result: " + result);
    }

    public static void insertMultiDocument(String tableName) throws Exception {
        String uri = "/_api/document/" + tableName;
        String payload = "";
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

    public static void insertMultiDocumentSingle(String tableName) throws Exception {
        String uri = "/_api/document/" + tableName;
        String payload = "";
        String file_path = "data/Company.txt";
        List<String> arrayList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(file_path)));
        String line = "";
        while ((line = br.readLine()) != null) {
            arrayList.add(line);
            payload += line;
        }

        log.info("url + uri: " + url + uri);
        String result = HttpUtil.restfulPost(
                url + uri,
                payload
        );

        log.info("result: " + result);
    }

    public static void insertMultiEdgeEnterpriseGuarantee(String tableName) throws Exception {
        String payload = "";
        String uri = "/_api/document/" + tableName;
        String file_path = "data/EnterpriseGuaranteeSQL/";

        File dir = new File(file_path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().equals("_SUCCESS")) {
                continue;
            }
            log.info("||||||" + file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(new File(file.getAbsolutePath())));

            int count = 0;
            JSONArray jsonArray = new JSONArray();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = FileUtitl.extract(line).split(",");
                JSONObject jsonObject = new JSONObject();
                if (data.length < 6 || data[0].length()<=3 || data[1].length() <=3 ) {
                    continue;
                }
                String from = ArangoHttpUtitl.aql_query_id(data[0]);
                if (from.length() <= 0) {
                    log.info("from.length is 0,没有顶点: " + data[0]);
                    continue;
                }
                String to = ArangoHttpUtitl.aql_query_id(data[1]);

                if (to.length() <= 0) {
                    log.info("to.length is 0,没有终点： " + data[1]);
                    continue;
                }
                jsonObject.put("_from", from);
                jsonObject.put("_to", to);
                jsonObject.put("value", data[2]);
                jsonObject.put("begin_date", data[3]);
                jsonObject.put("end_date", data[4]);
                jsonObject.put("currency", data[5]);

                count += 1;
                jsonArray.add(jsonObject);
                if(count %1000 == 0) {
                    payload = jsonArray.toJSONString();
                    String result = HttpUtil.restfulPost(
                            url + uri,
                            payload
                    );
                    log.info("--------------插入arangodb成功: "+ count);
                    jsonArray.clear();
                }
            }
            if(jsonArray.size() > 0) {
                payload = jsonArray.toJSONString();
                String result = HttpUtil.restfulPost(
                        url + uri,
                        payload
                );
                log.info("--------------插入arangodb成功: "+ count);
                jsonArray.clear();
            }

        }
    }

    public static void insertMultiEdgeEnterpriseGuarantee2(String tableName) throws Exception {
        String payload = "";
        String uri = "/_api/document/" + tableName;
        String file_path = "data/EnterpriseGuaranteeSQL/";

        File dir = new File(file_path);
        File[] files = dir.listFiles();
        log.info("files count: " + files.length);
        for (File file : files) {
            if (file.getName().equals("_SUCCESS")) {
                continue;
            }
            log.info("||||||||||||||||||||||||" + file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(new File(file.getAbsolutePath())));

//            List<JSONObject> arrayList = new ArrayList<>();

            String line = "";
            JSONArray jsonArray = new JSONArray();
            int count = 0;
            while ((line = br.readLine()) != null) {

                String[] data = FileUtitl.extract(line).split(",");
                JSONObject jsonObject = new JSONObject();

                if(data.length < 6) {
                    log.info("line: " + line);
                    continue;
                }
                jsonObject.put("_from", "Company/" + Utitl.getMD5(data[0]));
                jsonObject.put("_to", "Company/" + Utitl.getMD5(data[1]));
                jsonObject.put("value", data[2]);
                jsonObject.put("begin_date", data[3]);
                jsonObject.put("end_date", data[4]);
                jsonObject.put("currency", data[5]);

                count += 1;
                jsonArray.add(jsonObject);
                if(count %1000 == 0) {
                    payload = jsonArray.toJSONString();
                    String result = HttpUtil.restfulPost(
                            url + uri,
                            payload
                    );
                    log.info("--------------插入arangodb成功: "+ count);
                    jsonArray.clear();
                }
            }
            if(jsonArray.size() > 0) {
                payload = jsonArray.toJSONString();
                String result = HttpUtil.restfulPost(
                        url + uri,
                        payload
                );
                log.info("--------------插入arangodb成功: "+ count);
                jsonArray.clear();
            }
        }
    }

    public static void insertMultiEdgeEnterpriseTransfer(String tableName) throws Exception {
        String payload = "";
        String uri = "/_api/document/" + tableName;
        String file_path = "data/EnterpriseTransferSQL/";

        File dir = new File(file_path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().equals("_SUCCESS")) {
                continue;
            }
            log.info(file.getAbsolutePath());
            List<JSONObject> arrayList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(new File(file.getAbsolutePath())));

            int count = 0;
            JSONArray jsonArray = new JSONArray();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = FileUtitl.extract(line).split(",");
                JSONObject jsonObject = new JSONObject();
                if (data.length < 6
                        || data[0].length() <= 3
                        || data[1].length() <= 3
                        || data[3].equals("null"))
                { continue; }

                String from = ArangoHttpUtitl.aql_query_id(data[1]);
                String to = ArangoHttpUtitl.aql_query_id(data[3]);
                if (from.length() <= 0) {
                    log.info("from.length is 0,没有起点表: " + data[1]);
                    continue;
                } else if (to.length() <= 0) {
                    log.info("to.length is 0,没有终点表: " + data[3]);
                    continue;
                }
                jsonObject.put("_from", ArangoHttpUtitl.aql_query_id(data[1]));
                jsonObject.put("_to", ArangoHttpUtitl.aql_query_id(data[3]));
                jsonObject.put("value", data[4]);
                jsonObject.put("date", data[5]);

//                arrayList.add(jsonObject);
//
//                JSONArray jsonArray = new JSONArray();
//                for (int i = 0; i < arrayList.size(); i++) {
//                    jsonArray.add(arrayList.get(i));
//                }
//
//                payload = jsonArray.toJSONString();
//                log.info("payload: " + payload);
//                log.info("url + uri: " + url + uri);
//                String result = HttpUtil.restfulPost(
//                        url + uri,
//                        payload
//                );
//
//                log.info("插入完成result: " + result);
//            }
//        }
                count += 1;
                jsonArray.add(jsonObject);
                if (count % 1000 == 0) {
                    payload = jsonArray.toJSONString();
                    String result = HttpUtil.restfulPost(
                            url + uri,
                            payload
                    );
                    log.info("--------------插入arangodb成功: " + count);
                    jsonArray.clear();
                }
            }
            if (jsonArray.size() > 0) {
                payload = jsonArray.toJSONString();
                String result = HttpUtil.restfulPost(
                        url + uri,
                        payload
                );
                log.info("--------------插入arangodb成功: " + count);
                jsonArray.clear();
            }
        }
    }

    public static void createCollection(String tableName, String tableType) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String uri = "/_api/collection";
        jsonObject.put("name", tableName);

        log.info("tableType: " + tableType);

        switch (tableType){
            // type为2表示表类型为document，即定点表
            // type为2表示表类型为vetex，即边表
            case "vetex":
                jsonObject.put("type", "2");
                break;
            case "edge":
                jsonObject.put("type", "3");
                break;
            default:
                jsonObject.put("type", "3");
                break;
        }

        String result = HttpUtil.restfulPost(
                url + uri,
                jsonObject.toJSONString()
        );

    }


    public static void dropCollection(String tableName) throws Exception{
        String uri = "/_api/collection/" + tableName;
        log.info("start dropCollection: " + url + uri);
        String result = HttpUtil.restfulDelete(url + uri);
    }

    public static void queryAllKeys(String tableName) throws Exception{
        JSONObject jsonObject = new JSONObject();

        // 查询表所有数据
        // http://10.10.10.171:18530/_db/nagle/_api/simple/all-keys
        String uri = "/_api/simple/all-keys";
        jsonObject.put("collection", tableName);
        jsonObject.put("type", "key");
        String result = HttpUtil.restfulPut(
                url + uri,
                jsonObject.toJSONString()
        );

    }

    public static void aql_query(String sql) throws Exception{
        // FOR u IN Company FILTER u.name=='甘肃国美物流有限公司' RETURN u._key
        String uri = "/_api/cursor";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("query", sql);
        String result = HttpUtil.restfulPost( url + uri, jsonObject.toJSONString());

        log.info("result: " + result);
    }

    public static String aql_query_id(String company) throws Exception{
        // FOR u IN Company FILTER u.name=='甘肃国美物流有限公司' RETURN u._key
        String aql = "FOR u IN Company FILTER u.name=='" + company + "' RETURN u._id";
        String uri = "/_api/cursor";

        if(company.equals("null")) { return ""; }
        JSONObject jsonObject = new JSONObject();

//        log.info("aql: " + aql);
        jsonObject.put("query", aql);
        String result = HttpUtil.restfulPost( url + uri, jsonObject.toJSONString());

//        log.info("restfulPost result: " + result);
        JSONObject retJsonObject = JSONObject.parseObject(result);
        JSONArray retJSONArray = (JSONArray)retJsonObject.get("result");

//        log.info("retJSONArray: " + retJSONArray.toJSONString());

        String ret = "";
        if( retJSONArray.size() > 0 ) {
            ret = retJSONArray.get(0).toString();
//            log.info("aql_query_id查询结果: " + ret);
        }else {
//            log.info("aql_query_id查不到key: " + ret);
        }

        return ret;
    }
    public static String aql_query_id_by_name(String collection, String name) throws Exception{
        if(collection == null || collection.equals("") || name == null || name.equals("")){
            throw new Exception("输入非法！");
        }
        String aql = String.format("FOR u in %s Filter u.name == %s return u._id", collection, name);
        String uri = "/api/cursor";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("query", aql);
        String result = HttpUtil.restfulPost( url + uri, jsonObject.toJSONString());

        JSONObject retJsonObject = JSONObject.parseObject(result);
        JSONArray retJSONArray = (JSONArray)retJsonObject.get("result");


        String ret = "";
        if( retJSONArray.size() > 0 ) {
            ret = retJSONArray.get(0).toString();
        }
        return ret;
    }
    public static String bulk_import(String collection, String data) throws Exception{
        String result = HttpUtil.restfulPost(
                url + "/_api/document/"+collection,
                    data
        );
        return result;
    }
}
