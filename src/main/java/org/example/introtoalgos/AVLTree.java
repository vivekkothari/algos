package org.example.introtoalgos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AVLTree<T extends Comparable<T>> {
  private class Node {
    T value;
    Node left, right, parent; // Added parent pointer
    int height;

    Node(T value, Node parent) {
      this.value = value;
      this.height = 1;
      this.parent = parent; // Store reference to parent
    }
  }

  private Node root;

  public void insert(T value) {
    root = insert(root, value, null);
  }

  void inorderTraversal() {
    inorder(root, "");
  }

  private void inorder(Node root, String indent) {
    if (root == null) return;
    inorder(root.left, "   " + indent);
    System.out.println(indent + root.value);
    inorder(root.right, "   " + indent);
  }

  void levels() {
    List<List<T>> levels = new ArrayList<>();
    levelOrder(root, 0, levels);
    var height = levels.size();
    for (int level = 0; level < height; level++) {
      List<T> nodes = levels.get(level);

      int spacesBefore = (int) Math.pow(2, height - level - 1) - 1;
      int spacesBetween = (int) Math.pow(2, height - level) - 1;

      printSpaces(spacesBefore);
      for (int i = 0; i < nodes.size(); i++) {
        System.out.print(nodes.get(i));
        if (i < nodes.size() - 1) {
          printSpaces(spacesBetween);
        }
      }
      System.out.println();
    }
  }

  private static void printSpaces(int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(" ");
    }
  }

  private void levelOrder(Node root, int currentLevel, List<List<T>> levels) {
    if (root == null) {
      return;
    }
    if (levels.size() <= currentLevel) {
      levels.add(new ArrayList<>());
    }
    levels.get(currentLevel).add(root.value);
    levelOrder(root.left, currentLevel + 1, levels);
    levelOrder(root.right, currentLevel + 1, levels);
  }

  private Node insert(Node node, T value, Node parent) {
    if (node == null) return new Node(value, parent);

    if (value.compareTo(node.value) < 0) {
      node.left = insert(node.left, value, node);
    } else {
      node.right = insert(node.right, value, node);
    }

    node.height = 1 + Math.max(height(node.left), height(node.right));

    return balance(node);
  }

  private Node balance(Node node) {
    int balanceFactor = height(node.left) - height(node.right);

    if (balanceFactor > 1) {
      if (height(node.left.left) < height(node.left.right)) {
        node.left = rotateLeft(node.left);
      }
      return rotateRight(node);
    }
    if (balanceFactor < -1) {
      if (height(node.right.right) < height(node.right.left)) {
        node.right = rotateRight(node.right);
      }
      return rotateLeft(node);
    }
    return node;
  }

  private Node rotateRight(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    if (T2 != null) T2.parent = y;
    x.parent = y.parent;
    y.parent = x;

    if (x.parent == null) root = x;

    y.height = 1 + Math.max(height(y.left), height(y.right));
    x.height = 1 + Math.max(height(x.left), height(x.right));

    return x;
  }

  private Node rotateLeft(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    if (T2 != null) T2.parent = x;
    y.parent = x.parent;
    x.parent = y;

    if (y.parent == null) root = y;

    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));

    return y;
  }

  private int height(Node node) {
    return node == null ? 0 : node.height;
  }

  public boolean search(T value) {
    return searchNode(value) != null;
  }

  private Node searchNode(T value) {
    Node current = root;
    while (current != null) {
      if (value.equals(current.value)) return current;
      current = value.compareTo(current.value) < 0 ? current.left : current.right;
    }
    return null;
  }

  public T higher(T value) {
    var node = searchNode(value);
    if (node == null) return null;
    else return node.right.value;
  }

  public T lower(T value) {
    var node = searchNode(value);
    if (node == null) return null;
    else return node.left.value;
  }

  public T getMin() {
    Node current = root;
    while (current.left != null) {
      current = current.left;
    }
    return current.value;
  }

  public T getMax() {
    Node current = root;
    while (current.right != null) {
      current = current.right;
    }
    return current.value;
  }

  public void delete(T value) {
    // Implement AVL delete logic with parent pointers
  }

  public T lowestCommonAncestor(T value1, T value2) {
    Node n1 = findNode(root, value1);
    Node n2 = findNode(root, value2);

    if (n1 == null || n2 == null) return null;

    return findLCAUsingParents(n1, n2).value;
  }

  private Node findLCAUsingParents(Node n1, Node n2) {
    Set<Node> ancestors = new HashSet<>();

    while (n1 != null) {
      ancestors.add(n1);
      n1 = n1.parent;
    }

    while (n2 != null) {
      if (ancestors.contains(n2)) return n2;
      n2 = n2.parent;
    }

    return null;
  }

  private Node findNode(Node node, T value) {
    if (node == null) return null;
    if (value.equals(node.value)) return node;
    return value.compareTo(node.value) < 0
        ? findNode(node.left, value)
        : findNode(node.right, value);
  }

  public static void main(String[] args) {
    var tree = new AVLTree<Integer>();
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    tree.insert(4);
    tree.insert(15);
    tree.insert(2);
    tree.insert(6);
    System.out.println("===============");
    tree.inorderTraversal();
    System.out.println("===============");
    tree.levels();
    System.out.println(tree.getMin());
    System.out.println(tree.getMax());
    //    System.out.println("===============");
    //    System.out.println(tree.higher(20));
    //    System.out.println(tree.lower(20));
  }
}
