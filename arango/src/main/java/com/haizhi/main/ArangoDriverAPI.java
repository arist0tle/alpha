package com.haizhi.main;

/* Created by Haiyang on 2017/6/19. */


import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDatabase;
import com.arangodb.ArangoEdgeCollection;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.GraphEntity;
import com.haizhi.utitl.ArangoUtitl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArangoDriverAPI {
    public static void main(String[] args) throws Exception {
        String databaseName = "nagle";
        ArangoUtitl arangoUtitl = new ArangoUtitl();
        arangoUtitl.init(databaseName);

        // create database test2
//        arangoUtitl.deleteDatabase(databaseName);
//        arangoUtitl.createDatabase(databaseName);

        // list user
//        arangoUtitl.listUser(arangoUtitl.arangoDB);

        // create table table_test
//        String tableName = "table_test";
//        arangoUtitl.createCollection(tableName);

        // 会删除 _graph这个隐藏表，最好是重新建库
//        arangoUtitl.deleteCollectionAll();

        // insert data
        String tableName = "vertex_tb_city";
//        arangoUtitl.insertCollection(tableName);
//        arangoUtitl.getDocument(tableName, "changsha");
        arangoUtitl.getDocumet(tableName);

    }
}
