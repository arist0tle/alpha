package com.haizhi.main;

/* Created by Haiyang on 2017/8/7. */

import com.haizhi.utitl.ArangoHttpUtitl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuildEdge {
    public static void main(String[] args) throws Exception{
        log.info("start BuildEdge!!!");
        ArangoHttpUtitl atu = new ArangoHttpUtitl();

        // 创建 担保数据表，并导入数据
//        String tableName = "guarantee";
//        atu.dropCollection(tableName);
//        atu.createCollection(tableName, "edge");
//        atu.cleanCollection(tableName);
//        atu.insertMultiEdgeEnterpriseGuarantee(tableName);

        String tableName = "money_flow";
        atu.dropCollection(tableName);
        atu.createCollection(tableName, "edge");
        atu.cleanCollection(tableName);
        atu.insertMultiEdgeEnterpriseTransfer(tableName);

        // 创建公司表并导入数据
//        String tableName = "Company";
//        atu.dropCollection(tableName);
//        atu.createCollection(tableName, "vetex");
//        atu.cleanCollection(tableName);
//        atu.insertMultiDocumentSingle(tableName);

//        atu.queryAllKeys(tableName);
//        { "query" : "FOR u IN users LIMIT 2 RETURN u", "count" : true, "batchSize" : 2 }
//        String aql = "FOR u IN guarantee FILTER u.value=='150000' RETURN u._id";
//        atu.aql_query_id("陕西蓬勃发展工贸有限公司");


    }


}
