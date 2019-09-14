package com.geektcp.alpha.algorithm.tree.binary;

/**
 * Created by tanghaiyang on 2019/1/21.
 */
public class RedBlackTree {

    public static void main(String[] args) {

    }

    public class TreeNode {
        private String value;
        private boolean red;

        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

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
    }


}
