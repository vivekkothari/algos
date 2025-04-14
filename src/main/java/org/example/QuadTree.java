package org.example;

/** https://leetcode.com/problems/construct-quad-tree/description/ */
class QuadTree {
  static class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {
      this(false, false);
    }

    public Node(boolean val, boolean isLeaf) {
      this(val, isLeaf, null, null, null, null);
    }

    public Node(
        boolean val,
        boolean isLeaf,
        Node topLeft,
        Node topRight,
        Node bottomLeft,
        Node bottomRight) {
      this.val = val;
      this.isLeaf = isLeaf;
      this.topLeft = topLeft;
      this.topRight = topRight;
      this.bottomLeft = bottomLeft;
      this.bottomRight = bottomRight;
    }
  }

  Node construct(int[][] grid) {
    return helper(grid, 0, 0, grid.length);
  }

  private Node helper(int[][] grid, int i, int j, int w) {
    if (allSame(grid, i, j, w)) return new Node(grid[i][j] == 1, true);

    Node node = new Node(true, false);
    int half = w / 2;
    node.topLeft = helper(grid, i, j, half);
    node.topRight = helper(grid, i, j + half, half);
    node.bottomLeft = helper(grid, i + half, j, half);
    node.bottomRight = helper(grid, i + half, j + half, half);
    return node;
  }

  private boolean allSame(int[][] grid, int i, int j, int w) {
    for (int x = i; x < i + w; ++x)
      for (int y = j; y < j + w; ++y) if (grid[x][y] != grid[i][j]) return false;
    return true;
  }
}
