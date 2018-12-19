package com.haizhi.utitl;

/* Created by Haiyang on 2017/6/19. */

import com.arangodb.*;
import com.arangodb.entity.*;
import com.arangodb.model.CollectionCreateOptions;
import com.haizhi.arangodb.example.graph.Circle;
import com.haizhi.arangodb.example.graph.CircleEdge;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@Slf4j
public class ArangoUtitl {
    public static ArangoDB arangoDB;
    public static ArangoDatabase arangoDatabase;
    public static ArangoGraph arangoGraph;

    public static ArangoVertexCollection arangoVertexCollection;

//    public static ArangoCollection vertexCollection;
//    public static ArangoCollection edgeCollection;

    public static CollectionEntity vertexCollectionEntity;

//    public static VertexEntity vertexEntity;

    ////////////////////////////////////////////////////
//    public static ArangoEdgeCollection arangoEdgeCollection;

    public static CollectionEntity edgeCollectionEntity;

//    public static EdgeEntity edgeEntity;

//    public static ArangoCollection arangoCollection;

    public ArangoUtitl(){
        build();
    }

    public static void init(String databaseName){
        arangoDatabase = arangoDB.db(databaseName);
    }

    public static void build() {
        ArangoDB.Builder builder = new ArangoDB.Builder();
        try {
            FileInputStream in = new FileInputStream("conf/common.properties");
            arangoDB = builder.loadProperties(in).build();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void addGraph(String graphName,
                                String edgeCollectionName,
                                String vertextCollectionName){
        EdgeDefinition edgeDefinition = new EdgeDefinition()
                .collection(edgeCollectionName)
                .from(vertextCollectionName).to(vertextCollectionName);

        ArangoGraph graph = arangoDatabase.graph(graphName);
        graph.addEdgeDefinition(edgeDefinition);
        graph.addVertexCollection(vertextCollectionName);

    }

    public static void addExampleElements() throws ArangoDBException {
        // Add circle circles
        final VertexEntity vA = createVertex(new Circle("A", "1"));
        final VertexEntity vB = createVertex(new Circle("B", "2"));
        final VertexEntity vC = createVertex(new Circle("C", "3"));
        final VertexEntity vD = createVertex(new Circle("D", "4"));
        final VertexEntity vE = createVertex(new Circle("E", "5"));
        final VertexEntity vF = createVertex(new Circle("F", "6"));
        final VertexEntity vG = createVertex(new Circle("G", "7"));
        final VertexEntity vH = createVertex(new Circle("H", "8"));
        final VertexEntity vI = createVertex(new Circle("I", "9"));
        final VertexEntity vJ = createVertex(new Circle("J", "10"));
        final VertexEntity vK = createVertex(new Circle("K", "11"));

        // Add relevant edges - left branch:
        saveEdge(new CircleEdge(vA.getId(), vB.getId(), false, true, "left_bar"));
        saveEdge(new CircleEdge(vB.getId(), vC.getId(), false, true, "left_blarg"));
        saveEdge(new CircleEdge(vC.getId(), vD.getId(), false, true, "left_blorg"));
        saveEdge(new CircleEdge(vB.getId(), vE.getId(), false, true, "left_blub"));
        saveEdge(new CircleEdge(vE.getId(), vF.getId(), false, true, "left_schubi"));

        // Add relevant edges - right branch:
        saveEdge(new CircleEdge(vA.getId(), vG.getId(), false, true, "right_foo"));
        saveEdge(new CircleEdge(vG.getId(), vH.getId(), false, true, "right_blob"));
        saveEdge(new CircleEdge(vH.getId(), vI.getId(), false, true, "right_blub"));
        saveEdge(new CircleEdge(vG.getId(), vJ.getId(), false, true, "right_zip"));
        saveEdge(new CircleEdge(vJ.getId(), vK.getId(), false, true, "right_zup"));
    }

    public static void addEdgeDefinition(String edge, String vertexCollectionFrom, String vertexCollectionTo){
        log.info("enter addEdgeDefinition");
        EdgeDefinition edgeDefinition = new EdgeDefinition()
                .collection(edge)
                .from(vertexCollectionFrom)
                .to(vertexCollectionTo);

        arangoGraph.addEdgeDefinition(edgeDefinition);
    }

    public static void createDatabase(String databaseName){
        try {
            arangoDB.createDatabase(databaseName);
        }catch (ArangoDBException ao) {
            // 没有认证时会提示连接超时，忽略这个报错
//            ao.printStackTrace();
//            if (ao != null && ao.getErrorNum() == 1207) {
//                log.info("库名: " + databaseName + " 已经存在, 错误号: " + ao.getErrorNum());
//            }
        }
    }

    public static GraphEntity createGraph(String graphName){
        GraphEntity graphEntity = null;
        try {
            log.info("create graph: " + graphName);
            graphEntity = arangoDatabase.createGraph(graphName, null, null);
        }catch (ArangoDBException ao ){
//            if(ao.getErrorNum() == 1925){
//                log.info("图名: " + graphName + " 已经存在, 错误号: " + ao.getErrorNum());
//            }else {
//                ao.printStackTrace();
//            }
        }

        arangoGraph = arangoDatabase.graph("test_graph");

        return graphEntity;
    }

    public static void createVertexCollection(String vertextCollectionName) {
        log.info("enter createVertexCollection");
        CollectionCreateOptions options_document = new CollectionCreateOptions().type(CollectionType.DOCUMENT);

        createCollection(vertextCollectionName, options_document);

        // 这步并没有创建表
//        arangoVertexCollection = arangoDatabase.graph(graphName).vertexCollection(vertextCollectionName);

//        vertexCollection = arangoDatabase.collection(vertextCollectionName);
    }

    public static ArangoEdgeCollection createEdgeCollection(String graphName,
                                            String edgeCollectionName) {
        log.info("enter createEdgeCollection");
        CollectionCreateOptions options_edge = new CollectionCreateOptions().type(CollectionType.EDGES);
        ArangoCollection arangoCollection = createCollection(edgeCollectionName, options_edge);

        ArangoEdgeCollection arangoEdgeCollection = arangoDatabase.graph(graphName).edgeCollection(edgeCollectionName);

        log.info("arangoCollection: " + arangoCollection.name());
        return arangoEdgeCollection;
    }

    public static ArangoCollection createCollection(String tableName, CollectionCreateOptions options){
        try {
            CollectionEntity collectionEntity = arangoDatabase.createCollection(tableName, options);
        }catch (ArangoDBException ao){
            if(ao.getErrorNum() == 1207){
                log.info("collection name duplicate: " + tableName);
            }else {
                ao.printStackTrace();
            }
        }

        return arangoDatabase.collection(tableName);
    }

    private static VertexEntity createVertex(final Circle vertex) throws ArangoDBException {
        return arangoGraph.vertexCollection("vertex1").insertVertex(vertex);
    }

    public static void deleteDatabase(String databaseName){
        try {
            arangoDB.db(databaseName).drop();
        }catch (ArangoDBException ao){
//            ao.printStackTrace();
//            if(ao != null && ao.getErrorNum() == 1207 || ao.getErrorNum() == 1228 ){
//                log.info("库名: " + databaseName + " 已经存在, 错误号: " + ao.getErrorNum());
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteCollection(String tableName){
        try {
            arangoDatabase.collection(tableName).drop();
        }catch (ArangoDBException ao){
            if(ao.getErrorNum() == 1207){
                log.info("collection name duplicate: " + tableName);
            }else {
                ao.printStackTrace();
            }
        }
    }

    public static void deleteCollectionAll(){
        Collection<CollectionEntity> collectionEntities =  arangoDatabase.getCollections();
        Iterator<CollectionEntity> iterator = collectionEntities.iterator();

        while (iterator.hasNext()){
            CollectionEntity collectionEntity = iterator.next();
            String deleteCollectionName = collectionEntity.getName();
            if(deleteCollectionName.equals("_graphs")) { continue; }

            arangoDatabase.collection(deleteCollectionName).drop();
            log.info("delete collection: " + deleteCollectionName);
        }
    }

    public static BaseDocument getDocument(String tableName, String key){
        ArangoCollection arangoCollection = arangoDatabase.collection(tableName);

        BaseDocument retDocument = arangoCollection.getDocument(key, BaseDocument.class);
        log.info("myDocument.getKey(): " + retDocument.getKey());

        // 获取表id
        log.info("getId: " + retDocument.getId());

        // 获取表的修订记录
        log.info("getRevision: " + retDocument.getRevision());

        // 获取表的key
        log.info("getKey: " + retDocument.getKey());

        // 获取表数据
        log.info("getProperties: " + retDocument.getProperties());

        // 获取表数据(json形式)中的值
        log.info("getAttribute: " + retDocument.getAttribute("name"));

        return retDocument;
    }

    public static void getDocumet(String tableName){
        ArangoCollection arangoCollection = arangoDatabase.collection(tableName);

//        String[] key ={}
//        BaseDocument retDocument = arangoCollection.getDocuments(key, BaseDocument.class);

//        log.info("count:" + count);

        // 获取表的key数组
//        Iterator iterator = indexEntities.iterator();
//        while(iterator.hasNext()){
//            IndexEntity indexEntity = (IndexEntity)iterator.next();
//            log.info("iterator: " + indexEntity.getId());
//        }

    }


    public static String getId(String tableName, String key){
        ArangoCollection arangoCollection = arangoDatabase.collection(tableName);

        BaseDocument retDocument = arangoCollection.getDocument(key, BaseDocument.class);
        // 获取表id
//        log.info("getId: " + retDocument.getId());

        return  retDocument.getId();
    }

    public static void getVertex(ArangoVertexCollection vertexCollection) {
        log.info("enter initVertex with ArangoVertexCollection");
        VertexEntity vertexEntity = vertexCollection.getVertex("shenzhen", VertexEntity.class );
    }

    public static void insertVertex(ArangoVertexCollection vertexCollection) {
        log.info("enter initVertex with ArangoVertexCollection");
//        BaseDocument baseDocument = new BaseDocument();
//        baseDocument.addAttribute("v1", "10");
//        baseDocument.addAttribute("v2", "20");
//        baseDocument.addAttribute("v3", "30");

        BaseEdgeDocument baseEdgeDocument = new BaseEdgeDocument();
        baseEdgeDocument.addAttribute("test1", "10");
        VertexEntity vertexEntity = vertexCollection.insertVertex(baseEdgeDocument, null);
    }

    public static DocumentImportEntity importDocumentsUDF(String vertextCollectionName, Collection<BaseDocument> data) {
        log.info("enter initVertex with ArangoCollection");
        ArangoCollection vertexCollection = arangoDatabase.collection(vertextCollectionName);
        DocumentImportEntity doc = vertexCollection.importDocuments(data);
        return doc;
    }

    public static void insertEdge(ArangoEdgeCollection arangoEdgeCollection, String fromVertex, String toVertex){
        BaseEdgeDocument baseEdgeDocument = new BaseEdgeDocument();
        baseEdgeDocument.setFrom(fromVertex);
        baseEdgeDocument.setTo(toVertex);
        arangoEdgeCollection.insertEdge(baseEdgeDocument);

        log.info("importDocuments successful!");
    }

    public static void insertEdge(String edgeCollectionName, String fromVertex, String toVertex){
        BaseDocument baseDocument = new BaseDocument();
        baseDocument.addAttribute("_from", fromVertex);
        baseDocument.addAttribute("_to", toVertex);
        ArangoCollection arangoCollection = arangoDatabase.collection(edgeCollectionName);
        arangoCollection.insertDocument(baseDocument);

        log.info("importDocuments successful!");
    }

    public static void insertEdgeRandom(String edgeCollectionName ){
        String[] key_str = {"bob", "charlie", "dave", "eve", "alice"};
        Random random = new Random();
        int from = random.nextInt(key_str.length);
        int to = random.nextInt(key_str.length);
        String fromTable = "vertex_tb_name";
        String toTable = "vertex_tb_name";
        insertEdge(edgeCollectionName, getId(fromTable,key_str[from]), getId(toTable,key_str[to]));
    }

    public static EdgeEntity insertEdge(ArangoEdgeCollection arangoEdgeCollection){
        String from = "test_vertex/v1";
        String to = "test_vertex/thh";
        BaseEdgeDocument baseEdgeDocument = new BaseEdgeDocument(from, to);
        EdgeEntity edgeEntity = arangoEdgeCollection.insertEdge(baseEdgeDocument);

        log.info("importDocuments successful!");
        return edgeEntity;
    }

    public static void insertCollection(String tableName){
        ArangoCollection arangoCollection = arangoDatabase.collection(tableName);
//        BaseDocument baseDocument1 = new BaseDocument();
//        baseDocument1.addAttribute(
//                UUID.randomUUID().toString(),
//                UUID.randomUUID().toString()
//        );
//        baseDocument1.addAttribute("name", "Bob");
//        baseDocument1.addAttribute("name", "Charlie");
//        baseDocument1.addAttribute("name", "Dave");
//        baseDocument1.addAttribute("name", "Eve");
//        baseDocument1.addAttribute("name", "Alice");
//        arangoCollection.insertDocument(baseDocument1, null);

        // 由于baseDocument1添加的数据的key都是"name",insertDocument会去重，使用最后一个值
        // 这种情况下要构造多个BaseDocument，使用importDocuments导入才好
        // 单独BaseDocument用于插入数据，添加到属性名称要不同，比如一个javabean的各个key
        Collection<BaseDocument> data = new ArrayList<BaseDocument>();
        String[] value_str = {"Bob", "Charlie", "Dave", "Eve", "Alice"};
        for(int i=0; i<value_str.length; i++){
            BaseDocument baseDocument = new BaseDocument();
            baseDocument.addAttribute("name", value_str[i]);
            data.add(baseDocument);
        }
        DocumentImportEntity doc = arangoCollection.importDocuments(data);
        log.info("importDocuments finished!");
    }

    public static void insertCollection(ArangoCollection arangoCollection, String str_json){
        BaseDocument baseDocument = new BaseDocument();
        baseDocument.addAttribute(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );

        DocumentCreateEntity<String> doc = arangoCollection.insertDocument(str_json);
        log.info(doc.getKey());
    }

    public static void insertEdgeTest(){
        arangoDatabase.createCollection("v1");
        arangoDatabase.createCollection("v2");

        arangoGraph.vertexCollection("v1");

        Circle circle1 = new Circle("A", "1");
        Circle circle2 = new Circle("B", "2");

        VertexEntity v1 = arangoGraph.vertexCollection("v1").insertVertex(circle1);
        VertexEntity v2 = arangoGraph.vertexCollection("v2").insertVertex(circle2);

        BaseEdgeDocument value = new BaseEdgeDocument();
        value.setFrom(v1.getId());
        value.setTo(v2.getId());

        final EdgeEntity edge = arangoGraph.edgeCollection("test_edge").insertEdge(value, null);
        assertThat(edge, is(notNullValue()));
        final BaseEdgeDocument document = arangoDatabase.collection("test_edge").getDocument(edge.getKey(),
                BaseEdgeDocument.class, null);
    }

    public static void listUser(ArangoDB arangoDB){
        Collection<UserEntity> userEntities =  arangoDB.getUsers();
        Iterator iter = userEntities.iterator();
        while (iter.hasNext()){
            UserEntity userEntity = (UserEntity)iter.next();
            log.info(userEntity.getUser());
        }
    }

    private static EdgeEntity saveEdge(final CircleEdge edge) throws ArangoDBException {
        return arangoGraph.edgeCollection("edge1").insertEdge(edge);
    }




}
