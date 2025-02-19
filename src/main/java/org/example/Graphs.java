package org.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
}
