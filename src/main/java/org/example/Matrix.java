package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Matrix {

  public static void main(String[] args) {
    //    sortMatrix(new int[][] {{1, 7, 3}, {9, 8, 2}, {4, 5, 6}});

    //    networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);

    //    System.out.println(
    //        shortestPathToTarget(new int[][] {{1, 3, 1}, {1, 2, 4}, {3, 4, 1}, {4, 2, 1}}, 4, 1,
    // 2));
    generateMatrix(3);
  }

  /**
   * https://leetcode.com/problems/sort-matrix-by-diagonals/description/
   *
   * <pre>
   *   You are given an n x n square matrix of integers grid. Return the matrix such that:
   *
   * The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
   * The diagonals in the top-right triangle are sorted in non-decreasing order.
   *
   * Example 1:
   *
   * Input: grid = [[1,7,3],[9,8,2],[4,5,6]]
   *
   * Output: [[8,2,3],[9,6,7],[4,5,1]]
   *
   * Explanation:
   *
   * <img src="https://assets.leetcode.com/uploads/2024/12/29/4052example1drawio.png"></img>
   * The diagonals with a black arrow (bottom-left triangle) should be sorted in non-increasing order:
   *
   * [1, 8, 6] becomes [8, 6, 1].
   * [9, 5] and [4] remain unchanged.
   * The diagonals with a blue arrow (top-right triangle) should be sorted in non-decreasing order:
   *
   * [7, 2] becomes [2, 7].
   * [3] remains unchanged.
   * </pre>
   */
  public static int[][] sortMatrix(int[][] grid) {
    var n = grid.length;
    Map<Integer, PriorityQueue<Integer>> diagonals = new HashMap<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        var key = i - j;
        diagonals
            .computeIfAbsent(
                key,
                k ->
                    new PriorityQueue<>(
                        key < 0
                            ? Comparator.<Integer>naturalOrder()
                            : Comparator.<Integer>reverseOrder()))
            .offer(grid[i][j]);
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        var list = diagonals.get(i - j);
        grid[i][j] = list.remove();
      }
    }
    return grid;
  }

  /**
   * https://leetcode.com/problems/network-delay-time/solutions/6391081/dijkstra-s-algorithm-easy-to-understand-explain-with-all-other-approaches-java/
   */
  public static int networkDelayTime(int[][] times, int n, int k) {
    // Create adjacency list for graph representation
    Map<Integer, List<int[]>> edges = new HashMap<>();
    for (int[] time : times) {
      edges.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[] {time[1], time[2]});
    }

    // Priority Queue (Min-Heap) for Dijkstra's Algorithm
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.offer(new int[] {k, 0}); // Start with the source node

    Map<Integer, Integer> visited = new HashMap<>();

    while (!pq.isEmpty()) {
      int[] curr = pq.poll(); // Get the node with the smallest time
      int node = curr[0], time = curr[1];

      if (visited.containsKey(node)) continue; // Skip if already visited

      visited.put(node, time);

      for (int[] neighbor : edges.get(node)) {
        int nextNode = neighbor[0], nextTime = neighbor[1];
        if (!visited.containsKey(nextNode)) {
          pq.offer(new int[] {nextNode, time + nextTime});
        }
      }
    }
    if (visited.size() != n) return -1; // If not all nodes are reached, return -1
    return Collections.max(visited.values()); // Return the maximum time taken
  }

  /** Djitras's algo, shortest path to target. */
  public static int shortestPathToTarget(int[][] times, int n, int startNode, int targetNode) {
    // Create adjacency list for graph representation
    Map<Integer, List<int[]>> edges = new HashMap<>();
    for (int[] time : times) {
      edges.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[] {time[1], time[2]});
    }

    // Priority Queue (Min-Heap) for Dijkstra's Algorithm
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.offer(new int[] {startNode, 0}); // Start with the source node

    Map<Integer, Integer> visited = new HashMap<>();

    while (!pq.isEmpty()) {
      int[] curr = pq.poll(); // Get the node with the smallest time
      int node = curr[0], time = curr[1];

      if (visited.containsKey(node) && node != targetNode) continue; // Skip if already visited

      if (node == targetNode) return time;

      visited.put(node, time);

      for (int[] neighbor : edges.get(node)) {
        int nextNode = neighbor[0], nextTime = neighbor[1];
        if (!visited.containsKey(nextNode)) {
          pq.offer(new int[] {nextNode, time + nextTime});
        }
      }
    }
    return -1;
  }

  /**
   * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in
   * spiral order. Input: n = 3 Output: [[1,2,3],[8,9,4],[7,6,5]]
   */
  public static int[][] generateMatrix(int n) {
    int[][] res = new int[n][n];
    var value = 1;
    int minCol = 0;
    int maxCol = n - 1;
    int minRow = 0;
    int maxRow = n - 1;
    while (value <= n * n) {
      for (int i = minCol; i <= maxCol; i++) {
        res[minRow][i] = value++;
      }
      for (int i = minRow + 1; i <= maxRow; i++) {
        res[i][maxCol] = value++;
      }
      for (int i = maxCol - 1; i >= minCol; i--) {
        res[maxRow][i] = value++;
      }
      for (int i = maxRow - 1; i > minRow; i--) {
        res[i][minCol] = value++;
      }
      minRow++;
      minCol++;
      maxRow--;
      maxCol--;
    }
    return res;
  }

  /** https://leetcode.com/problems/two-city-scheduling/ */
  public int twoCitySchedCost(int[][] costs) {
    int N = costs.length / 2;
    int[] refund = new int[N * 2];
    int minCost = 0, index = 0;
    for (int[] cost : costs) {
      refund[index++] = cost[1] - cost[0];
      minCost += cost[0];
    }
    Arrays.sort(refund);
    for (int i = 0; i < N; i++) {
      minCost += refund[i];
    }
    return minCost;
  }
}
