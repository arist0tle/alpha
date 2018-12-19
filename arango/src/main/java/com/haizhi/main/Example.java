package com.haizhi.main;

/* Created by Haiyang on 2017/6/21. */


import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.entity.EdgeDefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class Example {
    public static void main(String[] args) throws Exception{
        ArangoDB arangoDB = null;
        ArangoDB.Builder builder = new ArangoDB.Builder();
        try {
            FileInputStream in = new FileInputStream("conf/common.properties");
            arangoDB = builder.loadProperties(in).build();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // create database
        try {
            arangoDB.db("myDatabase").drop();
            arangoDB.createDatabase("myDatabase");
        }catch (ArangoDBException ao) {
            if (ao.getErrorNum() == 1207) {
            } else {
                ao.printStackTrace();
            }
        }


        // 创建一个图, 然后就自动生成了3个东西, 一个document表即顶点表, 一个edge表即边表, 一个图
        // 注意在createGraph方法之前，即便构造了edgeDefinitions对象，也只是在内存中，
        // 只有createGraph以后才立即并且同时创建了3个东西
        Set<EdgeDefinition> edgeDefinitions = Collections.singleton(
                new EdgeDefinition()
                        .collection("myEdgeCollection")
                        .from("myVertexCollection")
                        .to("myVertexCollection"));
        arangoDB.db("myDatabase").createGraph("myGraph", edgeDefinitions);

        // 创建定点, 这是在图界面可以看到两个框, 但是没有先连接起来
        BaseDocument from = new BaseDocument("myFromKey");
        from.addAttribute("name", "thy_from");
        arangoDB.db("myDatabase")
                .graph("myGraph")
                .vertexCollection("myVertexCollection")
                .insertVertex(from);

        // create to vertex
        BaseDocument to = new BaseDocument("myToKey");
        to.addAttribute("name", "thy_to");
        arangoDB.db("myDatabase")
                .graph("myGraph")
                .vertexCollection("myVertexCollection")
                .insertVertex(to);

        {
            // 创建一条边,效果是在web界面生产一条带箭头的线
            BaseEdgeDocument edge = new BaseEdgeDocument(
                    "myVertexCollection/myFromKey",
                    "myVertexCollection/myToKey"
            );

            edge.addAttribute("label", "value");
            edge.addAttribute("whatever", 42);
            arangoDB.db("myDatabase").graph("myGraph").edgeCollection("myEdgeCollection").insertEdge(edge);

//        Map<String, Object> edge = new HashMap<String, Object>();
//        edge.put("_from", "myVertexCollection/myFromKey");
//        edge.put("_to", "myVertexCollection/myToKey");
//        edge.put("label", "value");
//        edge.put("whatever", 42);
//        arangoDB.db("myDatabase").graph("myGraph").edgeCollection("myEdgeCollection").insertEdge(edge);
        }

    }
}
