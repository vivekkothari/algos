package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Trees {

  record NaryTreeNode(int val, List<NaryTreeNode> children) {
    NaryTreeNode(int val) {
      this(val, null);
    }

    @Override
    public List<NaryTreeNode> children() {
      return children == null ? List.of() : children;
    }
  }

  static final class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
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

  static List<List<Integer>> bfs(NaryTreeNode root) {
    var ret = new ArrayList<List<Integer>>();
    bfs(root, ret, 0);
    return ret;
  }

  static void bfs(NaryTreeNode root, List<List<Integer>> res, int level) {
    if (root == null) {
      return;
    }
    if (res.size() <= level) {
      res.add(new ArrayList<>());
    }
    res.get(level).add(root.val);
    root.children().forEach(node -> bfs(node, res, level + 1));
  }

  static List<List<Integer>> bfsIter(NaryTreeNode root) {
    if (root == null) {
      return List.of();
    }
    Queue<NaryTreeNode> queue = new LinkedList<>();
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

  static void preOrder(NaryTreeNode root, String indent) {
    if (root == null) {
      return;
    }
    System.out.println(indent + root.val);
    root.children().forEach(n -> preOrder(n, "  " + indent));
  }

  static void preOrder(TreeNode root, String indent) {
    if (root == null) {
      return;
    }
    System.out.println(indent + root.val);
    preOrder(root.left, "  " + indent);
    preOrder(root.right, "  " + indent);
  }

  static void preOrderIter(NaryTreeNode root) {
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

  static void postOrder(TreeNode root, String indent) {
    if (root == null) {
      return;
    }
    postOrder(root.left, "  " + indent);
    postOrder(root.right, "  " + indent);
    System.out.println(indent + root.val);
  }

  record NodeAndLevel(NaryTreeNode node, int level) {}

  static void postOrder(NaryTreeNode root, String indent) {
    if (root == null) {
      return;
    }
    root.children().forEach(n -> postOrder(n, "  " + indent));
    System.out.println(indent + root.val);
  }

  static void postOrderIter(NaryTreeNode root) {
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

  static void invertBinaryTree(TreeNode root) {
    if (root != null) {
      var left = root.left;
      root.left = root.right;
      root.right = left;
      invertBinaryTree(root.left);
      invertBinaryTree(root.right);
    }
  }

  public static int maxDepth(TreeNode root) {
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
  public static int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
  }

  public static int diameterOfBinaryTree(TreeNode root) {
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
  public static boolean findTarget(TreeNode root, int k) {
    var list = new HashSet<Integer>();
    return postOrder(root, list, k);
  }

  private static boolean postOrder(TreeNode root, Set<Integer> res, int k) {
    if (root == null) {
      return false;
    }
    if (res.contains(k - root.val)) {
      return true;
    }
    res.add(root.val);
    return postOrder(root.left, res, k) || postOrder(root.right, res, k);
  }

  public static TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) return null;
    return sortedArrayToBST(nums, 0, nums.length - 1);
  }

  private static TreeNode sortedArrayToBST(int[] nums, int low, int high) {
    if (low > high) {
      return null;
    }
    var mid = low + (high - low) / 2;
    return new TreeNode(
        nums[mid], sortedArrayToBST(nums, low, mid - 1), sortedArrayToBST(nums, mid + 1, high));
  }

  public static boolean isBalanced(TreeNode root) {
    return height(root) != -1;
  }

  private static int height(TreeNode node) {
    if (node == null) return 0; // Base case: empty tree has height 0

    // Recursively get the height of the left subtree
    int leftHeight = height(node.left);
    if (leftHeight == -1) return -1; // If the left subtree is unbalanced, return -1

    // Recursively get the height of the right subtree
    int rightHeight = height(node.right);
    if (rightHeight == -1) return -1; // If the right subtree is unbalanced, return -1

    // If the height difference between left and right subtrees is more than 1, return -1
    if (Math.abs(leftHeight - rightHeight) > 1) return -1;

    // Return the height of the current node
    return Math.max(leftHeight, rightHeight) + 1;
  }

  public static boolean isSameTree(TreeNode t1, TreeNode t2) {
    // Both root are null, so same
    if (t1 == null && t2 == null) return true;
    // if either is not null, then not same
    if (t1 == null || t2 == null) return false;
    // if value is not same, then false.
    if (t1.val != t2.val) return false;
    return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
  }

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == subRoot) return true;
    if (root == null) return false;
    if (isSameTree(root, subRoot)) return true;
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  /** https://leetcode.com/problems/symmetric-tree/ */
  public static boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return isMirror(root.left, root.right);
  }

  private static boolean isMirror(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null) return false;
    return left.val == right.val
        && isMirror(left.left, right.right)
        && isMirror(left.right, right.left);
  }

  /** https://leetcode.com/problems/binary-tree-inorder-traversal/ */
  public static List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    inorderTraversal(root, res);
    return res;
  }

  private static void inorderTraversal(TreeNode root, List<Integer> res) {
    if (root == null) return;
    inorderTraversal(root.left, res);
    res.add(root.val);
    inorderTraversal(root.right, res);
  }

  /** https://leetcode.com/problems/binary-tree-level-order-traversal/description/ */
  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    levelOrder(root, 0, res);
    return res;
  }

  private static void levelOrder(TreeNode root, int currentLevel, List<List<Integer>> levels) {
    if (root == null) {
      return;
    }
    if (levels.size() <= currentLevel) {
      levels.add(new ArrayList<>());
    }
    levels.get(currentLevel).add(root.val);
    levelOrder(root.left, currentLevel + 1, levels);
    levelOrder(root.right, currentLevel + 1, levels);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    zigzagLevelOrder(root, 0, res);
    return res;
  }

  private static void zigzagLevelOrder(
      TreeNode root, int currentLevel, List<List<Integer>> levels) {
    if (root == null) {
      return;
    }
    if (levels.size() <= currentLevel) {
      levels.add(new LinkedList<>());
    }
    var list = levels.get(currentLevel);
    if (currentLevel % 2 == 0) list.addLast(root.val);
    else list.addFirst(root.val);
    zigzagLevelOrder(root.left, currentLevel + 1, levels);
    zigzagLevelOrder(root.right, currentLevel + 1, levels);
  }

  /**
   * https://leetcode.com/problems/path-sum/
   *
   * <pre>
   * <img src="https://assets.leetcode.com/uploads/2021/01/18/pathsum1.jpg"/>
   * </pre>
   */
  public static boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;
    if (root.left == null && root.right == null) {
      return targetSum - root.val == 0;
    }
    targetSum -= root.val;
    return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
  }

  /**
   * https://leetcode.com/problems/path-sum-ii/
   *
   * <pre>
   *   <img src="https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg"/>
   *   Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
   * Output: [[5,4,11,2],[5,8,4,5]]
   * Explanation: There are two paths whose sum equals targetSum:
   * 5 + 4 + 11 + 2 = 22
   * 5 + 8 + 4 + 5 = 22
   * </pre>
   */
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    pathSum(root, targetSum, new LinkedList<>(), res);
    return res;
  }

  public void pathSum(TreeNode root, int targetSum, List<Integer> temp, List<List<Integer>> res) {
    if (root == null) return;
    temp.add(root.val);
    if (root.left == null && root.right == null) {
      if (targetSum - root.val == 0) {
        res.add(List.copyOf(temp));
      }
    }
    targetSum -= root.val;
    pathSum(root.left, targetSum, new ArrayList<>(temp), res);
    pathSum(root.right, targetSum, new ArrayList<>(temp), res);
  }

  /**
   * https://leetcode.com/problems/flatten-binary-tree-to-linked-list
   *
   * <pre>Morris Traversal.</pre>
   */
  public void flatten(TreeNode root) {
    if (root != null) revPreOrder(root);
  }

  TreeNode head = null;

  private void revPreOrder(TreeNode node) {
    if (node.right != null) revPreOrder(node.right);
    if (node.left != null) revPreOrder(node.left);
    node.left = null;
    node.right = head;
    head = node;
  }

  /**
   * https://leetcode.com/problems/count-complete-tree-nodes
   *
   * <pre>
   *   Given the root of a complete binary tree, return the number of the nodes in the tree.
   * According to Wikipedia, every level, except possibly the last,
   * is completely filled in a complete binary tree, and all nodes in the
   * last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
   * Design an algorithm that runs in less than O(n) time complexity.
   * </pre>
   */
  public int countNodes(TreeNode root) {
    int h = heightComplete(root);
    if (h == 0) return 0;
    return heightComplete(root.right) == h - 1
        ? (1 << h - 1) + countNodes(root.right)
        : (1 << h - 2) + countNodes(root.left);
  }

  int heightComplete(TreeNode root) {
    return root == null ? 0 : 1 + heightComplete(root.left);
  }

  /** https://leetcode.com/problems/sum-of-left-leaves */
  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) return 0;
    int sum = 0;
    // Check if there is a left and it is a LEAF node (its left and right are null)
    if (root.left != null && root.left.left == null && root.left.right == null) {
      sum += root.left.val;
    }
    sum += sumOfLeftLeaves(root.left);
    sum += sumOfLeftLeaves(root.right);
    return sum;
  }
}
