package org.example;

import java.util.*;
import java.util.LinkedList;

class Graphs {

  public static void main(String[] args) {
    countIslands(new char[][] {{'W', 'L'}, {'L', 'W'}, {'L', 'L'}, {'L', 'W'}});
    System.out.println(
        bfs(5, List.of(List.of(1, 2, 3), List.of(), List.of(4), List.of(), List.of())));
    System.out.println(
        dfs(5, List.of(List.of(1, 2, 3), List.of(), List.of(4), List.of(), List.of())));
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

    bfs(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 0, 0);
  }

  public List<Integer> mergeSequences(List<List<Integer>> sequences) {
    Map<Integer, Set<Integer>> adjList = new HashMap<>();
    Map<Integer, Integer> indegree = new HashMap<>();

    // Build graph and calculate indegree
    for (List<Integer> seq : sequences) {
      for (int i = 1; i < seq.size(); i++) {
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
    int rows = graph.length;
    int cols = graph[0].length;
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

  /** https://leetcode.com/problems/course-schedule/ */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
    for (int i = 0; i < numCourses; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (var edge : prerequisites) {
      adjacencyList.get(edge[1]).add(edge[0]);
    }

    Set<Integer> visited = new HashSet<>();
    Set<Integer> onPath = new HashSet<>();

    for (int i = 0; i < numCourses; i++) {
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
    for (int i = 0; i < numCourses; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (var edge : prerequisites) {
      adjacencyList.get(edge[1]).add(edge[0]);
    }

    Set<Integer> visited = new HashSet<>();
    Set<Integer> onPath = new HashSet<>();
    List<Integer> result = new LinkedList<>();

    for (int i = 0; i < numCourses; i++) {
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
    boolean[] visited = new boolean[vertices];
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);
    visited[0] = true;
    List<Integer> bfs = new ArrayList<>();
    while (!queue.isEmpty()) {
      Integer poll = queue.poll();
      bfs.add(poll);
      for (Integer i : adjList.get(poll)) {
        if (!visited[i]) {
          queue.offer(i);
          visited[i] = true;
        }
      }
    }
    return bfs;
  }

  static List<Integer> dfs(int vertices, List<List<Integer>> adjList) {
    boolean[] visited = new boolean[vertices];
    Deque<Integer> stack = new LinkedList<>();
    stack.push(0);
    visited[0] = true;
    List<Integer> dfs = new ArrayList<>();
    while (!stack.isEmpty()) {
      Integer pop = stack.pop();
      dfs.add(pop);
      for (Integer i : adjList.get(pop)) {
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
    boolean[][] visited = new boolean[n][m];

    int num = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
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
    for (int[] dir : allDirections) {
      int ex = sx + dir[0];
      int ey = sy + dir[1];
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
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (mat[i][j] == 0) {
          bfs.offer(new int[] {i, j});
        } else {
          mat[i][j] = -1;
        }
      }
    }
    int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    while (!bfs.isEmpty()) {
      int[] cell = bfs.poll();
      for (int[] dir : dirs) {
        int nr = cell[0] + dir[0];
        int nc = cell[1] + dir[1];
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
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // Top row
    for (int j = 0; j < n; j++) {
      if (board[0][j] == 'O') {
        surroundedRegionBfs(board, 0, j, directions, m, n);
      }
    }
    // bottom row
    for (int j = 0; j < n; j++) {
      if (board[m - 1][j] == 'O') {
        surroundedRegionBfs(board, m - 1, j, directions, m, n);
      }
    }
    // left column
    for (int i = 0; i < m; i++) {
      if (board[i][0] == 'O') {
        surroundedRegionBfs(board, i, 0, directions, m, n);
      }
    }
    // right column
    for (int i = 0; i < m; i++) {
      if (board[i][n - 1] == 'O') {
        surroundedRegionBfs(board, i, n - 1, directions, m, n);
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
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
      int[] cur = queue.poll();
      for (int[] direction : directions) {
        int nextRow = direction[0] + cur[0];
        int nextCol = direction[1] + cur[1];
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
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // Top row
    for (int j = 0; j < n; j++) {
      if (grid[0][j] == 1) {
        numEnclavesBfs(grid, 0, j, directions, m, n);
      }
    }
    // bottom row
    for (int j = 0; j < n; j++) {
      if (grid[m - 1][j] == 1) {
        numEnclavesBfs(grid, m - 1, j, directions, m, n);
      }
    }
    // left column
    for (int i = 0; i < m; i++) {
      if (grid[i][0] == 1) {
        numEnclavesBfs(grid, i, 0, directions, m, n);
      }
    }
    // right column
    for (int i = 0; i < m; i++) {
      if (grid[i][n - 1] == 1) {
        numEnclavesBfs(grid, i, n - 1, directions, m, n);
      }
    }
    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
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
      int[] cur = queue.poll();
      for (int[] direction : directions) {
        int nextRow = direction[0] + cur[0];
        int nextCol = direction[1] + cur[1];
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
    int n = graph.length;
    int[] colour = new int[n];

    for (int node = 0; node < n; node++) {
      if (colour[node] != 0) {
        continue;
      }

      Queue<Integer> q = new ArrayDeque<>();
      q.add(node);
      colour[node] = 1;

      while (!q.isEmpty()) {
        int cur = q.poll();

        for (int ne : graph[cur]) {
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
}
