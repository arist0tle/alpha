package com.geektcp.alpha.algorithm.tree.tree;//package com.haizhi.graph.server.arango.search.tree;
//
///**
// * Created by tanghaiyang on 2019/4/11.
// */
//import java.util.Stack;
///**
// * 创建二叉排序树，二叉树的六种遍历
// * @author root
// *
// */
//public class BiTreeTest {
//
//    private class Node {
//        int value;
//        Node left = null;
//        Node right = null;
//
//        private Node(int value) {
//            this.value = value;
//        }
//    }
//
//
//
//    //创建二叉排序树
//    private Node creatBiTree(int[] data) {
//        Node root = new Node(data[0]);
//        for (int i = 1; i < data.length; i++) {
//            insert(root, data[i]);
//        }
//        return root;
//    }
//
//    //插入节点
//    private void insert(Node root, int value) {
//
//        if (value < root.value) {
//            if (root.left == null) {
//                root.left = new Node(value);
//            } else {
//                insert(root.left, value);
//            }
//        } else {
//            if (root.right == null) {
//                root.right = new Node(value);
//            } else {
//                insert(root.right, value);
//            }
//        }
//    }
//
//    static Stack<Node> stack = new Stack<Node>();
//
//    /**
//     * 找出所有从根节点到叶子节点路径和等于n的路径并输出
//     *
//     */
//    private void findPath(Node root, int n) {
//        if (root != null) {
//            stack.push(root);
//            n = n - root.value;
//            if (n == 0 && root.left == null && root.right == null) {//是所求的叶子节点
//                for (Node no : stack) {
//                    System.out.print(no.value);
//                }
//                System.out.println();
//            }
//
//            findPath(root.left, n);
//            findPath(root.right, n);
//            stack.pop();
//        }
//    }
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        //int []data = {6,5,3,4,2,8,7,9,1};
//        int[] data = {8, 9, 5, 3, 6, 2, 4, 7};
//        BiTreeTest bt = new BiTreeTest();
//
//        Node root = bt.creatBiTree(data);
////        System.out.println(bt.getDepth(root));
//        bt.findPath(root, 15);
//        System.out.println(stack.toArray());
//    }
//
//}