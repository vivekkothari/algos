package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Matrix {

  public static void main(String[] args) {
    System.out.println(longestIncreasingPath(new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
    System.out.println(getRow(4));
    //    maxFarmLand(
    //        new char[][] {
    //          {'1', '0', '1', '0', '0'},
    //          {'1', '0', '1', '1', '1'},
    //          {'1', '1', '1', '1', '1'},
    //          {'1', '0', '0', '1', '0'}
    //        });
    //    rotate(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    //    System.out.println(
    //        searchMatrixOptimised(new int[][] {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}},
    // 3));
    //    System.out.println(
    //        searchMatrixOptimised(new int[][] {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}},
    // 13));
    //    System.out.println(
    //        searchMatrixOptimised(new int[][] {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}},
    // 10));
    //
    //    System.out.println(
    //        searchMatrix(new int[][] {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}}, 3));
    //    System.out.println(
    //        searchMatrix(new int[][] {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}}, 13));
    //    System.out.println(
    //        searchMatrix(new int[][] {{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}}, 10));
    //    generateRecur(5);
    //    System.out.println(
    //        Arrays.toString(findDiagonalOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    //    findCircleNum(new int[][] {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}});
    //    sortMatrix(new int[][] {{1, 7, 3}, {9, 8, 2}, {4, 5, 6}});

    //    networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);

    //    System.out.println(
    //        shortestPathToTarget(new int[][] {{1, 3, 1}, {1, 2, 4}, {3, 4, 1}, {4, 2, 1}}, 4, 1,
    // 2));
    //    var ints = generateMatrix(3);
    //    spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
    //    spiralMatrixIII(5, 6, 1, 4);
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

  /** https://leetcode.com/problems/sort-the-matrix-diagonally/ */
  public static int[][] diagonalSort(int[][] mat) {
    var m = mat.length;
    var n = mat[0].length;
    Map<Integer, PriorityQueue<Integer>> diagonals = new HashMap<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        diagonals.computeIfAbsent(i - j, k -> new PriorityQueue<>()).offer(mat[i][j]);
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        mat[i][j] = diagonals.get(i - j).remove();
      }
    }
    return mat;
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

  /**
   *
   *
   * <pre>https://leetcode.com/problems/number-of-provinces/description/</pre>
   *
   * There are n cities. Some of them are connected, while some are not. If city a is connected
   * directly with city b, and city b is connected directly with city c, then city a is connected
   * indirectly with city c.
   *
   * <p>A province is a group of directly or indirectly connected cities and no other cities outside
   * of the group.
   *
   * <p>You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and
   * the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
   *
   * <p>Return the total number of provinces.
   *
   * <pre>
   * Constraints:
   * 1 <= n <= 200
   * n == isConnected.length
   * n == isConnected[i].length
   * isConnected[i][j] is 1 or 0.
   * isConnected[i][i] == 1
   * isConnected[i][j] == isConnected[j][i]
   * </pre>
   *
   * <p>Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]] Output: 2
   *
   * <pre>
   *   1 1 0
   *   1 1 0
   *   0 0 1
   *   return 2
   *
   *   1 0 0 1
   *   0 1 1 0
   *   0 1 1 0
   *   1 0 0 1
   *   return 3
   * </pre>
   */
  public static int findCircleNum(int[][] isConnected) {
    var n = isConnected.length;
    var visit = new boolean[n];
    int numOfProvince = 0;
    for (int i = 0; i < n; i++) {
      if (!visit[i]) {
        numOfProvince++;
        findCircleNumDfs(i, visit, isConnected);
      }
    }
    return numOfProvince;
  }

  private static void findCircleNumDfs(int city, boolean[] visit, int[][] otherCities) {
    visit[city] = true;
    for (int i = 0; i < otherCities.length; i++) {
      if (otherCities[city][i] == 1 && !visit[i]) {
        findCircleNumDfs(i, visit, otherCities);
      }
    }
  }

  public static int findCircleNumDisjointSet(int[][] isConnected) {
    var n = isConnected.length;
    DisjointSet set = new DisjointSet(n);
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (isConnected[i][j] == 1) {
          set.unionBySize(i, j);
        }
      }
    }
    int sum = 0;
    for (int i = 0; i < n; i++) {
      if (set.find(i) == i) sum++;
    }
    return sum;
  }

  public static int[] findDiagonalOrder(int[][] mat) {
    var m = mat.length;
    var n = mat[0].length;
    Map<Integer, List<Integer>> map = new TreeMap<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        var list = map.computeIfAbsent(i + j, k -> new LinkedList<>());
        if ((i + j) % 2 == 0) {
          list.addFirst(mat[i][j]);
        } else {
          list.addLast(mat[i][j]);
        }
      }
    }
    return map.values().stream().flatMap(List::stream).mapToInt(i -> i).toArray();
  }

  /**
   * https://leetcode.com/problems/spiral-matrix/
   *
   * <pre>
   * Given an m x n matrix, return all elements of the matrix in spiral order.
   * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * Output: [1,2,3,6,9,8,7,4,5]
   * <img src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg"></img>
   * </pre>
   */
  public static List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    List<Integer> res = new ArrayList<>();
    int minCol = 0;
    int maxCol = n - 1;
    int minRow = 0;
    int maxRow = m - 1;
    var total = m * n;
    while (res.size() < total) {
      for (int i = minCol; i <= maxCol; i++) {
        res.add(matrix[minRow][i]);
      }
      minRow++;
      for (int i = minRow; i <= maxRow; i++) {
        res.add(matrix[i][maxCol]);
      }
      maxCol--;
      if (res.size() < total) {
        for (int i = maxCol; i >= minCol; i--) {
          res.add(matrix[maxRow][i]);
        }
        maxRow--;
      }
      if (res.size() < total) {
        for (int i = maxRow; i >= minRow; i--) {
          res.add(matrix[i][minCol]);
        }
        minCol++;
      }
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/spiral-matrix-iii/description/
   *
   * <pre>
   *   You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
   *
   * You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.
   *
   * Return an array of coordinates representing the positions of the grid in the order you visited them.
   * <img src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/08/24/example_2.png"></img>
   *
   * Input: rows = 5, cols = 6, rStart = 1, cStart = 4
   * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],
   * [1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],
   * [1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
   * </pre>
   */
  public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int numSteps = 1;
    int totalCells = rows * cols;
    List<int[]> result = new ArrayList<>();
    int r = rStart, c = cStart;
    int d = 0;

    while (result.size() < totalCells) {
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < numSteps; j++) {
          if (0 <= r && r < rows && 0 <= c && c < cols) {
            result.add(new int[] {r, c});
          }
          if (result.size() == totalCells) {
            return result.toArray(int[][]::new);
          }
          r += directions[d][0];
          c += directions[d][1];
        }
        d = (d + 1) % 4;
      }
      numSteps++;
    }
    return result.toArray(int[][]::new);
  }

  /**
   * Pascals triangle. https://leetcode.com/problems/pascals-triangle/description/
   *
   * <p><img
   * src="https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif"></img>
   */
  public static List<List<Integer>> generate(int numRows) {
    if (numRows == 0) return List.of();
    List<List<Integer>> res = new ArrayList<>();
    res.add(List.of(1));
    if (numRows == 1) return res;
    res.add(List.of(1, 1));
    if (numRows == 2) return res;

    for (int i = 2; i < numRows; i++) {
      var lastRow = res.get(i - 1);
      var thisRowSize = lastRow.size() + 1;
      var temp = new ArrayList<Integer>();
      for (int j = 0, k = 0; j < thisRowSize && k + 1 < lastRow.size(); j++) {
        if (j == 0) {
          temp.add(1);
        } else {
          temp.add(lastRow.get(k) + lastRow.get(k + 1));
          k++;
        }
      }
      temp.add(1);
      res.add(temp);
    }
    return res;
  }

  public static List<Integer> getRow(int rowIndex) {
    List<Integer> row = new ArrayList<>();
    row.add(1);
    for (int i = 0; i < rowIndex; i++) {
      for (int j = row.size() - 1; j > 0; j--) {
        row.set(j, row.get(j - 1) + row.get(j));
      }
      row.add(1);
    }
    return row;
  }

  public static List<List<Integer>> generateRecur(int numRows) {
    if (numRows == 0) return new ArrayList<>();
    if (numRows == 1) {
      List<List<Integer>> result = new ArrayList<>();
      result.add(List.of(1));
      return result;
    }
    var prevRows = generateRecur(numRows - 1);
    var newRow = new ArrayList<Integer>();
    for (int i = 0; i < numRows; i++) {
      if (i == 0 || i == numRows - 1) {
        newRow.add(1);
      } else {
        // we do - 2 because of 2 recursive base cases
        var prevRow = prevRows.get(numRows - 2);
        newRow.add(i, prevRow.get(i - 1) + prevRow.get(i));
      }
    }
    prevRows.add(newRow);
    return prevRows;
  }

  /**
   * https://neetcode.io/problems/search-2d-matrix Input: matrix =
   * [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 10
   *
   * <p>https://leetcode.com/problems/search-a-2d-matrix/
   *
   * <p>Output: true <img
   * src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/7ca61f56-00d4-4fa0-26cf-56809028ac00/public"/>
   */
  public static boolean searchMatrix(int[][] matrix, int target) {
    // This is O(m * log(n))
    for (int[] ints : matrix) {
      var exists = Arrays.binarySearch(ints, target) >= 0;
      if (exists) return true;
    }
    return false;
  }

  public static boolean searchMatrixOptimised(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;
    int l = 0, r = m * n - 1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      var row = mid / n;
      var col = mid % n;
      var cell = matrix[row][col];
      if (target == cell) {
        return true;
      } else if (target < cell) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return false;
  }

  /** https://leetcode.com/problems/search-a-2d-matrix-ii/ */
  public boolean searchMatrixII(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;
    int i = m - 1, j = 0; // start from top right corner
    while (i >= 0 && j < n) {
      if (matrix[i][j] == target) {
        return true;
      }
      if (matrix[i][j] > target) {
        i--;
      } else {
        j++;
      }
    }
    return false;
  }

  public static void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        swap(matrix, i, j, j, i);
      }
    }
    for (int[] ints : matrix) {
      reverseArray(ints);
    }
  }

  static void reverseArray(int[] row) {
    int i = 0, j = row.length - 1;
    while (i < j) {
      var temp = row[i];
      row[i] = row[j];
      row[j] = temp;
      i++;
      j--;
    }
  }

  public static void rotateMatrix(ArrayList<ArrayList<Integer>> mat, int n, int m) {
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < m; j++) {
        int temp = mat.get(i).get(j);
        mat.get(i).set(j, mat.get(j).get(i));
        mat.get(j).set(i, temp);
      }
    }

    for (int i = 0; i < n; i++) {
      Collections.reverse(mat.get(i));
    }
  }

  static void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
    var temp = matrix[i1][j1];
    matrix[i1][j1] = matrix[i2][j2];
    matrix[i2][j2] = temp;
  }

  public static int maxFarmLand(char[][] land) {
    int m = land.length;
    int n = land[0].length;
    int[][] dp = new int[m][n];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < land.length; i++) {
      for (int j = 0; j < land[i].length; j++) {
        if (land[i][j] == '0') continue;
        int left = 0, right = 0, diag = 0;
        if (i > 0) left = dp[i - 1][j];
        if (j > 0) right = dp[i][j - 1];
        if (i > 0 && j > 0) diag = dp[i - 1][j - 1];
        dp[i][j] = Collections.min(List.of(left, right, diag)) + 1;
        max = Math.max(max, dp[i][j]);
      }
    }
    return max * max;
  }

  /**
   * https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/?envType=daily-question&envId=2025-03-02
   *
   * <pre>
   * Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
   * Output: [[1,6],[2,3],[3,2],[4,6]]
   * Explanation: The resulting array contains the following:
   * - id = 1, the value of this id is 2 + 4 = 6.
   * - id = 2, the value of this id is 3.
   * - id = 3, the value of this id is 2.
   * - id = 4, the value of this id is 5 + 1 = 6.
   * </pre>
   */
  public static int[][] mergeArrays(int[][] nums1, int[][] nums2) {
    List<int[]> res = new ArrayList<>();
    int l = 0, r = 0;
    while (l < nums1.length && r < nums2.length) {
      if (nums1[l][0] == nums2[r][0]) {
        res.add(new int[] {nums1[l][0], nums1[l][1] + nums2[r][1]});
        l++;
        r++;
      } else if (nums1[l][0] < nums2[r][0]) {
        res.add(new int[] {nums1[l][0], nums1[l][1]});
        l++;
      } else {
        res.add(new int[] {nums2[r][0], nums2[r][1]});
        r++;
      }
    }
    while (l < nums1.length) {
      res.add(new int[] {nums1[l][0], nums1[l][1]});
      l++;
    }
    while (r < nums2.length) {
      res.add(new int[] {nums2[r][0], nums2[r][1]});
      r++;
    }
    return res.toArray(int[][]::new);
  }

  /** https://leetcode.com/problems/longest-increasing-path-in-a-matrix/ */
  public static int longestIncreasingPath(int[][] matrix) {
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] dp = new int[m][n];
    int max = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        max = Math.max(max, longestIncreasingPath(matrix, dp, directions, i, j));
      }
    }
    return max;
  }

  public static int longestIncreasingPath(
      int[][] matrix, int[][] dp, int[][] directions, int i, int j) {
    if (dp[i][j] != 0) return dp[i][j];
    for (int[] dir : directions) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x >= 0
          && x < matrix.length
          && y >= 0
          && y < matrix[0].length
          && matrix[x][y] > matrix[i][j]) {
        dp[i][j] = Math.max(dp[i][j], longestIncreasingPath(matrix, dp, directions, x, y));
      }
    }
    return ++dp[i][j];
  }

  /** https://leetcode.com/problems/available-captures-for-rook/ */
  public int numRookCaptures(char[][] board) {
    int[] rookPos = findRookPos(board);
    int numPawns = 0;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right

    for (int[] dir : directions) {
      numPawns += countPawnsInDirection(board, rookPos, dir);
    }

    return numPawns;
  }

  private int countPawnsInDirection(char[][] board, int[] rookPos, int[] dir) {
    int x = rookPos[0] + dir[0];
    int y = rookPos[1] + dir[1];

    while (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
      if (board[x][y] == 'B') {
        break;
      }
      if (board[x][y] == 'p') {
        return 1;
      }
      x += dir[0];
      y += dir[1];
    }

    return 0;
  }

  private int[] findRookPos(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 'R') {
          return new int[] {i, j};
        }
      }
    }
    throw new RuntimeException("rook not found");
  }
}
