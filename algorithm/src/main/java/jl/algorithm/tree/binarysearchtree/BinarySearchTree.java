package jl.algorithm.tree.binarysearchtree;

/**
 * BinarySearchTree
 * 二叉查找树
 *
 * @author Liu Chang
 * @date 2020/1/6
 */
public class BinarySearchTree {

    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }

        return null;
    }

    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            } else {    // data > p.data
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }
        }

    }

    public void delete(int data) {
        // 初始化，p用于记录要删除的节点，pp用于记录该节点的父节点
        Node p = tree;
        Node pp = null;
        // 先查找该节点
        while (p != null && p.data != data) {
            pp = p;
            if (data < p.data) {
                p = p.left;
            } else { // data > p.data
                p = p.right;
            }
        }

        if (p == null) return; // 没有找到

        // p有两个节点
        while (p.left != null && p.right != null) {
            // 找到右子树的最小节点
            Node minP = p.left; // 最小节点
            Node minPP = p;     // 最小节点的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 将minP的值替换到p中
            p.data = minP.data;
            // 然后将p指向minP所在的位置，即左子树最小值的位置，然后准备删除该节点
            p = minP;
            pp = minPP;
        }

        // 如果删除节点是叶子节点或只有一个子节点
        Node child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child;   // 删除的是跟节点
        else if (pp.left == p) pp.left = child; // 删除p
        else pp.right = child;  // 删除p
    }



    public static class Node {

        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

}
