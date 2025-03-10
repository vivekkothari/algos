package org.example;

import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import org.example.ds.NestedInteger;

/**
 * https://github.com/krishnadey30/LeetCode-Questions-CompanyWise/blob/master/linkedin_alltime.csv
 */
public class LinkedInQuestions {

  public static void main(String[] args) {
    RandomizedSet set = new RandomizedSet();
    System.out.println(set.remove(0));
    System.out.println(set.remove(0));
    System.out.println(set.insert(0));
    System.out.println(set.getRandom());
    System.out.println(set.remove(0));
    System.out.println(set.insert(0));
    //    maxPoints(new int[][] {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}});
    //    System.out.println(getFactors(32));
    //    System.out.println(minCostII(new int[][] {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
    //    var binaryNode =
    //        new Trees.TreeNode(
    //            4,
    //            new Trees.TreeNode(2, new Trees.TreeNode(1), new Trees.TreeNode(3)),
    //            new Trees.TreeNode(5, null, null));
    //    System.out.println(findLeavesOptimised(binaryNode));
    //    System.out.println(closestKValuesOptimal(binaryNode, 3.714286, 2));
    // [[1,1],2,[1,1]]
    //    ShortestDistance shortestDistance =
    //        new ShortestDistance(new String[] {"practice", "makes", "perfect", "coding",
    // "makes"});
    //    System.out.println(shortestDistance.minDistance("makes", "coding"));
    //    System.out.println(shortestDistance.minDistance("coding", "practice"));
    //    TwoSum twoSum = new TwoSum();
    //    twoSum.add(3);
    //    twoSum.add(3);
    //    twoSum.add(3);
    //    System.out.println(twoSum.find(6));
    //    System.out.println(
    //        shortestDistance(
    //            new String[] {"practice", "makes", "perfect", "coding", "makes"}, "makes",
    // "coding"));
    //    System.out.println(
    //        depthSumBfs(
    //            List.of(
    //                NestedInteger.of().add(NestedInteger.of(1)).add(NestedInteger.of(1)),
    //                NestedInteger.of(2),
    //                NestedInteger.of().add(NestedInteger.of(1)).add(NestedInteger.of(1)))));
    //    System.out.println(
    //        depthSumInverse(
    //            List.of(
    //                NestedInteger.of().add(NestedInteger.of(1)).add(NestedInteger.of(1)),
    //                NestedInteger.of(2),
    //                NestedInteger.of().add(NestedInteger.of(1)).add(NestedInteger.of(1)))));
  }

  public static int depthSum(List<NestedInteger> nestedList) {
    return depthSumDfs(nestedList, 1);
  }

  private static int depthSumDfs(List<NestedInteger> nestedList, int level) {
    var depthSum = 0;
    for (var list : nestedList) {
      if (list.isInteger()) {
        depthSum += list.getInteger() * level;
      } else {
        depthSum += depthSumDfs(list.getList(), level + 1);
      }
    }
    return depthSum;
  }

  public static int depthSumBfs(List<NestedInteger> nestedList) {
    Queue<NestedInteger> queue = new LinkedList<>(nestedList); // {elem, level}
    int ans = 0;
    int depth = 1;
    while (!queue.isEmpty()) {
      int size = queue.size(); // Track the number of elements in the current depth
      for (int i = 0; i < size; i++) {
        NestedInteger nestedInteger = queue.poll();
        if (nestedInteger.isInteger()) {
          ans += nestedInteger.getInteger() * depth;
        } else {
          queue.addAll(nestedInteger.getList());
        }
      }
      depth++; // Increment depth after processing the full level
    }
    return ans;
  }

  /** https://leetcode.com/problems/nested-list-weight-sum-ii */
  public static int depthSumInverse(List<NestedInteger> nestedList) {
    return depthSumInverseHelper(nestedList, 0);
  }

  private static int depthSumInverseHelper(List<NestedInteger> nestedList, int sum) {
    if (nestedList.isEmpty()) {
      return 0;
    }
    int total = sum;
    List<NestedInteger> currLevel = new ArrayList<>();
    for (NestedInteger nestedInteger : nestedList) {
      if (nestedInteger.isInteger()) {
        total += nestedInteger.getInteger();
      } else {
        currLevel.addAll(nestedInteger.getList());
      }
    }
    return total + depthSumInverseHelper(currLevel, total);
  }

  /** https://leetcode.ca/2016-07-30-243-Shortest-Word-Distance/ */
  public static int shortestDistance(String[] wordsDict, String word1, String word2) {
    int distance = Integer.MAX_VALUE;
    int pt1 = -1, pt2 = -1;
    for (int i = 0; i < wordsDict.length; i++) {
      if (wordsDict[i].equals(word1)) {
        pt1 = i;
      }
      if (wordsDict[i].equals(word2)) {
        pt2 = i;
      }
      if (pt1 != -1 && pt2 != -1) {
        distance = Math.min(distance, Math.abs(pt1 - pt2));
      }
    }
    return distance;
  }

  /** https://leetcode.ca/all/244.html */
  static class ShortestDistance {

