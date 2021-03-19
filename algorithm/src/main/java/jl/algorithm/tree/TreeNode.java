package jl.algorithm.tree;

/**
 * TreeNode
 *
 * @author Liu Chang
 * @date 2021/3/3
 */
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}

