package org.example;

import java.util.*;
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

  boolean unionBySize(int x, int y) {
    int xpar = find(x);
    int ypar = find(y);
    if (xpar == ypar) return false;

    if (size[xpar] < size[ypar]) {
      parent[xpar] = ypar;
      size[ypar] += size[xpar];
    } else {
      parent[ypar] = xpar;
      size[xpar] += size[ypar];
    }
    return true;
  }

  static int kruskalsMST(int V, int[][] edges) {
    DisjointSet set = new DisjointSet(V);
    Arrays.sort(edges, (a, b) -> a[2] - b[2]);
    int sum = 0;
    for (int[] edge : edges) {
      // different parent
      if (set.unionBySize(edge[0], edge[1])) {
        sum += edge[2];
      }
    }
    return sum;
  }

  /** https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/ */
  public int makeConnected(int n, int[][] connections) {
    DisjointSet set = new DisjointSet(n);
    int extraEdges = 0;
    for (int[] connection : connections) {
      if (!set.unionBySize(connection[0], connection[1])) {
        extraEdges++;
      }
    }
    int components = 0;
    for (int i = 0; i < n; i++) {
      if (set.find(i) == i) components++;
    }
    return extraEdges >= components - 1 ? components - 1 : -1;
  }

  public static List<List<String>> accountsMerge(List<List<String>> accounts) {
    int n = accounts.size();
    DisjointSet set = new DisjointSet(n);
    Map<String, Integer> emailToAccountIdx = new HashMap<>();
    for (int i = 0; i < n; i++) {
      List<String> account = accounts.get(i);
      for (int j = 1; j < account.size(); j++) {
        var email = account.get(j);
        if (!emailToAccountIdx.containsKey(email)) {
          emailToAccountIdx.put(email, i);
        } else {
          set.unionBySize(i, emailToAccountIdx.get(email));
        }
      }
    }

    List<String>[] mergedMails = new List[n];

    for (Map.Entry<String, Integer> entry : emailToAccountIdx.entrySet()) {
      String email = entry.getKey();
      int parent = set.find(entry.getValue());
      if (mergedMails[parent] == null) {
        mergedMails[parent] = new ArrayList<>();
      }
      mergedMails[parent].add(email);
    }

    List<List<String>> res = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      if (mergedMails[i] == null) continue;
      Collections.sort(mergedMails[i]);
      String name = accounts.get(i).getFirst();
      mergedMails[i].addFirst(name);
      res.add(mergedMails[i]);
    }
    return res;
  }

  public static void main(String[] args) {
    List<List<String>> accounts = new ArrayList<>();

    accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
    accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
    accounts.add(Arrays.asList("Mary", "mary@mail.com"));
    accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
    accountsMerge(accounts);
  }

  public static List<Integer> numIslands2(int m, int n, int[][] positions) {
    DisjointSet set = new DisjointSet(m * n); // because those many cells
    boolean[][] visited = new boolean[m][n];
    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    int numIsland = 0;
    List<Integer> ans = new ArrayList<>();
    for (int[] p : positions) {
      int i = p[0], j = p[1];
      if (visited[i][j]) {
        ans.add(numIsland);
        continue;
      }
      visited[i][j] = true;
      numIsland++;
      for (int[] dir : dirs) {
        int nr = i + dir[0];
        int nc = j + dir[1];
        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
          continue;
        }
        if (visited[nr][nc] && set.unionBySize(i * n + j, nr * n + nc)) {
          numIsland--;
        }
      }
      ans.add(numIsland);
    }
    return ans;
  }

  /** https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/ */
  public int removeStones(int[][] stones) {
    int n = stones.length;
    DisjointSet ds = new DisjointSet(n);
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
          ds.unionBySize(i, j);
        }
      }
    }

    int numComponents = 0;
    for (int i = 0; i < n; i++) {
      if (ds.find(i) == i) numComponents++;
    }
    return n - numComponents;
  }
}
