package org.example;

import java.util.*;
import java.util.LinkedList;

class Graphs {

  public static void main(String[] args) {
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
}
