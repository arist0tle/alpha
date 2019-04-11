package com.geektcp.alpha.algorithm.tree.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by tanghaiyang on 2019/4/10.
 */
@Slf4j
public class TreeTest {

    /*
    * path: 1,2,5,7
    * */
    @Test
    public void test() {
//        GraphNode graphNodeRoot = buildTree();
        GraphNode graphNodeRoot = buildTree2();
        JSONObject ret = new JSONObject();
        toString(graphNodeRoot, ret);
        log.info(JSON.toJSONString(ret, SerializerFeature.PrettyFormat));


        Stack<String> path = new Stack<>();
//        Queue<String> path = new LinkedBlockingQueue<>();

        GraphNode dst = new GraphNode("vertex/13");
        findTree(graphNodeRoot,path, dst);

    }


    @Test
    public void test2(){
        GraphNode graphNode1 = new GraphNode("vertex/1");
        GraphNode graphNode2 = new GraphNode("vertex/2");
        GraphNode graphNode3 = new GraphNode("vertex/3");
        GraphNode graphNode4 = new GraphNode("vertex/4");
        GraphNode graphNode5 = new GraphNode("vertex/5");
        GraphNode graphNode6 = new GraphNode("vertex/6");
        GraphNode graphNode7 = new GraphNode("vertex/7");
//        graphNode1.addPath(graphNode2);
//        graphNode2.addPath(graphNode3);
        System.out.println(graphNode1.getPath());
    }

    /*
    * 深度优先遍历
    * */
    private boolean findTree(GraphNode node, Stack<String> path, GraphNode to) {
        log.info("push : {0}", node.getVertexId());
//        node.getPath().addPath(node);
        if(node.getPath().isEmpty()){
            node.getPath().add(path);
        }else {
            path = node.getPath().get(0);
        }

        path.push(node.getVertexId());
        if (node.isArrived(to)) {
//            LOG.info("path: {0}",  path.toString());
            return true;
        } else {
            LinkedList<GraphNode> subList = node.getChildren();
            if(subList.isEmpty()){
                node.addPath(path);
                log.info("path: {0}",  path.toString());
                return false;
            }else {
                for (int i = 0; i < subList.size(); i++) {
                    GraphNode subNode = subList.get(i);
                    findTree(subNode, path, to);
                }
            }
        }
        return false;
    }

    /*
    * 广度优先遍历
    * */
//    private boolean findTree2(GraphNode node, Stack<String> path, GraphNode to) {
//        for(int i=0;i<10;i++){
//            if (node.isArrived(to)) {
//                LOG.info("path: {0}", path.toString());
//                path.pop();
//                return true;
//            }else {
//                LinkedList<GraphNode> subList = node.getChildren();
//                if(subList.isEmpty()) {
//                    LOG.info("path: {0}", path.toString());
////                LOG.info("pop : {0}", node.getVertexId());
//                    path.pop();
//                    return false;
//                } else {
//                    for (int j = 0; i < subList.size(); i++) {
//                        GraphNode subNode = subList.get(i);
//                        findTree2(subNode, path, to);
//                    }
//                }
//
//            }
//        }
//
//        return false;
//    }

    private GraphNode buildTree() {
        GraphNode graphNodeRoot = new GraphNode("vertex/0");

        GraphNode graphNode1 = new GraphNode("vertex/1");
        GraphNode graphNode2 = new GraphNode("vertex/2");
        GraphNode graphNode3 = new GraphNode("vertex/3");
        GraphNode graphNode4 = new GraphNode("vertex/4");
        GraphNode graphNode5 = new GraphNode("vertex/5");

        GraphNode graphNode6 = new GraphNode("vertex/6");
        GraphNode graphNode7 = new GraphNode("vertex/7");
        GraphNode graphNode8 = new GraphNode("vertex/8");
        GraphNode graphNode9 = new GraphNode("vertex/9");
        GraphNode graphNode10 = new GraphNode("vertex/10");
        GraphNode graphNode11 = new GraphNode("vertex/11");
        GraphNode graphNode12 = new GraphNode("vertex/12");
        GraphNode graphNode13 = new GraphNode("vertex/13");

        graphNodeRoot.getChildren().add(graphNode1);
        graphNode1.getChildren().add(graphNode2);
        graphNode1.getChildren().add(graphNode3);


        graphNode2.getChildren().add(graphNode4);
        graphNode2.getChildren().add(graphNode5);
        graphNode2.getChildren().add(graphNode6);

        graphNode5.getChildren().add(graphNode7);
//
        graphNode7.getChildren().add(graphNode8);
        graphNode7.getChildren().add(graphNode9);

        graphNode9.getChildren().add(graphNode10);
//        graphNode9.getChildren().add(graphNode11);

        graphNode11.getChildren().add(graphNode12);
        graphNode11.getChildren().add(graphNode13);


        ////////////////////////////////
        graphNode3.getChildren().add(graphNode10);
        graphNode10.getChildren().add(graphNode13);

        // 不能循环插入
//        graphNode5.getChildren().add(graphNode2);

        return graphNodeRoot;
    }


    /*
    * 0,1,4,5
    * 0,2
    * */
    private GraphNode buildTree2() {
        GraphNode graphNodeRoot = new GraphNode("vertex/0");

        GraphNode graphNode1 = new GraphNode("vertex/1");
        GraphNode graphNode2 = new GraphNode("vertex/2");
        GraphNode graphNode4 = new GraphNode("vertex/4");
        GraphNode graphNode5 = new GraphNode("vertex/5");

        graphNodeRoot.getChildren().add(graphNode1);
        graphNodeRoot.getChildren().add(graphNode2);


        graphNode1.getChildren().add(graphNode4);
        graphNode4.getChildren().add(graphNode5);


        return graphNodeRoot;
    }

    private void toString(GraphNode node, JSONObject ret){
        ret.put(node.getVertexId(), new JSONObject());
        LinkedList<GraphNode> children = node.getChildren();
        children.forEach(child ->{
            toString(child, ret.getJSONObject(node.getVertexId()));
//            ret.getJSONObject(node.getVertexId()).put(child.getVertexId(), new JSONObject());
        });
    }

    @Test
    public void testStack(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }

    @Test
    public void testStack1(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.remove(stack.size()-1);
        System.out.println(stack);
        stack.remove(stack.size()-1);
        System.out.println(stack);
    }

}
