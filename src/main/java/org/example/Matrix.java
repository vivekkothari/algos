package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Matrix {

  public static void main(String[] args) {
    sortMatrix(new int[][] {{1, 7, 3}, {9, 8, 2}, {4, 5, 6}});
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
}
