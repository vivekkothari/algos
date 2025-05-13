package org.example;

import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;

class Graphs {

  public static void main(String[] args) {
    int[][] ints = {
      {0, 6, 7},
      {0, 1, 2},
      {1, 2, 3},
      {1, 3, 3},
      {6, 3, 3},
      {3, 5, 1},
      {6, 5, 1},
      {2, 5, 1},
      {0, 4, 5},
      {4, 6, 2}
    };
    countPaths(7, ints);
    //    System.out.println(
    //        ladderLengthBiDirectionalBfs(
    //            "hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    //    canFinishKahn(2, new int[][] {{1, 0}, {0, 1}});
    //    countIslands(new char[][] {{'W', 'L'}, {'L', 'W'}, {'L', 'L'}, {'L', 'W'}});
    //    System.out.println(
    //        bfs(5, List.of(List.of(1, 2, 3), List.of(), List.of(4), List.of(), List.of())));
    //    System.out.println(
    //        dfs(5, List.of(List.of(1, 2, 3), List.of(), List.of(4), List.of(), List.of())));
    //    bfs(
    //        Map.of(
    //            "A",
    //            List.of("B", "C", "D"),
    //            "B",
    //            List.of("E"),
    //            "C",
    //            List.of("E", "F"),
    //            "D",
    //            List.of("G"),
    //            "F",
    //            List.of("G")),
    //        "A");

    //    bfs(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 0, 0);
  }

  public List<Integer> mergeSequences(List<List<Integer>> sequences) {
    Map<Integer, Set<Integer>> adjList = new HashMap<>();
    Map<Integer, Integer> indegree = new HashMap<>();

    // Build graph and calculate indegree
    for (var seq : sequences) {
      for (var i = 1; i < seq.size(); i++) {
        int from = seq.get(i - 1), to = seq.get(i);
        adjList.computeIfAbsent(from, k -> new HashSet<>()).add(to);
        adjList.putIfAbsent(to, new HashSet<>());
        indegree.put(to, indegree.getOrDefault(to, 0) + 1);
        indegree.putIfAbsent(from, 0);
      }
    }

    // Topological Sort using Kahn's Algorithm
    Queue<Integer> queue = new LinkedList<>();
    indegree.forEach(
        (node, count) -> {
          if (count == 0) queue.offer(node);
        });

    List<Integer> result = new ArrayList<>();
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result.add(current);
      for (int neighbor : adjList.getOrDefault(current, Collections.emptySet())) {
        if (indegree.merge(neighbor, -1, Integer::sum) == 0) {
          queue.offer(neighbor);
        }
      }
    }

    // Check for cycle
    if (result.size() != indegree.size()) {
      throw new IllegalStateException("Cycle detected! No valid order exists.");
    }

