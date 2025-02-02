package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Trees {

  record Node(int val, List<Node> children) {
    Node(int val) {
      this(val, null);
    }

    @Override
    public List<Node> children() {
      return children == null ? List.of() : children;
    }
  }

  static final class BinaryNode {
    int val;
    BinaryNode left;
    BinaryNode right;

    public BinaryNode(int val) {
      this.val = val;
    }

    public BinaryNode(int val, BinaryNode left, BinaryNode right) {
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
    var binaryNode =
        new BinaryNode(
            1,
            new BinaryNode(2, new BinaryNode(10), new BinaryNode(20)),
            new BinaryNode(3, new BinaryNode(11), null));
    preOrder(binaryNode, "");
    investBinaryTree(binaryNode);
    preOrder(binaryNode, "");
  }

  static List<List<Integer>> bfs(Node root) {
    var ret = new ArrayList<List<Integer>>();
    bfs(root, ret, 0);
    return ret;
  }

  static void bfs(Node root, List<List<Integer>> res, int level) {
    if (root == null) {
      return;
    }
    if (res.size() <= level) {
      res.add(new ArrayList<>());
    }
    res.get(level).add(root.val);
    root.children().forEach(node -> bfs(node, res, level + 1));
  }

  static List<List<Integer>> bfsIter(Node root) {
    if (root == null) {
      return List.of();
    }
    Queue<Node> queue = new LinkedList<>();
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

  static void preOrder(Node root, String indent) {
    if (root == null) {
      return;
    }
    System.out.println(indent + root.val);
    root.children().forEach(n -> preOrder(n, "  " + indent));
  }

  static void preOrder(BinaryNode root, String indent) {
    if (root == null) {
      return;
    }
    System.out.println(indent + root.val);
    preOrder(root.left, "  " + indent);
    preOrder(root.right, "  " + indent);
  }

  static void preOrderIter(Node root) {
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

  static void postOrder(BinaryNode root, String indent) {
    if (root == null) {
      return;
    }
    postOrder(root.left, "  " + indent);
    postOrder(root.right, "  " + indent);
    System.out.println(indent + root.val);
  }

  record NodeAndLevel(Node node, int level) {}

  static void postOrder(Node root, String indent) {
    if (root == null) {
      return;
    }
    root.children().forEach(n -> postOrder(n, "  " + indent));
    System.out.println(indent + root.val);
  }

  static void postOrderIter(Node root) {
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

  static void investBinaryTree(BinaryNode root) {
    if (root != null) {
      var left = root.left;
      root.left = root.right;
      root.right = left;
      investBinaryTree(root.left);
      investBinaryTree(root.right);
    }
  }
}