    private final Map<String, List<Integer>> positions = new HashMap<>();

    ShortestDistance(String[] words) {
      for (int i = 0; i < words.length; i++) {
        positions.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
      }
    }

    int minDistance(String word1, String word2) {
      List<Integer> indices1 = positions.get(word1);
      List<Integer> indices2 = positions.get(word2);

      int i = 0, j = 0, minDistance = Integer.MAX_VALUE;

      // Two pointers technique to find the minimum distance
      while (i < indices1.size() && j < indices2.size()) {
        int index1 = indices1.get(i);
        int index2 = indices2.get(j);
        minDistance = Math.min(minDistance, Math.abs(index1 - index2));

        if (index1 < index2) {
          i++;
        } else {
          j++;
        }
      }
      return minDistance;
    }
  }

  /** https://leetcode.ca/all/170.html */
  static class TwoSum {
    private final Map<Integer, Integer> nums = new HashMap<>();

    void add(int num) {
      nums.put(num, nums.getOrDefault(num, 0) + 1);
    }

    // Check if 2 nums exists whose sum is num.
    boolean find(int num) {
      for (Integer oneNumber : nums.keySet()) {
        int target = num - oneNumber;
        if (nums.containsKey(target)) {
          if (oneNumber == target && nums.get(target) < 2) {
            continue;
          }
          return true;
        }
      }
      return false;
    }
  }

  private record Pair(int value, double diff) {}

  /** https://leetcode.ca/all/272.html TC: O(n*log(n)) SC: O(n) */
  public static List<Integer> closestKValues(Trees.TreeNode root, double target, int k) {
    PriorityQueue<Pair> maxHeap = new PriorityQueue<>(Comparator.comparingDouble(a -> a.diff));
    inorderTraversal(root, target, maxHeap);
    return maxHeap.stream().limit(k).map(Pair::value).collect(Collectors.toList());
  }

  private static void inorderTraversal(
      Trees.TreeNode root, double target, PriorityQueue<Pair> maxHeap) {
    if (root == null) {
      return;
    }
    inorderTraversal(root.left, target, maxHeap);
    maxHeap.offer(new Pair(root.val, Math.abs(target - root.val)));
    inorderTraversal(root.right, target, maxHeap);
  }

  public static List<Integer> closestKValuesOptimal(Trees.TreeNode root, double target, int k) {
    List<Integer> maxHeap = new LinkedList<>();
    inorderTraversalOptimal(root, target, maxHeap, k);
    return maxHeap;
  }

  private static void inorderTraversalOptimal(
      Trees.TreeNode root, double target, List<Integer> maxHeap, int k) {
    if (root == null) {
      return;
    }
    inorderTraversalOptimal(root.left, target, maxHeap, k);
    if (maxHeap.size() < k) {
      maxHeap.add(root.val);
    } else {
      // The list is iterated in-order making sure that the list is always sorted.
      if (Math.abs(root.val - target) >= Math.abs(maxHeap.getFirst() - target)) {
        // At this point the current element diff is larger than the largest diff.
        return;
      }
      // If not, we remove the smallest elements (thus the largest diff)
      maxHeap.removeFirst();
      // And add the current
      maxHeap.add(root.val);
    }
    inorderTraversalOptimal(root.right, target, maxHeap, k);
  }

  public static List<List<Integer>> getFactors(int n) {
    List<List<Integer>> factors = new ArrayList<>();
    getFactors(factors, new ArrayList<>(), n, 2);
    return factors;
  }

  private static void getFactors(List<List<Integer>> ans, List<Integer> t, int n, int i) {
    if (!t.isEmpty()) {
      List<Integer> cp = new ArrayList<>(t);
      cp.add(n);
      ans.add(cp);
    }
    for (int j = i; j <= n / j; ++j) {
      if (n % j == 0) {
        t.add(j);
        getFactors(ans, t, n / j, j);
        t.removeLast();
      }
    }
  }

  /** https://leetcode.com/problems/find-leaves-of-binary-tree */
  public static List<List<Integer>> findLeaves(Trees.TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    var prev = new Trees.TreeNode(0, root, null);
    while (prev.left != null) {
      List<Integer> t = new ArrayList<>();
      findLeaves(prev.left, prev, t);
      res.add(t);
    }
    return res;
  }

