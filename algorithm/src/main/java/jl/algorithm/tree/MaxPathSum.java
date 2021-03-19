package jl.algorithm.tree;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

/**
 * 二叉树中的最大路径和 <a href="https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/">题目</a>
 *
 * @author Liu Chang
 * @date 2021/3/3
 */
public class MaxPathSum {

    private static int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = root.val;
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftMaxGain = Math.max(maxGain(node.left), 0);
        int rightMaxGain = Math.max(maxGain(node.right), 0);

        int nodePathSum = node.val + leftMaxGain + rightMaxGain;
        int nodeGain = node.val + Math.max(leftMaxGain, rightMaxGain);
        maxSum = Math.max(nodePathSum, maxSum);
        return nodeGain;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 1;

        TreeNode leftNode = new TreeNode();
        leftNode.val = 2;

        TreeNode rightNode = new TreeNode();
        rightNode.val = 3;

        root.left = leftNode;
        root.right = rightNode;

        System.out.println(new MaxPathSum().maxPathSum(root));

    }

}
