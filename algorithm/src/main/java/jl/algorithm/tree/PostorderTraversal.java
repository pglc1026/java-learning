package jl.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树后序遍历
 *
 * @author Liu Chang
 * @date 2021/3/3
 */
public class PostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.peek();
            if (node.right == null || node.right == lastVisit) {
                // 如果右子树为空或者右子树已经访问过，则可以直接输出
                result.add(node.val);
                lastVisit = node;
                node = null;
                stack.pop();
            } else {
                // 继续遍历右子树
                node = node.right;
            }
        }

        return result;
    }

}
