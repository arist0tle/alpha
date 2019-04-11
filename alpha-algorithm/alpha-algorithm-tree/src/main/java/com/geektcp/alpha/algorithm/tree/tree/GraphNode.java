package com.geektcp.alpha.algorithm.tree.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tanghaiyang on 2019/4/10.
 */
public class GraphNode {
    private String key;
    private String vertexId;

//    private String from;
//    private String to;

    private String edgeId;

    private LinkedList<GraphNode> children = new LinkedList<>();

    private List<Stack<String>> pathList = new ArrayList<>();

    public GraphNode(String id){
        this.vertexId = id;
    }

    public boolean isArrived(GraphNode to){
        return this.vertexId.equals(to.getVertexId());
    }

    public String getVertexId(){
        return this.vertexId;
    }

    public List<Stack<String>> getPath(){
        return this.pathList;
    }

    public LinkedList<GraphNode> getChildren(){
        return this.children;
    }


//    public void addPath(GraphNode node){
//        if(pathList.isEmpty()){
//            List<String> path = new ArrayList<>();
//            path.add(this.vertexId);
//            pathList.add(path);
//        }
//        pathList.forEach(path->{
//            path.add(node.getVertexId());
//        });
//    }


    public void addPath(Stack<String> path){
        pathList.add(path);
    }




}
