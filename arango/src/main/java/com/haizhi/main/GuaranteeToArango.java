package com.haizhi.main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haizhi.utitl.ArangoHttpUtitl;
import com.haizhi.utitl.FileUtitl;
import com.haizhi.utitl.HttpUtil;
import com.haizhi.utitl.Utitl;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by gikieng on 17/8/18.
 */
@Slf4j
public class GuaranteeToArango {
    public static void main(String []args){
        try {
            //ArangoHttpUtitl.dropCollection("guarantee");
            //Thread.sleep(1000);
            //ArangoHttpUtitl.createCollection("guarantee", "edge");
            GuaranteeToArango.etl();
        }catch (Exception e){

        }
    }
    static String file_path = "data/EnterpriseGuaranteeSQL/";

    private static String url = "http://98.10.65.72:18530/_db/poc_data_lanzhou";
    static private JSONObject build_entity(String name){
        JSONObject jsonObject = new JSONObject();
        if (!Utitl.isCompanyName(name)){
            String entityKey = Utitl.getMD5("guarantee"+name);
            jsonObject.put("_key", entityKey);
            jsonObject.put("name", name);
            jsonObject.put("belong_to_lzbank", true);
        }else{
            String entityKey = Utitl.getMD5(name);
            jsonObject.put("_key", entityKey);
            jsonObject.put("name", name);
            jsonObject.put("belong_to_lzbank", true);
        }
        return jsonObject;
    }

    public static void etl() throws Exception {
        String payload = "";
        File dir = new File(file_path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().equals("_SUCCESS")) {
                continue;
            }
            log.info("||||||" + file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(new File(file.getAbsolutePath())));

            int count = 0;
            JSONArray personJsonArray = new JSONArray();
            JSONArray companyJsonArray = new JSONArray();
            JSONArray guaranteeJsonArray = new JSONArray();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = FileUtitl.extract(line).split(",");
                if (data.length < 6) {
                    continue;
                }
                String fromEntity = data[0].trim();
                String toEntity = data[1].trim();
                String fromEntityId = null;
                String toEntityId = null;
                JSONObject fromEntityJson = build_entity(fromEntity);
                JSONObject toEntityJson = build_entity(toEntity);
                if(Utitl.isCompanyName(fromEntity)){
                    companyJsonArray.add(fromEntityJson);
                    fromEntityId = "Company/"+fromEntityJson.getString("_key");
                }else{
                    personJsonArray.add(fromEntityJson);
                    fromEntityId = "Person/"+fromEntityJson.getString("_key");

                }
                if(Utitl.isCompanyName(toEntity)){
                    companyJsonArray.add(toEntityJson);
                    toEntityId = "Company/"+toEntityJson.getString("_key");
                }else{
                    personJsonArray.add(toEntityJson);
                    toEntityId = "Person/"+toEntityJson.getString("_key");
                }

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("_from", fromEntityId);
                jsonObject.put("_to", toEntityId);
                jsonObject.put("value", data[2]);
                jsonObject.put("begin_date", data[3]);
                jsonObject.put("end_date", data[4]);
                jsonObject.put("currency", data[5]);
                guaranteeJsonArray.add(jsonObject);
                count += 1;

                if(count %1000 == 0) {
                    payload = guaranteeJsonArray.toJSONString();
                    log.info("导入guarantee",ArangoHttpUtitl.bulk_import("guarantee", payload));
                    guaranteeJsonArray.clear();
                    if(personJsonArray.size() > 0){
                        payload = personJsonArray.toJSONString();
                        ArangoHttpUtitl.bulk_import("Person", payload);

                        guaranteeJsonArray.clear();
                    }
                    if(companyJsonArray.size() > 0){
                        payload = companyJsonArray.toJSONString();
                        ArangoHttpUtitl.bulk_import("Company", payload);
                        companyJsonArray.clear();
                    }
                }
            }
            if(guaranteeJsonArray.size() > 0) {
                payload = guaranteeJsonArray.toJSONString();
                ArangoHttpUtitl.bulk_import("guarantee", payload);
                guaranteeJsonArray.clear();
                if(personJsonArray.size() > 0){
                    payload = personJsonArray.toJSONString();
                    ArangoHttpUtitl.bulk_import("Person", payload);
                    guaranteeJsonArray.clear();
                }
                if(companyJsonArray.size() > 0){
                    payload = companyJsonArray.toJSONString();
                    ArangoHttpUtitl.bulk_import("Company", payload);
                    companyJsonArray.clear();
                }
            }

        }
    }
}
