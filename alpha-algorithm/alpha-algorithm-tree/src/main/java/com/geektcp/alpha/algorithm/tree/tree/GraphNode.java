package com.geektcp.alpha.algorithm.tree.tree;

import lombok.Data;

import java.util.*;

/**
 * Created by tanghaiyang on 2019/4/10.
 */
@Data
public class GraphNode {
    private String key;
    private String vertexId;

//    private String from;
//    private String to;

    private String edgeId;

    private boolean traversed = false;

    private boolean printed = false;

    private LinkedList<GraphNode> children = new LinkedList<>();

    private List<Stack<String>> pathList = new Stack<>();

//    private Stack<String> path = new Stack<>();

    public GraphNode(String id){
        this.vertexId = id;
    }

    public boolean isArrived(GraphNode to){
        return this.vertexId.equals(to.getVertexId());
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
