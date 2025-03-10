package org.example;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;
import org.example.ds.NestedInteger;

/**
 * https://github.com/krishnadey30/LeetCode-Questions-CompanyWise/blob/master/linkedin_alltime.csv
 */
public class LinkedInQuestions {

  public static void main(String[] args) {
    //    System.out.println(getFactors(32));
    System.out.println(minCost(new int[][] {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
    System.out.println(counter);
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

  static int counter = 0;

  /** https://leetcode.ca/all/256.html */
  public static int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;
    // 0 = read, 1 = blue, 2 = green
    Map<String, Integer> memo = new HashMap<>();
    return Math.min(
        paint(costs, 0, 0, memo), Math.min(paint(costs, 0, 1, memo), paint(costs, 0, 2, memo)));
  }

  private static int paint(int[][] costs, int i, int color, Map<String, Integer> memo) {
    if (memo.containsKey("%d,%d".formatted(i, color))) {
      return memo.get("%d,%d".formatted(i, color));
    }
    counter++;
    if (i == costs.length) return 0;

    int next1 = (color + 1) % 3; // Choose a different color
    int next2 = (color + 2) % 3; // Choose another different color

    int cost =
        costs[i][color]
            + Math.min(paint(costs, i + 1, next1, memo), paint(costs, i + 1, next2, memo));
    memo.put("%d,%d".formatted(i, color), cost);
    return cost;
  }
}