  private static void findLeaves(Trees.TreeNode root, Trees.TreeNode prev, List<Integer> t) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      t.add(root.val);
      if (prev.left == root) {
        prev.left = null;
      } else {
        prev.right = null;
      }
    }
    findLeaves(root.left, root, t);
    findLeaves(root.right, root, t);
  }

  /** https://leetcode.com/problems/find-leaves-of-binary-tree */
  public static List<List<Integer>> findLeavesOptimised(Trees.TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    getHeight(root, res);
    return res;
  }

  private static int getHeight(Trees.TreeNode node, List<List<Integer>> res) {
    if (node == null) return -1; // Base case

    int leftHeight = getHeight(node.left, res);
    int rightHeight = getHeight(node.right, res);

    int currentHeight = Math.max(leftHeight, rightHeight) + 1;

    if (res.size() == currentHeight) {
      res.add(new ArrayList<>());
    }

    res.get(currentHeight).add(node.val);

    // Remove leaves by setting references to null
    node.left = node.right = null;

    return currentHeight;
  }

  /** https://leetcode.ca/all/256.html */
  public static int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;
    // 0 = read, 1 = blue, 2 = green
    int[][] memo = new int[costs.length][3];
    for (int[] ints : memo) {
      Arrays.fill(ints, -1);
    }
    return Math.min(
        paint(costs, 0, 0, memo), Math.min(paint(costs, 0, 1, memo), paint(costs, 0, 2, memo)));
  }

  private static int paint(int[][] costs, int i, int color, int[][] memo) {
    if (i == costs.length) return 0;
    if (memo[i][color] != -1) return memo[i][color];
    int next1 = (color + 1) % 3; // Choose a different color
    int next2 = (color + 2) % 3; // Choose another different color

    int cost =
        costs[i][color]
            + Math.min(paint(costs, i + 1, next1, memo), paint(costs, i + 1, next2, memo));
    memo[i][color] = cost;
    return cost;
  }

  public static int minCostBottomsUp(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;

    int n = costs.length;

    // Bottom-up DP: Modify the costs array itself to save space
    for (int i = n - 2; i >= 0; i--) {
      costs[i][0] += Math.min(costs[i + 1][1], costs[i + 1][2]); // Red cost
      costs[i][1] += Math.min(costs[i + 1][0], costs[i + 1][2]); // Blue cost
      costs[i][2] += Math.min(costs[i + 1][0], costs[i + 1][1]); // Green cost
    }

    // The answer is the min cost to paint the first house with any of the three colors
    return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
  }

  /** https://leetcode.ca/2016-08-21-265-Paint-House-II/ */
  public static int minCostII(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }
    int min1 = -1, min2 = -1;
    for (int i = 0; i < costs.length; i++) {
      int last1 = min1;
      int last2 = min2;
      min1 = -1;
      min2 = -1;
      for (int j = 0; j < costs[i].length; j++) {
        if (j != last1) {
          costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
        } else {
          costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
        }

        if (min1 < 0 || costs[i][j] < costs[i][min1]) {
          min2 = min1;
          min1 = j;
        } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
          min2 = j;
        }
      }
    }

    return costs[costs.length - 1][min1];
  }

  /** https://leetcode.com/problems/max-points-on-a-line/description/ */
  public static int maxPoints(int[][] points) {
    int max = 1;
    for (int i = 0; i < points.length; i++) {
      Map<Double, Integer> map = new HashMap<>();
      for (int j = 0; j < points.length; j++) {
        if (i == j) {
          continue;
        }
        double slope;
        if (points[i][0] - points[j][0] == 0) {
          slope = Double.POSITIVE_INFINITY;
        } else {
          slope = (double) (points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
        }
        map.put(slope, map.getOrDefault(slope, 1) + 1);
      }
      if (!map.isEmpty()) {
        max = Math.max(max, Collections.max(map.values()));
      }
    }
    return max;
  }

  public boolean isNumber(String s) {
    boolean isdot = false, ise = false, nums = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) nums = true;
      else if (c == '+' || c == '-') {
        if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
      } else if (c == 'e' || c == 'E') {
        if (ise || !nums) return false;
        ise = true;
        nums = false;
      } else if (c == '.') {
        if (isdot || ise) return false;
        isdot = true;
      } else return false;
    }
    return nums;
  }

  /** https://leetcode.com/problems/can-place-flowers/description/ */
  public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    for (int i = 0; i < flowerbed.length && n > 0; i++) {
      if (canPlaceFlowersAt(flowerbed, i)) {
        n--;
      }
    }
    return n == 0;
  }

  private static boolean canPlaceFlowersAt(int[] flowerbed, int i) {
    // Already has flower
    if (flowerbed[i] == 1) return false;
    boolean leftEmpty = i == 0 || flowerbed[i - 1] == 0;
    boolean rightEmpty = i == flowerbed.length - 1 || flowerbed[i + 1] == 0;
    if (leftEmpty && rightEmpty) {
      flowerbed[i] = 1;
      return true;
    }
    return false;
  }

  /** https://leetcode.com/problems/insert-delete-getrandom-o1/ */
  static class RandomizedSet {

    private final Random random = new Random();
    // Element to its position in list.
    private final Map<Integer, Integer> map = new HashMap<>();
    private final List<Integer> list = new ArrayList<>();

    public RandomizedSet() {}

    public boolean insert(int val) {
      if (map.containsKey(val)) return false;
      list.add(val);
      map.put(val, list.size() - 1);
      return true;
    }

    public boolean remove(int val) {
      if (!map.containsKey(val)) return false;
      var posOfRemoved = map.get(val);
      var temp = list.getLast();
      list.set(posOfRemoved, temp);
      map.put(temp, posOfRemoved);
      list.removeLast();
      map.remove(val);
      return true;
    }

    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }
}
