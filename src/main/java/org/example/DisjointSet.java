package org.example;

import java.util.Arrays;

/**
 * We can either do union by rank or size, prefer size as after path compression, rank becomes
 * useless.
 */
public class DisjointSet {

  private final int[] parent;
  private final int[] rank;
  private final int[] size;

  public DisjointSet(int n) {
    rank = new int[n];
    parent = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  // Finds parent
  int find(int n) {
    if (n == parent[n]) {
      return n;
    }
    // Do path compression, basically store ultimate parent as parent.
    return parent[n] = find(parent[n]);
  }

  void unionByRank(int x, int y) {
    int xpar = find(x);
    int ypar = find(y);
    if (xpar == ypar) return;

    if (rank[xpar] < rank[ypar]) {
      parent[xpar] = ypar;
    } else if (rank[xpar] > rank[ypar]) {
      parent[ypar] = xpar;
    } else {
      parent[ypar] = xpar;
      rank[xpar]++;
    }
  }

  void unionBySize(int x, int y) {
    int xpar = find(x);
    int ypar = find(y);
    if (xpar == ypar) return;

    if (size[xpar] < size[ypar]) {
      parent[xpar] = ypar;
      size[ypar] += size[xpar];
    } else {
      parent[ypar] = xpar;
      size[xpar] += size[ypar];
    }
  }

  static int kruskalsMST(int V, int[][] edges) {
    DisjointSet set = new DisjointSet(V);
    Arrays.sort(edges, (a, b) -> a[2] - b[2]);
    int sum = 0;
    for (int[] edge : edges) {
      // different parent
      if (set.find(edge[0]) != set.find(edge[1])) {
        set.unionBySize(edge[0], edge[1]);
        sum += edge[2];
      }
    }
    return sum;
  }
}
