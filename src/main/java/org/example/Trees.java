package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Trees {

  record TreeNode(int val, List<TreeNode> children) {
    TreeNode(int val) {
      this(val, null);
    }

    @Override
    public List<TreeNode> children() {
      return children == null ? List.of() : children;
    }
  }

  static final class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, Node left, Node right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static void main(String[] args) {
    //    var root =
    //        new Node(
    //            1,
    //            List.of(
    //                new Node(2, List.of(new Node(10), new Node(20))),
    //                new Node(3, List.of(new Node(11))),
    //                new Node(4, List.of(new Node(12)))));
    //    System.out.println(bfs(root));
    //    System.out.println(bfsIter(root));

    //    preOrder(root, "");
    //    System.out.println("===================");
    //    postOrder(root, "");
    //    System.out.println("===================");
    //    preOrderIter(root);
    //    System.out.println("===================");
    //    postOrderIter(root);
    //    var binaryNode =
    //        new Node(1, new Node(2, new Node(10), new Node(20)), new Node(3, new Node(11), null));
    //    preOrder(binaryNode, "");
    //    invertBinaryTree(binaryNode);
    //    preOrder(binaryNode, "");

    //    findTarget(
    //        new Node(5, new Node(3, new Node(2), new Node(4)), new Node(6, null, new Node(7))),
    // 9);

    var node = sortedArrayToBST(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    preOrder(node, "");
  }

  static List<List<Integer>> bfs(TreeNode root) {
    var ret = new ArrayList<List<Integer>>();
    bfs(root, ret, 0);
    return ret;
  }

  static void bfs(TreeNode root, List<List<Integer>> res, int level) {
    if (root == null) {
      return;
    }
    if (res.size() <= level) {
      res.add(new ArrayList<>());
    }
    res.get(level).add(root.val);
    root.children().forEach(node -> bfs(node, res, level + 1));
  }

  static List<List<Integer>> bfsIter(TreeNode root) {
    if (root == null) {
      return List.of();
    }
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    queue.add(root);
    int currLevel = 0;
    while (!queue.isEmpty()) {
      int len = queue.size();
      res.add(new ArrayList<>());
      for (int i = 0; i < len; i++) {
        var node = queue.poll();
        if (node == null) {
          break;
        }
        res.get(currLevel).add(node.val);
        queue.addAll(node.children());
      }
      currLevel++;
    }
    return res;
  }

  static void preOrder(TreeNode root, String indent) {
    if (root == null) {
      return;
    }
    System.out.println(indent + root.val);
    root.children().forEach(n -> preOrder(n, "  " + indent));
  }

  static void preOrder(Node root, String indent) {
    if (root == null) {
      return;
    }
    System.out.println(indent + root.val);
    preOrder(root.left, "  " + indent);
    preOrder(root.right, "  " + indent);
  }

  static void preOrderIter(TreeNode root) {
    if (root == null) {
      return;
    }
    Stack<NodeAndLevel> stack = new Stack<>();
    stack.push(new NodeAndLevel(root, 0));
    while (!stack.isEmpty()) {
      var node = stack.pop();
      System.out.println("  ".repeat(node.level) + node.node.val);
      for (var child : node.node.children()) {
        stack.push(new NodeAndLevel(child, node.level + 1));
      }
    }
  }

  static void postOrder(Node root, String indent) {
    if (root == null) {
      return;
    }
    postOrder(root.left, "  " + indent);
    postOrder(root.right, "  " + indent);
    System.out.println(indent + root.val);
  }

  record NodeAndLevel(TreeNode node, int level) {}

  static void postOrder(TreeNode root, String indent) {
    if (root == null) {
      return;
    }
    root.children().forEach(n -> postOrder(n, "  " + indent));
    System.out.println(indent + root.val);
  }

  static void postOrderIter(TreeNode root) {
    if (root == null) {
      return;
    }
    Stack<NodeAndLevel> stack = new Stack<>();
    Stack<NodeAndLevel> output = new Stack<>(); // To store nodes in post-order
    stack.push(new NodeAndLevel(root, 0));

    while (!stack.isEmpty()) {
      var node = stack.pop();
      output.push(node); // Push to output stack to reverse processing order
      for (var child : node.node.children()) {
        stack.push(new NodeAndLevel(child, node.level + 1)); // Push children with increased level
      }
    }

    // Now print nodes from the output stack for post-order traversal
    while (!output.isEmpty()) {
      var node = output.pop();
      System.out.println("  ".repeat(node.level) + node.node.val);
    }
  }

  static void invertBinaryTree(Node root) {
    if (root != null) {
      var left = root.left;
      root.left = root.right;
      root.right = left;
      invertBinaryTree(root.left);
      invertBinaryTree(root.right);
    }
  }

  public static int maxDepth(Node root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  /**
   * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
   *
   * <pre>
   *   Given a binary tree, find its minimum depth.
   *
   * The minimum depth is the number of nodes along the shortest path
   * from the root node down to the nearest leaf node.
   *
   * Note: A leaf is a node with no children.
   * </pre>
   */
  public static int minDepth(Node root) {
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
  }

  public static int diameterOfBinaryTree(Node root) {
    if (root == null) {
      return 0;
    }
    return Math.max(
        maxDepth(root.left) + maxDepth(root.right),
        Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
  }

  /**
   * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
   *
   * <pre>
   *   Given the root of a binary search tree and an integer k, return true if there exist
   *   two elements in the BST such that their sum is equal to k, or false otherwise.
   * </pre>
   */
  public static boolean findTarget(Node root, int k) {
    var list = new HashSet<Integer>();
    return postOrder(root, list, k);
  }

  private static boolean postOrder(Node root, Set<Integer> res, int k) {
    if (root == null) {
      return false;
    }
    if (res.contains(k - root.val)) {
      return true;
    }
    res.add(root.val);
    return postOrder(root.left, res, k) || postOrder(root.right, res, k);
  }

  public static Node sortedArrayToBST(int[] nums) {
    if (nums.length == 0) return null;
    return sortedArrayToBST(nums, 0, nums.length - 1);
  }

  private static Node sortedArrayToBST(int[] nums, int low, int high) {
    if (low > high) {
      return null;
    }
    var mid = low + (high - low) / 2;
    return new Node(
        nums[mid], sortedArrayToBST(nums, low, mid - 1), sortedArrayToBST(nums, mid + 1, high));
  }
}
