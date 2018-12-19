package com.haizhi.main;

/* Created by Haiyang on 2017/6/19. */


import com.arangodb.entity.BaseDocument;
import com.haizhi.utitl.ArangoUtitl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Slf4j
public class BuildGraph2 {
    public static void main(String[] args) throws Exception {
        String databaseName = "nagle";
        ArangoUtitl arangoUtitl = new ArangoUtitl();

        // create database test2
        arangoUtitl.deleteDatabase(databaseName);
        arangoUtitl.createDatabase(databaseName);
        arangoUtitl.init(databaseName);

        // list user
//        arangoUtitl.listUser(arangoUtitl.arangoDB);

        // create table table_test
//        String tableName = "table_test";
//        arangoUtitl.createCollection(tableName);

        // 会删除 _graph这个隐藏表，最好是重新建库
//        arangoUtitl.deleteCollectionAll();

        // insert data
//        arangoUtitl.insertCollection(tableName);
//        arangoUtitl.getDocument("10802299");

        // ------------------ 图构建 -------------------
        // 第一步，开始创建图对象
        String graphName = "test_graph";
        {
            arangoUtitl.createGraph(graphName);
        }

        {
            // 第二步，创建顶点表，这里定点表不会和图进行关联，也不需要和图进行关联
            String vertextCollectionName = "vertex_tb_name";
            arangoUtitl.createVertexCollection(vertextCollectionName);
            Collection<BaseDocument> data = new ArrayList<>();
            String[] key_str = {"bob", "charlie", "dave", "eve", "alice"};
            String[] value_str = {"Bob", "Charlie", "Dave", "Eve", "Alice"};
            for (int i = 0; i < value_str.length; i++) {
                BaseDocument baseDocument = new BaseDocument();
                baseDocument.setKey(key_str[i]);
                baseDocument.addAttribute("name", value_str[i]);
                data.add(baseDocument);
            }
            arangoUtitl.importDocumentsUDF(vertextCollectionName, data);

            String vertextCollectionName2 = "vertex_tb_city";
            arangoUtitl.createVertexCollection(vertextCollectionName2);
            Collection<BaseDocument> data2 = new ArrayList<>();
            String[] key_str2 = {"shenzhen", "changsha", "guangzhou", "nanchang", "shanghai"};
            String[] value_str2 = {"Shenzhen", "Changsha", "Guangzhou", "Nanchang", "Shanghai"};
            for (int i = 0; i < value_str.length; i++) {
                BaseDocument baseDocument = new BaseDocument();
                baseDocument.setKey(key_str2[i]);
                baseDocument.addAttribute("name", value_str2[i]);
                data2.add(baseDocument);
            }

            arangoUtitl.importDocumentsUDF(vertextCollectionName2, data2);

            // 单独添加顶点表不会报错，但是添加了顶点表再添加边表就会报错，目前还不清楚具体怎么操作
//            arangoUtitl.arangoGraph.addVertexCollection(vertextCollectionName);
//            arangoUtitl.arangoGraph.addVertexCollection(vertextCollectionName2);
        }


        {
            // 第三步，创建边表，
            // 边表的数据来源于顶点表，这里代码简单写死了
            String ecn = "test_edge";
            arangoUtitl.createEdgeCollection(graphName, ecn);

            // 在边表里面插入 起始顶点的数据，一条边对应两个顶点，即一条数据库记录
            arangoUtitl.insertEdgeRandom(ecn);
            arangoUtitl.insertEdgeRandom(ecn);
            arangoUtitl.insertEdgeRandom(ecn);
            arangoUtitl.insertEdgeRandom(ecn);

            String ecn2 = "test_edge2";
            arangoUtitl.createEdgeCollection(graphName, ecn2);
            arangoUtitl.insertEdge(ecn2, "vertex_tb_city/changsha", "vertex_tb_city/guangzhou");
            arangoUtitl.insertEdge(ecn2, "vertex_tb_city/nanchang", "vertex_tb_city/shanghai");
            arangoUtitl.insertEdge(ecn2, "vertex_tb_city/shanghai", "vertex_tb_city/shenzhen");

            String ecn3 = "test_edge3";
            arangoUtitl.createEdgeCollection(graphName, ecn3);
            arangoUtitl.insertEdge(ecn2, "vertex_tb_city/nanchang", "vertex_tb_name/alice");
            arangoUtitl.insertEdge(ecn2, "vertex_tb_city/shenzhen", "vertex_tb_name/dave");
            arangoUtitl.insertEdge(ecn2, "vertex_tb_city/guangzhou", "vertex_tb_city/shenzhen");
        }

        {
            // 第四步，关联边表和图
            // 图的数据来源于边，两个顶点构成一条边，多条边构成一个图
            // 所以要构建图的话，只需要添加边表即可,没有addEdgeDefinition操作，不会出现图
            // 类似于没有正确的两个顶点，不会出现边，边和图其实是自动出现的，只需要对应的数据正确
            arangoUtitl.addEdgeDefinition("test_edge", "vertex_tb_name", "vertex_tb_name");
            arangoUtitl.addEdgeDefinition("test_edge2", "vertex_tb_city", "vertex_tb_city");
            arangoUtitl.addEdgeDefinition("test_edge3", "vertex_tb_city", "vertex_tb_name");
        }
    }
}
