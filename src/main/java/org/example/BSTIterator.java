package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import org.example.Trees.TreeNode;

/** https://leetcode.com/problems/binary-search-tree-iterator/description/ */
public class BSTIterator {

  private final Deque<TreeNode> stack = new ArrayDeque<>();
  private final boolean reverse;

  // if is before, then we want to traverse in desc order
  public BSTIterator(TreeNode root, boolean reverse) {
    this.reverse = reverse;
    populateStack(root);
  }

  public int next() {
    TreeNode node = stack.pop();
    populateStack(reverse ? node.left : node.right);
    return node.val;
  }

  private void populateStack(TreeNode cur) {
    while (cur != null) {
      stack.push(cur);
      cur = reverse ? cur.right : cur.left;
    }
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public static void main(String[] args) {
    TreeNode root =
        new TreeNode(
            1,
            new TreeNode(3, new TreeNode(5), new TreeNode(3)),
            new TreeNode(2, null, new TreeNode(9)));
    BSTIterator iterator = new BSTIterator(root, false);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
    System.out.println(Trees.inorderTraversal(root));
  }
}
