package com.geektcp.alpha.algorithm.tree.binary;

import org.junit.Test;

/**
 * Created by tanghaiyang on 2019/1/21.
 */
public class RedBlackTree {

    private static final class TreeNode {
        private int value;
        private boolean red;

        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int value){
            this.value = value;
        }

        public void leftRotate() {
            TreeNode rawLeft = left;
            left = parent;
            left.right = rawLeft;
        }

        public void rightRotate() {
            TreeNode rawRight = right;
            right = parent;
            right.left = rawRight;
        }

        public void put(TreeNode treeNode){
            // do something
        }

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);

        TreeNode one = new TreeNode(20);
        TreeNode two = new TreeNode(30);
        TreeNode three = new TreeNode(40);

        root.left = one;
        root.right = two;




    }


}
