package org.example;

import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

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

  public static final class TreeNode {
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
    numBusesToDestination(new int[][] {{1, 2, 7}, {3, 6, 7}}, 1, 6);
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

    //    var node = sortedArrayToBST(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    //    preOrder(node, "");
    largestValues(
        new TreeNode(
            1,
            new TreeNode(3, new TreeNode(5), new TreeNode(3)),
            new TreeNode(2, null, new TreeNode(9))));
  }

  public static List<Integer> largestValues(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Queue<TreeNode> bfs = new LinkedList<>();
    bfs.offer(root);
    Integer levelMax = null;
    while (!bfs.isEmpty()) {
      var size = bfs.size();
      for (int i = 0; i < size; i++) {
        var node = bfs.poll();
        if (node == null) {
          continue;
        }
        bfs.offer(node.left);
        bfs.offer(node.right);
        levelMax = levelMax == null ? node.val : Math.max(levelMax, node.val);
      }
      if (levelMax != null) {
        res.add(levelMax);
        levelMax = null;
      }
    }
    return res;
  }

  public static int numBusesToDestination(int[][] routes, int source, int target) {
    if (source == target) {
      return 0;
    }
    Map<Integer, Set<Integer>> stopsToRoutes = new HashMap<>();
    for (int i = 0; i < routes.length; i++) {
      for (int stop : routes[i]) {
        stopsToRoutes.computeIfAbsent(stop, _ -> new HashSet<>()).add(i);
      }
    }
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {source, 0});
    Set<Integer> visitedBuses = new HashSet<>();
    Set<Integer> visitedStops = new HashSet<>();
    while (!queue.isEmpty()) {
      var size = queue.size();
      for (int i = 0; i < size; i++) {
        var node = queue.poll();
        if (node[0] == target) {
          return node[1];
        }
        for (int bus : stopsToRoutes.getOrDefault(node[0], Set.of())) {
          if (visitedBuses.contains(bus)) {
            continue;
          }
          visitedBuses.add(bus);
          for (int stop : routes[bus]) {
            if (visitedStops.contains(stop)) {
              continue;
            }
            visitedStops.add(stop);
            if (stop != node[0]) {
              queue.offer(new int[] {stop, node[1] + 1});
            }
          }
        }
      }
    }
    return -1;
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

  public static int maxDepthIter(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Stack<Map.Entry<TreeNode, Integer>> stack = new Stack<>();
    stack.push(Map.entry(root, 1));
    int maxDepth = 0;
    while (!stack.isEmpty()) {
      var node = stack.pop();
      int depth = node.getValue();
      maxDepth = Math.max(maxDepth, depth);
      if (node.getKey().left != null) {
        stack.push(Map.entry(node.getKey().left, depth + 1));
      }
      if (node.getKey().right != null) {
        stack.push(Map.entry(node.getKey().right, depth + 1));
      }
    }
    return maxDepth;
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
    // n^2 TC
    if (root == null) {
      return 0;
    }
    int leftHeight = maxDepth(root.left);
    int rightHeight = maxDepth(root.right);
    return Math.max(
        leftHeight + rightHeight,
        Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
  }

  private int maxDiameter = 0;

  public int diameterOfBinaryTreeOptimised(TreeNode root) {
    // n TC
    depth(root);
    return maxDiameter;
  }

  private int depth(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftDepth = depth(node.left);
    int rightDepth = depth(node.right);

    // Update the diameter at this node
    maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

    // Return the height of the tree rooted at this node
    return 1 + Math.max(leftDepth, rightDepth);
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

  /**
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/ Tree
   * is a BST.
   */
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || p == null || q == null) return null;
    if (Math.max(p.val, q.val) < root.val) {
      // left tree, both p and q are on the left
      return lowestCommonAncestor(root.left, p, q);
    } else if (Math.min(p.val, q.val) > root.val) {
      // right tree, both of them are on the right
      return lowestCommonAncestor(root.right, p, q);
    }
    return root;
  }

  /** https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/ tree is NOT a BST. */
  public TreeNode lowestCommonAncestorBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    var left = lowestCommonAncestorBinaryTree(root.left, p, q);
    var right = lowestCommonAncestorBinaryTree(root.right, p, q);
    // We traversed both the subtrees
    if (left == null) {
      // we reached a node where both p/q doesn't exist in left subtree
      return right;
    } else if (right == null) {
      // not found in right subtree
      return left;
    }
    return root;
  }

  public TreeNode lowestCommonAncestorOther(TreeNode root, TreeNode p, TreeNode q) {
    ArrayList<TreeNode> pList = new ArrayList<>();
    hasPath(root, p, pList);
    ArrayList<TreeNode> qList = new ArrayList<>();
    hasPath(root, q, qList);
    int i = 0;
    while (i < pList.size() && i < qList.size() && pList.get(i) == qList.get(i)) {
      i++;
    }
    return pList.get(i - 1);
  }

  static boolean hasPath(TreeNode node, TreeNode x, ArrayList<TreeNode> list) {
    if (node == null) {
      return false;
    }
    list.add(node);
    if (node == x) {
      return true;
    }
    if (hasPath(node.left, x, list) || hasPath(node.right, x, list)) {
      return true;
    }
    list.removeLast();
    return false;
  }

  // https://leetcode.com/problems/maximum-width-of-binary-tree/
  public int widthOfBinaryTree(TreeNode root) {
    record Pair(TreeNode node, int num) {}
    if (root == null) {
      return 0;
    }
    Queue<Pair> queue = new LinkedList<>();
    queue.offer(new Pair(root, 0));
    int width = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      int mmin = queue.peek().num;
      int first = 0, last = 0;
      for (int i = 0; i < size; i++) {
        int curId = queue.peek().num - mmin;
        TreeNode node = queue.peek().node;
        queue.poll();
        if (i == 0) {
          // its first node in level
          first = curId;
        } else if (i == size - 1) {
          last = curId;
        }
        if (node.left != null) {
          queue.offer(new Pair(node.left, curId * 2 + 1));
        }
        if (node.right != null) {
          queue.offer(new Pair(node.right, curId * 2 + 2));
        }
      }
      width = Math.max(width, last - first + 1);
    }
    return width;
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

  /** https://leetcode.com/problems/validate-binary-search-tree/submissions/1572171765/ */
  public boolean isValidBST(TreeNode root) {
    return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public boolean valid(TreeNode node, long left, long right) {
    if (node == null) {
      return true;
    }
    if (left >= node.val || node.val >= right) {
      return false;
    }
    return valid(node.left, left, node.val) && valid(node.right, node.val, right);
  }

  /** https://leetcode.com/problems/kth-smallest-element-in-a-bst/ */
  public static int kthSmallest(TreeNode root, int k) {
    var nodes = new ArrayList<Integer>();
    kthSmallest(root, nodes);
    return nodes.get(k - 1);
  }

  private static void kthSmallest(TreeNode root, List<Integer> nodes) {
    if (root == null) {
      return;
    }
    kthSmallest(root.left, nodes);
    nodes.add(root.val);
    kthSmallest(root.right, nodes);
  }

  public static int kthSmallestOptimal(TreeNode root, int k) {
    int[] nodes = new int[] {k, 0}; // index, val
    kthSmallestOptimal(root, nodes);
    return nodes[1];
  }

  private static void kthSmallestOptimal(TreeNode root, int[] nodes) {
    if (root == null) {
      return;
    }
    kthSmallestOptimal(root.left, nodes);
    nodes[0]--;
    if (nodes[0] == 0) {
      nodes[1] = root.val;
      return;
    }
    kthSmallestOptimal(root.right, nodes);
  }

  // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
  public TreeNode bstFromPreorderOptimal(int[] preorder) {
    int[] index = new int[1];
    return bstFromPreorderOptimal(preorder, Integer.MAX_VALUE, index);
  }

  private TreeNode bstFromPreorderOptimal(int[] preorder, int upperBound, int[] index) {
    if (index[0] == preorder.length || preorder[index[0]] > upperBound) return null;
    TreeNode node = new TreeNode(preorder[index[0]++]);
    node.left = bstFromPreorderOptimal(preorder, node.val, index);
    node.right = bstFromPreorderOptimal(preorder, upperBound, index);
    return node;
  }

  // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
  public TreeNode bstFromPreorder(int[] preorder) {
    int[] inorder = Arrays.copyOf(preorder, preorder.length);
    Arrays.sort(inorder);
    return buildTree(preorder, inorder);
  }

  /** https://neetcode.io/problems/binary-tree-from-preorder-and-inorder-traversal */
  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inorderMap = new HashMap<>(); // value -> index
    for (int i = 0; i < inorder.length; i++) {
      inorderMap.put(inorder[i], i);
    }
    return buildTree(preorder, inorderMap, new int[] {0}, 0, inorder.length - 1);
  }

  private static TreeNode buildTree(
      int[] preorder, Map<Integer, Integer> inorderMap, int[] preIndex, int l, int r) {
    if (l > r) return null;
    TreeNode root = new TreeNode(preorder[preIndex[0]++]);
    var idx = inorderMap.get(root.val);
    root.left = buildTree(preorder, inorderMap, preIndex, l, idx - 1);
    root.right = buildTree(preorder, inorderMap, preIndex, idx + 1, r);
    return root;
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

  /** https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/ */
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

  /** https://leetcode.com/problems/binary-tree-maximum-path-sum/submissions/1572490240/ */
  public int maxPathSum(TreeNode root) {
    int[] res = new int[] {root.val};
    maxPathSum(root, res);
    return res[0];
  }

  private int maxPathSum(TreeNode root, int[] res) {
    if (root == null) {
      return 0;
    }
    int leftMax = Math.max(0, maxPathSum(root.left, res));
    int rightMax = Math.max(0, maxPathSum(root.right, res));
    res[0] = Math.max(res[0], root.val + leftMax + rightMax);
    return root.val + Math.max(leftMax, rightMax);
  }

  /** https://leetcode.com/problems/sum-root-to-leaf-numbers/ */
  public int sumNumbers(TreeNode root) {
    int[] sum = new int[] {0};
    sumNumbers(root, sum, 0);
    return sum[0];
  }

  private void sumNumbers(TreeNode root, int[] sum, int curSum) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      sum[0] += (curSum * 10) + root.val;
      return;
    }
    sumNumbers(root.left, sum, curSum * 10 + root.val);
    sumNumbers(root.right, sum, curSum * 10 + root.val);
  }

  /** https://leetcode.com/problems/binary-tree-right-side-view/ */
  public List<Integer> rightSideView(TreeNode root) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    dfs(root, map, 0);
    return map.values().stream().map(List::getLast).toList();
  }

  private void dfs(TreeNode root, Map<Integer, List<Integer>> map, int level) {
    if (root == null) {
      return;
    }
    map.computeIfAbsent(level, _ -> new ArrayList<>());
    map.get(level).add(root.val);
    dfs(root.left, map, level + 1);
    dfs(root.right, map, level + 1);
  }

  public List<Integer> rightSideViewBfs(TreeNode root) {
    List<Integer> rsv = new ArrayList<>();
    if (root == null) return rsv;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      TreeNode rv = null;
      while (size-- > 0) {
        rv = queue.poll();
        if (rv.left != null) {
          queue.offer(rv.left);
        }
        if (rv.right != null) {
          queue.offer(rv.right);
        }
      }
      rsv.add(rv.val);
    }
    return rsv;
  }

  /** https://leetcode.com/problems/boundary-of-binary-tree/ */
  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    if (root.left != null || root.right != null) {
      res.add(root.val);
    }

    gatherLeft(root.left, res);
    gatherLeaf(root, res);
    Stack<Integer> stack = new Stack<>();
    gatherRight(root.right, stack);
    while (!stack.isEmpty()) {
      res.add(stack.pop());
    }
    return res;
  }

  private void gatherLeft(TreeNode root, List<Integer> res) {
    if (root == null) return;
    if (root.left == null && root.right == null) return; // exclude leaf
    res.add(root.val);
    if (root.left != null) {
      gatherLeft(root.left, res);
    } else {
      gatherLeft(root.right, res);
    }
  }

  private void gatherLeaf(TreeNode root, List<Integer> res) {
    if (root == null) return;
    if (root.left == null && root.right == null) res.add(root.val);
    gatherLeaf(root.left, res);
    gatherLeaf(root.right, res);
  }

  private void gatherRight(TreeNode root, Stack<Integer> res) {
    if (root == null) return;
    if (root.left == null && root.right == null) return; // exclude leaf
    res.push(root.val);
    if (root.right != null) {
      gatherRight(root.right, res);
    } else {
      gatherRight(root.left, res);
    }
  }

  static List<Integer> topView(TreeNode root) {
    Map<Integer, List<NodeData>> columnTable = new TreeMap<>();
    traverse(columnTable, 0, 0, root);

    List<Integer> result = new ArrayList<>();

    for (var entry : columnTable.entrySet()) {
      List<NodeData> nodes = entry.getValue();
      nodes.sort((a, b) -> a.level - b.level);
      result.add(nodes.getFirst().val());
    }
    return result;
  }

  static List<Integer> bottomView(TreeNode root) {
    Map<Integer, List<NodeData>> columnTable = new TreeMap<>();
    traverse(columnTable, 0, 0, root);

    List<Integer> result = new ArrayList<>();

    for (var entry : columnTable.entrySet()) {
      List<NodeData> nodes = entry.getValue();
      nodes.sort((a, b) -> a.level - b.level);
      result.add(nodes.getLast().val());
    }
    return result;
  }

  record NodeData(int level, int val) {}

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    Map<Integer, List<NodeData>> columnTable = new TreeMap<>();
    traverse(columnTable, 0, 0, root);

    List<List<Integer>> result = new ArrayList<>();

    for (var entry : columnTable.entrySet()) {
      List<NodeData> nodes = entry.getValue();
      nodes.sort((a, b) -> a.level != b.level ? a.level - b.level : a.val - b.val);
      result.add(nodes.stream().map(n -> n.val).toList());
    }
    return result;
  }

  private static void traverse(Map<Integer, List<NodeData>> map, int x, int level, TreeNode root) {
    if (root == null) return;
    map.computeIfAbsent(x, _ -> new ArrayList<>()).add(new NodeData(level, root.val));
    traverse(map, x - 1, level + 1, root.left);
    traverse(map, x + 1, level + 1, root.right);
  }

  /** https://leetcode.com/problems/binary-tree-paths/ */
  public static List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<>();
    binaryTreePaths(root, new ArrayList<>(), paths);
    return paths;
  }

  private static void binaryTreePaths(TreeNode root, List<Integer> current, List<String> paths) {
    if (root == null) {
      return;
    }
    current.add(root.val);
    binaryTreePaths(root.left, current, paths);
    binaryTreePaths(root.right, current, paths);
    if (root.left == null && root.right == null) {
      paths.add(current.stream().map(String::valueOf).collect(Collectors.joining("->")));
    }
    current.removeLast();
  }

  /** https://www.naukri.com/code360/problems/path-in-a-tree_3843990?leftPanelTabValue=SUBMISSION */
  public static ArrayList<Integer> pathInATree(TreeNode root, int x) {
    ArrayList<Integer> list = new ArrayList<>();
    hasPath(root, x, list);
    return list;
  }

  static boolean hasPath(TreeNode node, int x, ArrayList<Integer> list) {
    if (node == null) {
      return false;
    }
    list.add(node.val);
    if (node.val == x) {
      return true;
    }
    if (hasPath(node.left, x, list) || hasPath(node.right, x, list)) {
      return true;
    }
    list.removeLast();
    return false;
  }

  // Function to check whether all nodes of a tree have the value
  // equal to the sum of their child nodes.
  public static int isSumProperty(TreeNode root) {
    return isSumPropertyRecur(root) ? 1 : 0;
  }

  public static boolean isSumPropertyRecur(TreeNode root) {
    // Base cases
    if (root == null || (root.left == null && root.right == null)) {
      return true; // Null or leaf nodes satisfy the property
    }
    int leftData = (root.left != null) ? root.left.val : 0;
    int rightData = (root.right != null) ? root.right.val : 0;
    // Check current node and recurse
    return (root.val == leftData + rightData)
        && isSumPropertyRecur(root.left)
        && isSumPropertyRecur(root.right);
  }

  public static void convertToSumTree(TreeNode root) {
    toSumTree(root);
  }

  public static int toSumTree(TreeNode root) {
    if (root == null) return 0;
    int leftVal = toSumTree(root.left);
    int rightValue = toSumTree(root.right);
    int oldValue = root.val;
    root.val = leftVal + rightValue;
    return root.val + oldValue;
  }

  // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, TreeNode> childParent = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      TreeNode node = q.poll();
      if (node.left != null) {
        q.offer(node.left);
        childParent.put(node.left, node);
      }
      if (node.right != null) {
        q.offer(node.right);
        childParent.put(node.right, node);
      }
    }
    Set<TreeNode> visited = new HashSet<>();
    q.offer(target);
    int distance = 0;
    while (!q.isEmpty() && distance < k) {
      int size = q.size();
      distance++;
      for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        visited.add(node);
        TreeNode parent = childParent.get(node);
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (parent != null && !visited.contains(parent)) {
          q.offer(parent);
        }
        if (left != null && !visited.contains(left)) {
          q.offer(left);
        }
        if (right != null && !visited.contains(right)) {
          q.offer(right);
        }
      }
    }
    return q.stream().map(n -> n.val).toList();
  }

  public int amountOfTime(TreeNode root, int start) {
    Map<TreeNode, TreeNode> childParent = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    TreeNode startNode = null;
    while (!q.isEmpty()) {
      TreeNode node = q.poll();
      if (node.val == start) startNode = node;
      if (node.left != null) {
        q.offer(node.left);
        childParent.put(node.left, node);
      }
      if (node.right != null) {
        q.offer(node.right);
        childParent.put(node.right, node);
      }
    }
    Set<TreeNode> visited = new HashSet<>();
    q.offer(startNode);
    int distance = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      distance++;
      for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        visited.add(node);
        TreeNode parent = childParent.get(node);
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (parent != null && !visited.contains(parent)) {
          q.offer(parent);
        }
        if (left != null && !visited.contains(left)) {
          q.offer(left);
        }
        if (right != null && !visited.contains(right)) {
          q.offer(right);
        }
      }
    }
    return distance;
  }

  // https://leetcode.com/problems/search-in-a-binary-search-tree/
  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null || root.val == val) return root;
    if (root.val < val) {
      return searchBST(root.right, val);
    } else {
      return searchBST(root.left, val);
    }
  }

  // Function to return the ceil of given number in BST.
  // https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1
  int findCeil(TreeNode root, int key) {
    if (root == null) return -1;
    if (root.val == key) return key;
    if (key > root.val) return findCeil(root.right, key);

    int ceil = findCeil(root.left, key);
    return ceil >= key && ceil != -1 ? ceil : root.val;
  }

  // https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1
  int findCeilIter(TreeNode root, int key) {
    int ceil = -1;
    while (root != null) {
      if (root.val == key) {
        ceil = key;
        break;
      }
      if (key < root.val) {
        ceil = root.val;
        root = root.left;
      } else {
        root = root.right;
      }
    }
    return ceil;
  }

  // https://www.geeksforgeeks.org/problems/floor-in-bst/1
  int findFloor(TreeNode root, int key) {
    if (root == null) return -1;
    if (root.val == key) return key;
    if (key < root.val) return findFloor(root.left, key);

    int floor = findFloor(root.right, key);
    return floor <= key && floor != -1 ? floor : root.val;
  }

  // https://www.geeksforgeeks.org/problems/floor-in-bst/1
  int findFloorIter(TreeNode root, int key) {
    int floor = -1;
    while (root != null) {
      if (root.val == key) {
        floor = key;
        break;
      }
      if (key < root.val) {
        root = root.left;
      } else {
        floor = root.val;
        root = root.right;
      }
    }
    return floor;
  }

  // https://leetcode.com/problems/insert-into-a-binary-search-tree/
  public TreeNode insertIntoBST(TreeNode root, int val) {
    var node = new TreeNode(val);
    if (root == null) return node;
    var cur = root;
    var parent = root;
    while (cur != null) {
      parent = cur;
      if (cur.val < val) {
        cur = cur.right;
      } else {
        cur = cur.left;
      }
    }
    if (parent.val < val) parent.right = node;
    else parent.left = node;
    return root;
  }

  // returns the inorder successor of the Node x in BST (rooted at 'root')
  // https://www.geeksforgeeks.org/problems/inorder-successor-in-bst/1
  public int inorderSuccessor(TreeNode root, TreeNode x) {
    TreeNode succ = null;
    while (root != null) {
      if (x.val >= root.val) {
        root = root.right;
      } else {
        succ = root;
        root = root.left;
      }
    }
    return succ == null ? -1 : succ.val;
  }

  private TreeNode first, mid, last, prev;

  public void recoverTree(TreeNode root) {
    first = mid = last = prev = null;
    inorder(root);
    last = last != null ? last : mid;
    if (first != null && last != null) {
      int t = first.val;
      first.val = last.val;
      last.val = t;
    }
  }

  private void inorder(TreeNode node) {
    if (node == null) {
      return;
    }
    inorder(node.left);

    if (prev != null && prev.val >= node.val) {
      // if this is first violation, mark them as first and mid
      if (first == null) {
        first = prev;
        mid = node;
      } else {
        // we got second violation
        last = node;
      }
    }

    prev = node;

    inorder(node.right);
  }

  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
  }

  // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
  public Node lowestCommonAncestor(Node p, Node q) {
    int pLen = lenFromRoot(p);
    int qLen = lenFromRoot(q);
    while (pLen > qLen) {
      p = p.parent;
      pLen--;
    }
    while (qLen > pLen) {
      q = q.parent;
      qLen--;
    }
    while (p != null && q != null) {
      if (p == q) {
        return p;
      }
      p = p.parent;
      q = q.parent;
    }
    return null;
  }

  private int lenFromRoot(Node n) {
    int len = 0;
    while (n != null) {
      n = n.parent;
      len++;
    }
    return len;
  }
}