    return result;
  }

  static void bfs(Map<String, List<String>> graph, String startAt) {
    if (!graph.containsKey(startAt)) return;
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    queue.offer(startAt);
    while (!queue.isEmpty()) {
      var node = queue.poll();
      System.out.println(node);
      graph.getOrDefault(node, List.of()).stream().filter(visited::add).forEach(queue::offer);
    }
  }

  static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up
  static int[][] allDirections = {
    {0, 1}, {1, 0}, {0, -1}, {-1, 0}, // Right, Down, Left, Up
    {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
  };
  static int[][] rightAndDown = {{0, 1}, {1, 0}}; // Right, Down

  // BFS the matrix starting from x, y
  static void bfs(int[][] graph, int startX, int startY) {
    // Use in[] to store the vertices
    var rows = graph.length;
    var cols = graph[0].length;
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {startX, startY});
    var visited = new boolean[rows][cols];
    while (!queue.isEmpty()) {
      var poll = queue.poll();
      int x = poll[0], y = poll[1];
      System.out.println("Visited: (" + x + ", " + y + ") -> Value: " + graph[x][y]);
      for (var dir : allDirections) {
        int newX = x + dir[0], newY = y + dir[1];
        if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
          queue.offer(new int[] {newX, newY});
          visited[newX][newY] = true;
        }
      }
    }
  }

  public static boolean canFinishKahn(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
    for (var i = 0; i < numCourses; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    var indegree = new int[numCourses];
    for (var edge : prerequisites) {
      adjacencyList.get(edge[1]).add(edge[0]);
      indegree[edge[0]]++;
    }
    Queue<Integer> q = new LinkedList<>();
    for (var i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
      }
    }
    var count = 0;
    while (!q.isEmpty()) {
      var node = q.poll();
      count++;
      for (var next : adjacencyList.get(node)) {
        indegree[next]--;
        if (indegree[next] == 0) {
          q.offer(next);
        }
      }
    }
    return count == numCourses;
  }

  public static int[] findOrderKahn(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
    for (var i = 0; i < numCourses; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    var indegree = new int[numCourses];
    for (var edge : prerequisites) {
      adjacencyList.get(edge[1]).add(edge[0]);
      indegree[edge[0]]++;
    }
    Queue<Integer> q = new LinkedList<>();
    for (var i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
      }
    }
    var count = 0;
    var res = new int[numCourses];
    while (!q.isEmpty()) {
      var node = q.poll();
      res[count++] = node;
      for (var next : adjacencyList.get(node)) {
        indegree[next]--;
        if (indegree[next] == 0) {
          q.offer(next);
        }
      }
    }
    return count == numCourses ? res : new int[] {};
  }

  /** https://www.geeksforgeeks.org/problems/alien-dictionary/1 */
  public static String findOrderAlienDict(String[] words) {
    List<List<Integer>> adjList = new ArrayList<>(26);
    for (var i = 0; i < 26; i++) {
      adjList.add(new ArrayList<>());
    }
    Set<Integer> lettersPresent = new HashSet<>();
    for (var word : words) {
      for (var c : word.toCharArray()) {
        lettersPresent.add(c - 'a');
      }
    }

    var indegree = new int[26];
    for (var i = 0; i < words.length - 1; i++) {
      var s1 = words[i];
      var s2 = words[i + 1];
      if (s1.startsWith(s2) && s1.length() > s2.length()) return "";
      var len = Math.min(s1.length(), s2.length());
      for (var j = 0; j < len; j++) {
        if (s1.charAt(j) != s2.charAt(j)) {
          adjList.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
          indegree[s2.charAt(j) - 'a']++;
          break;
        }
      }
    }

    Queue<Integer> q = new LinkedList<>();
    for (var i = 0; i < 26; i++) {
      if (lettersPresent.contains(i) && indegree[i] == 0) {
        q.offer(i);
      }
    }

    var sb = new StringBuilder();
    while (!q.isEmpty()) {
      var node = q.poll();
      sb.append((char) (node + 'a'));

      for (var i : adjList.get(node)) {
        indegree[i]--;
        if (indegree[i] == 0) {
          q.offer(i);
        }
      }
    }
    return sb.length() == lettersPresent.size() ? sb.toString() : "";
  }

  /** https://leetcode.com/problems/course-schedule/ */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
    for (var i = 0; i < numCourses; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (var edge : prerequisites) {
      adjacencyList.get(edge[1]).add(edge[0]);
    }

    Set<Integer> visited = new HashSet<>();
    Set<Integer> onPath = new HashSet<>();

    for (var i = 0; i < numCourses; i++) {
      if (!visited.contains(i)) {
        if (hasCycle(adjacencyList, visited, onPath, i)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean hasCycle(
      List<List<Integer>> adjacencyList, Set<Integer> visited, Set<Integer> onPath, int node) {
    if (onPath.contains(node)) {
      return true; // cycle detected
    }
    if (visited.contains(node)) {
      return false; // already processed
    }

    visited.add(node);
    onPath.add(node);
    for (var next : adjacencyList.get(node)) {
      if (hasCycle(adjacencyList, visited, onPath, next)) {
        return true;
      }
    }
    onPath.remove(node); // backtrack
    return false;
  }

  /** https://leetcode.com/problems/course-schedule-ii/ */
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
    for (var i = 0; i < numCourses; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (var edge : prerequisites) {
      adjacencyList.get(edge[1]).add(edge[0]);
    }

    Set<Integer> visited = new HashSet<>();
    Set<Integer> onPath = new HashSet<>();
    List<Integer> result = new LinkedList<>();

    for (var i = 0; i < numCourses; i++) {
      if (!visited.contains(i)) {
        if (hasCycle(adjacencyList, visited, onPath, i, result)) {
          return new int[] {};
        }
      }
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  private boolean hasCycle(
      List<List<Integer>> adjacencyList,
      Set<Integer> visited,
      Set<Integer> onPath,
      int node,
      List<Integer> result) {
    if (onPath.contains(node)) {
      return true; // cycle detected
    }
    if (visited.contains(node)) {
      return false; // already processed
    }

    visited.add(node);
    onPath.add(node);
    for (var next : adjacencyList.get(node)) {
      if (hasCycle(adjacencyList, visited, onPath, next, result)) {
        return true;
      }
    }
    onPath.remove(node); // backtrack
    result.addFirst(node);
    return false;
  }

  static List<Integer> bfs(int vertices, List<List<Integer>> adjList) {
    var visited = new boolean[vertices];
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);
    visited[0] = true;
    List<Integer> bfs = new ArrayList<>();
    while (!queue.isEmpty()) {
      var poll = queue.poll();
      bfs.add(poll);
      for (var i : adjList.get(poll)) {
        if (!visited[i]) {
          queue.offer(i);
          visited[i] = true;
        }
      }
    }
    return bfs;
  }

  static List<Integer> dfs(int vertices, List<List<Integer>> adjList) {
    var visited = new boolean[vertices];
    Deque<Integer> stack = new LinkedList<>();
    stack.push(0);
    visited[0] = true;
    List<Integer> dfs = new ArrayList<>();
    while (!stack.isEmpty()) {
      var pop = stack.pop();
      dfs.add(pop);
      for (var i : adjList.get(pop)) {
        if (!visited[i]) {
          stack.push(i);
          visited[i] = true;
        }
      }
    }
    return dfs;
  }

  public static int countIslands(char[][] grid) {
    int n = grid.length, m = grid[0].length;
    var visited = new boolean[n][m];

    var num = 0;
    for (var i = 0; i < n; i++) {
      for (var j = 0; j < m; j++) {
        if (!visited[i][j] && grid[i][j] == 'L') {
          num++;
          bfs(grid, n, m, i, j, visited);
        }
      }
    }
    return num;
  }

  static void bfs(char[][] grid, int n, int m, int sx, int sy, boolean[][] visited) {
    visited[sx][sy] = true;
    for (var dir : allDirections) {
      var ex = sx + dir[0];
      var ey = sy + dir[1];
      if (ex >= n || ex < 0 || ey >= m || ey < 0 || grid[ex][ey] != 'L' || visited[ex][ey]) {
        continue;
      }
      bfs(grid, n, m, ex, ey, visited);
    }
  }

  /** https://leetcode.com/problems/01-matrix/ */
  public int[][] updateMatrix(int[][] mat) {
    int m = mat.length, n = mat[0].length;
    Queue<int[]> bfs = new LinkedList<>();
    for (var i = 0; i < m; i++) {
      for (var j = 0; j < n; j++) {
        if (mat[i][j] == 0) {
          bfs.offer(new int[] {i, j});
        } else {
          mat[i][j] = -1;
        }
      }
    }
    var dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    while (!bfs.isEmpty()) {
      var cell = bfs.poll();
      for (var dir : dirs) {
        var nr = cell[0] + dir[0];
        var nc = cell[1] + dir[1];
        if (nr < 0 || nr >= m || nc < 0 || nc >= n || mat[nr][nc] != -1) {
          continue;
        }
        mat[nr][nc] = 1 + mat[cell[0]][cell[1]];
        bfs.offer(new int[] {nr, nc});
      }
    }
    return mat;
  }

  /** https://leetcode.com/problems/surrounded-regions/ */
  public static void surroundedRegion(char[][] board) {
    var m = board.length;
    var n = board[0].length;
    // Explore all 4 directions, bottom, top, right, left
    var directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // Top row
    for (var j = 0; j < n; j++) {
      if (board[0][j] == 'O') {
        surroundedRegionBfs(board, 0, j, directions, m, n);
      }
    }
    // bottom row
    for (var j = 0; j < n; j++) {
      if (board[m - 1][j] == 'O') {
        surroundedRegionBfs(board, m - 1, j, directions, m, n);
      }
    }
    // left column
    for (var i = 0; i < m; i++) {
      if (board[i][0] == 'O') {
        surroundedRegionBfs(board, i, 0, directions, m, n);
      }
    }
    // right column
    for (var i = 0; i < m; i++) {
      if (board[i][n - 1] == 'O') {
        surroundedRegionBfs(board, i, n - 1, directions, m, n);
      }
    }
    for (var i = 0; i < m; i++) {
      for (var j = 0; j < n; j++) {
        if (board[i][j] == 'V') {
          board[i][j] = 'O';
        } else if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }
  }

  private static void surroundedRegionBfs(
      char[][] grid, int i, int j, int[][] directions, int m, int n) {
    grid[i][j] = 'V';
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {i, j});
    while (!queue.isEmpty()) {
      var cur = queue.poll();
      for (var direction : directions) {
        var nextRow = direction[0] + cur[0];
        var nextCol = direction[1] + cur[1];
        if (nextRow < 0
            || nextRow >= m
            || nextCol < 0
            || nextCol >= n
            || grid[nextRow][nextCol] != 'O') {
          continue;
        }
        grid[nextRow][nextCol] = 'V';
        queue.offer(new int[] {nextRow, nextCol});
      }
    }
  }

  /** https://leetcode.com/problems/number-of-enclaves/ */
  public static int numEnclaves(int[][] grid) {
    var m = grid.length;
    var n = grid[0].length;
    // Explore all 4 directions, bottom, top, right, left
    var directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // Top row
    for (var j = 0; j < n; j++) {
      if (grid[0][j] == 1) {
        numEnclavesBfs(grid, 0, j, directions, m, n);
      }
    }
    // bottom row
    for (var j = 0; j < n; j++) {
      if (grid[m - 1][j] == 1) {
        numEnclavesBfs(grid, m - 1, j, directions, m, n);
      }
    }
    // left column
    for (var i = 0; i < m; i++) {
      if (grid[i][0] == 1) {
        numEnclavesBfs(grid, i, 0, directions, m, n);
      }
    }
    // right column
    for (var i = 0; i < m; i++) {
      if (grid[i][n - 1] == 1) {
        numEnclavesBfs(grid, i, n - 1, directions, m, n);
      }
    }
    var count = 0;
    for (var i = 0; i < m; i++) {
      for (var j = 0; j < n; j++) {
        if (grid[i][j] == -1) {
          grid[i][j] = 1;
        } else if (grid[i][j] == 1) {
          count++;
        }
      }
    }
    return count;
  }

  private static void numEnclavesBfs(int[][] grid, int i, int j, int[][] directions, int m, int n) {
    // Mark as -1 to mark boundary
    grid[i][j] = -1;
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {i, j});
    while (!queue.isEmpty()) {
      var cur = queue.poll();
      for (var direction : directions) {
        var nextRow = direction[0] + cur[0];
        var nextCol = direction[1] + cur[1];
        if (nextRow < 0
            || nextRow >= m
            || nextCol < 0
            || nextCol >= n
            || grid[nextRow][nextCol] != 1) {
          continue;
        }
        grid[nextRow][nextCol] = -1;
        queue.offer(new int[] {nextRow, nextCol});
      }
    }
  }

  /** https://leetcode.com/problems/is-graph-bipartite/description/ */
  public boolean isBipartite(int[][] graph) {
    var n = graph.length;
    var colour = new int[n];

    for (var node = 0; node < n; node++) {
      if (colour[node] != 0) {
        continue;
      }

      Queue<Integer> q = new ArrayDeque<>();
      q.add(node);
      colour[node] = 1;

      while (!q.isEmpty()) {
        int cur = q.poll();

        for (var ne : graph[cur]) {
          if (colour[ne] == 0) {
            colour[ne] = -colour[cur];
            q.add(ne);
          } else if (colour[ne] != -colour[cur]) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static boolean isBipartiteDfs(int[][] graph) {
    var n = graph.length;
    var colour = new int[n];
    Arrays.fill(colour, -1);
    for (var i = 0; i < n; i++) {
      if (colour[i] == -1) {
        if (!isBipartiteDfsRecur(graph, colour, i, 0)) return false;
      }
    }
    return true;
  }

  public static boolean isBipartiteDfsRecur(int[][] graph, int[] colour, int node, int color) {
    colour[node] = color;
    for (var ne : graph[node]) {
      if (colour[ne] == -1) {
        if (!isBipartiteDfsRecur(graph, colour, ne, 1 - color)) {
          return false;
        }
      } else if (colour[ne] == color) {
        return false;
      }
    }
    return true;
  }

  /** https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1 */
  public boolean isCycle(int V, int[][] edges) {
    // can also be done using kahn algo, if topo seq size == V, then no cycle, else there is a cycle
    List<List<Integer>> adjList = new ArrayList<>();
    for (var i = 0; i < V; i++) {
      adjList.add(new ArrayList<>());
    }
    for (var edge : edges) {
      adjList.get(edge[0]).add(edge[1]);
      adjList.get(edge[1]).add(edge[0]);
    }
    var visited = new boolean[V];
    for (var i = 0; i < V; i++) {
      if (visited[i]) {
        continue;
      }
      Queue<int[]> q = new LinkedList<>(); // node, parent
      q.add(new int[] {i, -1});
      visited[i] = true;
      while (!q.isEmpty()) {
        var poll = q.poll();
        var parent = poll[1];
        for (var vertex : adjList.get(poll[0])) {
          if (vertex != parent && visited[vertex]) {
            return true;
          } else if (!visited[vertex]) {
            q.offer(new int[] {vertex, poll[0]});
            visited[vertex] = true;
          }
        }
      }
    }
    return false;
  }

  /** https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1 */
  public boolean isCyclic(int V, int[][] edges) {
    List<List<Integer>> adjList = new ArrayList<>();
    for (var i = 0; i < V; i++) {
      adjList.add(new ArrayList<>());
    }
    for (var edge : edges) {
      adjList.get(edge[0]).add(edge[1]);
    }

    var visited = new boolean[V];
    var pathVisited = new boolean[V];

    for (var i = 0; i < V; i++) {
      if (!visited[i] && dfs(i, adjList, visited, pathVisited)) {
        return true;
      }
    }
    return false;
  }

  private boolean dfs(
      int node, List<List<Integer>> adjList, boolean[] visited, boolean[] pathVisited) {
    visited[node] = true;
    pathVisited[node] = true;

    for (int neighbor : adjList.get(node)) {
      if (!visited[neighbor] && dfs(neighbor, adjList, visited, pathVisited)) {
        return true;
      } else if (pathVisited[neighbor]) {
        return true;
      }
    }

    pathVisited[node] = false; // backtrack
    return false;
  }

  /** https://leetcode.com/problems/find-eventual-safe-states */
  public static List<Integer> eventualSafeNodes(int[][] adjList) {
    var visited = new boolean[adjList.length];
    var pathVisited = new boolean[adjList.length];
    List<Integer> safeNodes = new ArrayList<>();
    for (var i = 0; i < adjList.length; i++) {
      if (!visited[i]) {
        eventualSafeNodesDfsHasCycle(adjList, visited, pathVisited, i, safeNodes);
      }
    }
    return safeNodes.stream().sorted().toList();
  }

  private static boolean eventualSafeNodesDfsHasCycle(
      int[][] adjList,
      boolean[] visited,
      boolean[] pathVisited,
      int node,
      List<Integer> safeNodes) {
    visited[node] = true;
    pathVisited[node] = true;

    var neighbours = adjList[node];
    for (var neighbour : neighbours) {
      if (!visited[neighbour]
          && eventualSafeNodesDfsHasCycle(adjList, visited, pathVisited, neighbour, safeNodes)) {
        return true;
      } else if (pathVisited[neighbour]) {
        return true;
      }
    }
    safeNodes.add(node);
    pathVisited[node] = false; // backtrack
    return false;
  }

  /** https://www.geeksforgeeks.org/problems/topological-sort/1 */
  public static ArrayList<Integer> topoSort(int V, int[][] edges) {
    var visited = new boolean[V];
    List<List<Integer>> adjList = new ArrayList<>();

    for (var i = 0; i < V; i++) {
      adjList.add(new ArrayList<>());
    }

    for (var edge : edges) {
      adjList.get(edge[0]).add(edge[1]);
    }

    Deque<Integer> stack = new LinkedList<>();
    for (var i = 0; i < V; i++) {
      if (!visited[i]) {
        topoSortDfs(adjList, visited, i, stack);
      }
    }
    var res = new ArrayList<Integer>();
    while (!stack.isEmpty()) {
      res.add(stack.pop());
    }
    return res;
  }

  static void topoSortDfs(
      List<List<Integer>> adjList, boolean[] visited, int node, Deque<Integer> stack) {
    visited[node] = true;

    for (var n : adjList.get(node)) {
      if (!visited[n]) {
        topoSortDfs(adjList, visited, n, stack);
      }
    }
    stack.push(node);
  }

  record Node(int vt, int wt) {}

  public int[] shortestPath(int V, int E, int[][] edges) {
    // step 1 create Adj list:
    var adj = new ArrayList<List<Node>>();
    for (var i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }

    // convert 2D array into adj list
    for (int[] edge : edges) {
      var v1 = edge[0];
      var v2 = edge[1];
      var wt = edge[2];

      adj.get(v1).add(new Node(v2, wt));
    }

    // apply topo sort get the ordered stack.
    var st = new Stack<Integer>();
    var vis = new int[V];
    for (var i = 0; i < V; i++) {
      if (vis[i] == 0) {
        topoSort(st, adj, i, vis);
      }
    }

    // create the answer array to store the distance;
    var ans = new int[V];
    Arrays.fill(ans, Integer.MAX_VALUE);

    ans[0] = 0;

    while (!st.isEmpty()) {
      int t = st.pop();
      var temp = adj.get(t);
      if (ans[t] != Integer.MAX_VALUE) { // donot proceed if it is already at max distance.
        for (Node tm : temp) {
          if (ans[tm.vt] > ans[t] + tm.wt) {
            ans[tm.vt] = ans[t] + tm.wt;
          }
        }
      }
    }

    for (var i = 0; i < V; i++) {
      if (ans[i] == Integer.MAX_VALUE) {
        ans[i] = -1;
      }
    }

    return ans;
  }

  private void topoSort(Stack<Integer> st, List<List<Node>> adj, int v, int[] vis) {
    vis[v] = 1;
    var al = adj.get(v);
    for (var temp : al) {
      if (vis[temp.vt] == 0) topoSort(st, adj, temp.vt, vis);
    }
    st.add(v);
  }

  /** Kahn's algo topo sort */
  public static ArrayList<Integer> topoSortBfs(int V, int[][] edges) {
    List<List<Integer>> adjList = new ArrayList<>();

    for (var i = 0; i < V; i++) {
      adjList.add(new ArrayList<>());
    }

    var indegree = new int[V];
    for (var edge : edges) {
      adjList.get(edge[0]).add(edge[1]);
      indegree[edge[1]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    var res = new ArrayList<Integer>();
    for (var i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    while (!queue.isEmpty()) {
      var poll = queue.poll();
      res.add(poll);
      for (var nextNode : adjList.get(poll)) {
        indegree[nextNode]--;
        if (indegree[nextNode] == 0) {
          queue.offer(nextNode);
        }
      }
    }
    return res;
  }

  /**
   * https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
   */
  // Function to find the shortest path from a source node to all other nodes
  public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
    Queue<int[]> q = new LinkedList<>(); // node,distance
    int[] distances = new int[adj.size()];
    Arrays.fill(distances, -1);
    q.offer(new int[] {src, 0});
    distances[src] = 0;
    while (!q.isEmpty()) {
      int[] poll = q.poll();
      int node = poll[0];
      int distance = poll[1];
      for (Integer next : adj.get(node)) {
        if (distances[next] != -1) {
          // already visited
          continue;
        }
        q.offer(new int[] {next, 1 + distance});
        if (distances[next] == -1 || distances[next] > (1 + distance)) {
          distances[next] = 1 + distance;
        }
      }
    }
    return distances;
  }

  /**
   * https://leetcode.com/problems/word-ladder/description/
   *
   * <p>Example 1:
   *
   * <p>Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
   * Output: 5 Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog"
   * -> cog", which is 5 words long.
   *
   * <p>Example 2:
   *
   * <p>Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
   * Output: 0 Explanation: The endWord "cog" is not in wordList, therefore there is no valid
   * transformation sequence.
   */
  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    var words = new HashSet<>(wordList);
    if (!words.contains(endWord)) {
      return 0;
    }
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    int level = 1; // Level starts from 1 because we count the `beginWord`
    while (!queue.isEmpty()) {
      int size = queue.size(); // Process all words at the current level
      for (int i = 0; i < size; i++) {
        var word = queue.poll();
        for (var j = 0; j < word.length(); j++) {
          var sb = new StringBuilder(word);
          char originalChar = word.charAt(j);
          for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch == originalChar) continue; // Skip the original character
            sb.setCharAt(j, ch);
            var newWord = sb.toString();
            if (newWord.equals(endWord)) {
              return level + 1; // Return steps including this last transformation
            }
            if (words.contains(newWord)) {
              queue.offer(newWord);
              words.remove(newWord); // Remove to avoid re-visiting
            }
          }
        }
      }
      level++; // Move to the next BFS level
    }
    return 0;
  }

  public static int ladderLengthBiDirectionalBfs(
      String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) return 0;

    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();

    beginSet.add(beginWord);
    endSet.add(endWord);
    int level = 1;

    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
      // Always expand the smaller set to optimize
      if (beginSet.size() > endSet.size()) {
        Set<String> temp = beginSet;
        beginSet = endSet;
        endSet = temp;
      }

      Set<String> nextLevel = new HashSet<>();
      for (String word : beginSet) {
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < wordChars.length; i++) {
          char originalChar = wordChars[i];
          for (char c = 'a'; c <= 'z'; c++) {
            if (c == originalChar) continue;
            wordChars[i] = c;
            String newWord = new String(wordChars);

            if (endSet.contains(newWord)) {
              return level + 1; // Found the connection point
            }

            if (wordSet.contains(newWord)) {
              nextLevel.add(newWord);
              wordSet.remove(newWord); // Avoid re-visiting
            }
          }
          wordChars[i] = originalChar;
        }
      }

      beginSet = nextLevel;
      level++;
    }

    return 0;
  }

  public static List<List<String>> findLadder(
      String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) return List.of();

    List<List<String>> ans = new ArrayList<>();
    Queue<List<String>> queue = new LinkedList<>();
    queue.offer(List.of(beginWord));
    boolean found = false;

    while (!queue.isEmpty() && !found) {
      int size = queue.size();
      Set<String> toRemove = new HashSet<>();

      for (int i = 0; i < size; i++) {
        List<String> path = queue.poll();
        String lastWord = path.get(path.size() - 1);

        for (int j = 0; j < lastWord.length(); j++) {
          char[] wordChars = lastWord.toCharArray();
          char originalChar = wordChars[j];

          for (char c = 'a'; c <= 'z'; c++) {
            if (c == originalChar) continue;

            wordChars[j] = c;
            String newWord = new String(wordChars);

            if (newWord.equals(endWord)) {
              List<String> newPath = new ArrayList<>(path);
              newPath.add(endWord);
              ans.add(newPath);
              found = true;
            } else if (wordSet.contains(newWord)) {
              toRemove.add(newWord);
              List<String> newPath = new ArrayList<>(path);
              newPath.add(newWord);
              queue.offer(newPath);
            }
          }
        }
      }

      // Remove visited words *after* the level is complete
      wordSet.removeAll(toRemove);
    }

    return ans;
  }

  /** https://leetcode.com/problems/shortest-path-in-binary-matrix/description/ */
  public static int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] != 0) {
      return -1;
    }
    int m = grid.length, n = grid[0].length;
    if (grid[m - 1][n - 1] != 0) {
      return -1;
    }
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {0, 0, 1}); // (x,y,distance)
    // all 8 dirs allowed
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    while (!queue.isEmpty()) {
      var cur = queue.poll();
      int r = cur[0], c = cur[1];
      if (r == m - 1 && c == n - 1) {
        return cur[2];
      }
      for (int[] dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0
            && nr < m
            && nc >= 0
            && nc < n
            // Only 0 are allowed
            && grid[nr][nc] == 0) {
          grid[nr][nc] = 1;
          queue.offer(new int[] {nr, nc, cur[2] + 1});
        }
      }
    }
    return -1;
  }

  /** https://www.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1 */
  static int shortestPath(int[][] grid, int[] source, int[] destination) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {source[0], source[1], 0}); // (x,y,distance)
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while (!queue.isEmpty()) {
      var cur = queue.poll();
      int r = cur[0], c = cur[1];
      if (r == destination[0] && c == destination[1]) {
        return cur[2];
      }
      for (int[] dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0
            && nr < grid.length
            && nc >= 0
            && nc < grid[0].length
            // Only 0 are allowed
            && grid[nr][nc] == 1) {
          grid[nr][nc] = 0;
          queue.offer(new int[] {nr, nc, cur[2] + 1});
        }
      }
    }
    return -1;
  }

  /** https://leetcode.com/problems/path-with-minimum-effort/description/ */
  public int minimumEffortPath(int[][] heights) {
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    queue.offer(new int[] {0, 0, 0}); // (x,y,effort)
    int m = heights.length, n = heights[0].length;
    int[][] effort = new int[m][n];
    for (int[] ints : effort) {
      Arrays.fill(ints, Integer.MAX_VALUE);
    }
    effort[0][0] = 0;
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while (!queue.isEmpty()) {
      var cur = queue.poll();
      int r = cur[0], c = cur[1];
      if (r == m - 1 && c == n - 1) {
        return cur[2];
      }
      for (int[] dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
          int nextEffort = Math.max(cur[2], Math.abs(heights[r][c] - heights[nr][nc]));
          if (nextEffort < effort[nr][nc]) {
            effort[nr][nc] = nextEffort;
            queue.offer(new int[] {nr, nc, effort[nr][nc]});
          }
        }
      }
    }
    return -1;
  }

  /** https://leetcode.com/problems/cheapest-flights-within-k-stops/ */
  public int findCheapestPrice(int numCities, int[][] flights, int src, int dst, int k) {
    List<List<int[]>> adjList = new ArrayList<>(); // [adjNode,cost]
    for (int i = 0; i < numCities; i++) {
      adjList.add(new ArrayList<>());
    }

    for (int[] flight : flights) {
      adjList.get(flight[0]).add(new int[] {flight[1], flight[2]});
    }

    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    queue.offer(new int[] {src, 0, 0}); // (dest,price, stop)
    int[] prices = new int[numCities];
    Arrays.fill(prices, Integer.MAX_VALUE);
    prices[src] = 0;
    while (!queue.isEmpty()) {
      int source = queue.peek()[0];
      int price = queue.peek()[1];
      int stop = queue.peek()[2];
      queue.poll();
      if (stop > k) continue;

      for (int[] adjNodes : adjList.get(source)) {
        int adjNode = adjNodes[0];
        int adjNodePrice = adjNodes[1];
        if (price + adjNodePrice < prices[adjNode]) {
          prices[adjNode] = price + adjNodePrice;
          queue.offer(new int[] {adjNode, prices[adjNode], stop + 1}); // (dest,price, stop)
        }
      }
    }

    return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
  }

  /** https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/ */
  public static int countPaths(int n, int[][] roads) {
    int MOD = 1_000_000_007;

    List<List<int[]>> adjList = new ArrayList<>(n);

    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }

    for (int[] road : roads) {
      adjList.get(road[0]).add(new int[] {road[1], road[2]});
      adjList.get(road[1]).add(new int[] {road[0], road[2]});
    }

    long[] distance = new long[n];
    Arrays.fill(distance, Long.MAX_VALUE);
    distance[0] = 0;

    int[] ways = new int[n];
    ways[0] = 1;

    PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
    queue.offer(new long[] {0, 0}); // [index, distance].

    while (!queue.isEmpty()) {
      int node = (int) queue.peek()[0];
      long wt = queue.peek()[1];
      queue.poll();
      for (int[] adjNodes : adjList.get(node)) {
        int adjNode = adjNodes[0];
        int adjWt = adjNodes[1];
        long newDistance = (wt + adjWt);
        if (newDistance < distance[adjNode]) {
          distance[adjNode] = newDistance;
          ways[adjNode] = ways[node];
          queue.offer(new long[] {adjNode, adjWt + wt});
        } else if (newDistance == distance[adjNode]) {
          ways[adjNode] = (ways[adjNode] + ways[node]) % MOD;
        }
      }
    }
    return ways[n - 1];
  }

  /**
   * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
   *
   * <p>Floyd–Warshall algorithm
   */
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] dist = new int[n][n];
    for (int[] ints : dist) {
      Arrays.fill(ints, 10001); // as distance threshold is 10e4
    }
    // populate adjacency matrix
    for (int[] edge : edges) {
      dist[edge[0]][edge[1]] = dist[edge[1]][edge[0]] = edge[2];
    }
    // path to self is always 0
    for (int i = 0; i < n; i++) {
      dist[i][i] = 0;
    }

    for (int via = 0; via < n; via++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
        }
      }
    }

    int countMax = n + 1, cityNo = -1;
    for (int i = 0; i < n; i++) {
      int count = 0;
      for (int j = 0; j < n; j++) {
        if (dist[i][j] <= distanceThreshold) {
          count++;
        }
      }
      if (countMax >= count) {
        countMax = count;
        cityNo = i;
      }
    }

    return cityNo;
  }
}
