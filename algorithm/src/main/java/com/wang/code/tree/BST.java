package com.wang.code.tree;

/**
 * @author WANGJJ
 * @date 2020/04/21
 */
public class BST<T extends Comparable<T>, V> {

    private Node root;

    private class Node{

        private T key;
        private V val;
        private Node left, right;
        private int n;

        public Node(T key, V val, int n){
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public int size(){
        return size(root);
    }

    public int size(Node node){
        if (node == null) {
            return 0;
        }
        return node.n;
    }

}
