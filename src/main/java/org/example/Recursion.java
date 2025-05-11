package org.example;

import java.util.*;
import java.util.Arrays;

class Recursion {

  public static void main(String[] args) {
    splitArray(new int[] {7, 2, 5, 10, 8}, 2).forEach(System.out::println);
    splitArraySum(new int[] {7, 2, 5, 10, 8}, 2).forEach(System.out::println);
    splitArrayMinSum(new int[] {7, 2, 5, 10, 8}, 2).forEach(System.out::println);
    //    System.out.println(cutRod(new int[] {3, 5, 8, 9, 10, 17, 17, 20}));
    //    System.out.println(cutRodTab(new int[] {3, 5, 8, 9, 10, 17, 17, 20}));

    //    System.out.println(findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3));

    //    System.out.println(countPartitions(new int[] {1, 2, 1, 0, 1, 3, 3}, 11));

    //    System.out.println(change(5, new int[] {1, 2, 5}));
    //    System.out.println(changeTable(5, new int[] {1, 2, 5}));
    //
    //    System.out.println(coinChange(new int[] {1, 2, 3, 5, 10}, 8));
    //    System.out.println(coinChangeTab(new int[] {1, 2, 3, 5, 10}, 8));
    //    System.out.println(coinChangeTabDifferent(new int[] {1, 2, 3, 5, 10}, 8));
    //    System.out.println(knapsack(6, new int[] {30, 40, 60}, new int[] {3, 2, 5}));
    //    System.out.println(knapsackTabulation(6, new int[] {30, 40, 60}, new int[] {3, 2, 5}));
    //    System.out.println(knapsackTabulationSpace(6, new int[] {30, 40, 60}, new int[] {3, 2,
    // 5}));
    //    System.out.println(perfectSum(new int[] {0, 10, 0}, 0));
    //    System.out.println(minDiffBetweenTwoSubSequences(new int[] {1, 6, 5, 11}));
    //    System.out.println(canPartition(new int[] {1, 2, 3, 3, 4, 4, 5, 5}));
    //    System.out.println(subsetSumOfKTabulation(new int[] {5, 1, 2, 7, 6, 1, 5}, 8));
    //    System.out.println(minSumPath(new int[][] {{5, 9, 6}, {11, 5, 2}}));
    //    System.out.println(minSumPathDp(new int[][] {{5, 9, 6}, {11, 5, 2}}));
    //    System.out.println(uniqueWaysDpSpaceOptimised(2, 2));
    //    System.out.println(uniqueWaysDp(10, 10));
    //    System.out.println(uniqueWaysDpSpaceOptimised(10, 10));
    //    System.out.println(uniqueWays(3, 3));
    //    System.out.println(mazeObstacles(new int[][] {{0, 0, 0}, {0, -1, 0}, {0, -1, 0}}));
    //    System.out.println(ninjaTraining(new int[][] {{10, 50, 1}, {5, 100, 11}, {5, 100, 11}}));
    //    System.out.println(ninjaTrainingDp(new int[][] {{10, 50, 1}, {5, 100, 11}, {5, 100,
    // 11}}));
    //    System.out.println(nonAdjacentSubseq(new int[] {1, 2, 3, 4, 5}));
    //    System.out.println(nonAdjacentSubseqMaxSum(new int[] {1, 2, 3, 4, 5}));
    //    System.out.println(nonAdjacentSubseqMaxSumDp(new int[] {1, 2, 3, 4, 5}));
    //    System.out.println(nonAdjacentSubseqMaxSpaceOptimised(new int[] {1, 2, 3, 4, 5}));
    //    System.out.println(minFrogKJumpDp(new int[] {30, 10, 60, 10, 60, 50}, 2));
    //    System.out.println(
    //        wordBreak(
    //
    // "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
    //            List.of(
    //                "a",
    //                "aa",
    //                "aaa",
    //                "aaaa",
    //                "aaaaa",
    //                "aaaaaa",
    //                "aaaaaaa",
    //                "aaaaaaaa",
    //                "aaaaaaaaa",
    //                "aaaaaaaaaa")));
    //    System.out.println(partition("madammadam"));
    //    System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 7));
    //    System.out.println(permute(new int[] {1, 2, 3}));
    //    System.out.println(getSubSequences(new int[] {3, 1, 2}));
    //    System.out.println(getSubSequencesWithSum(new int[] {1, 1, 2}, 2));
    //    System.out.println(countAndSay(30));
    //    removeInvalidParentheses(")()()").forEach(System.out::println);
    //    printName("vivek", 5);
    //    printNumsBackTrack(10);
    //    System.out.println(factorial(3));
    //    int[] arr = new int[] {1, 2, 3, 4, 5, 6};
    //    reverseArray(arr, 0);
    //    System.out.println(Arrays.toString(arr));
    //    System.out.println(checkIsPalindrome("madam", 0));
    //    printReverse(10);
  }

  public static void printName(String name, int n) {
    if (n == 0) return;
    System.out.println(name);
    printName(name, n - 1);
  }

  public static void printNums(int k, int n) {
    if (k == n + 1) return;
    System.out.println(k);
    printNums(k + 1, n);
  }

  public static void printNumsBackTrack(int n) {
    if (n == 0) return;
    printNumsBackTrack(n - 1);
    System.out.println(n);
  }

  public static int sum(int n) {
    if (n == 0) return 0;
    return n + sum(n - 1);
  }

  public static int factorial(int n) {
    if (n == 1) return 1;
    return n * factorial(n - 1);
  }

  public static void printReverse(int n) {
    if (n == 0) return;
    System.out.println(n);
    printReverse(n - 1);
  }

  public static void reverseArray(int[] arr, int l) {
    if (l > arr.length / 2) return;
    reverseArray(arr, l + 1);
    var tmp = arr[l];
    arr[l] = arr[arr.length - l - 1];
    arr[arr.length - l - 1] = tmp;
  }

  public static long fib(long n) {
    return fib(n, new HashMap<>());
  }

  public static long fib(long n, Map<Long, Long> memo) {
    if (n < 2) {
      return n;
    }
    if (memo.containsKey(n)) {
      return memo.get(n);
    }
    memo.put(n, fib(n - 1, memo) + fib(n - 2, memo));
    return memo.get(n);
  }

  public static boolean checkIsPalindrome(String s, int l) {
    if (l > s.length() / 2) return true;
    return s.charAt(l) == s.charAt(s.length() - l - 1) && checkIsPalindrome(s, l + 1);
  }

  public static List<List<Integer>> getSubSequences(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsequences(nums, 0, res, new ArrayList<>());
    return res;
  }

  public static void subsequences(int[] nums, int i, List<List<Integer>> res, List<Integer> temp) {
    if (i >= nums.length) {
      res.add(List.copyOf(temp));
      return;
    }
    temp.addLast(nums[i]);
    subsequences(nums, i + 1, res, temp);
    temp.removeLast();
    subsequences(nums, i + 1, res, temp);
  }

  public static List<List<Integer>> getSubSequencesWithSum(int[] nums, int k) {
    List<List<Integer>> res = new ArrayList<>();
    subsequencesWithSum(nums, 0, res, new ArrayList<>(), 0, k);
    return res;
  }

  public static void subsequencesWithSum(
      int[] nums, int i, List<List<Integer>> res, List<Integer> temp, int currSum, int k) {
    if (i >= nums.length) {
      if (currSum == k) {
        res.add(List.copyOf(temp));
      }
      return;
    }
    temp.addLast(nums[i]);
    subsequencesWithSum(nums, i + 1, res, temp, currSum + nums[i], k);
    temp.removeLast();
    subsequencesWithSum(nums, i + 1, res, temp, currSum, k);
  }

  public static String countAndSay(int n) {
    return recur(n);
  }

  static String recur(int n) {
    if (n == 1) return "1";

    String s = recur(n - 1);
    return countFreq(s);
  }

  // //////////////////////////////////////////////////

  /** Is subset adds upto target. */
  public static boolean isSubsetSum(int[] nums, int target) {
    return isSubsetSum(nums, target, 0, 0);
  }

  private static boolean isSubsetSum(int[] nums, int target, int i, int currentSum) {
    if (i >= nums.length) {
      return currentSum == target;
    }
    // take
    return isSubsetSum(nums, target, i + 1, currentSum + nums[i])
        // don't take
        || isSubsetSum(nums, target, i + 1, currentSum);
  }

  /// //////////////////////////////////////////////////
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsets(nums, res, new ArrayList<>(), 0);
    return res;
  }

  private void subsets(int[] nums, List<List<Integer>> res, List<Integer> temp, int i) {
    if (i >= nums.length) {
      res.add(List.copyOf(temp));
      return;
    }
    // take
    temp.addLast(nums[i]);
    subsets(nums, res, temp, i + 1);
    // don't take
    temp.removeLast();
    subsets(nums, res, temp, i + 1);
  }

  //////////////////////////////////////////////////
  /**
   * Permutations of Distinct Integers Problem: Given an array of distinct integers nums, return all
   * possible permutations of the array.
   */
  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    permute(nums, res, new ArrayList<>(), used);
    return res;
  }

  private static void permute(
      int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
    if (temp.size() == nums.length) {
      res.add(List.copyOf(temp));
      return;
    }
    for (int j = 0; j < nums.length; j++) {
      if (used[j]) continue;
      temp.addLast(nums[j]);
      used[j] = true;
      permute(nums, res, temp, used);
      used[j] = false;
      temp.removeLast();
    }
  }

  /// /////////////////////////

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    combinationSum(candidates, target, res, new ArrayList<>(), 0);
    return res;
  }

  private static void combinationSum(
      int[] candidates, int target, List<List<Integer>> res, List<Integer> temp, int i) {
    if (0 == target) {
      res.add(List.copyOf(temp));
      return;
    }
    if (i >= candidates.length) {
      return;
    }
    if (target >= candidates[i]) {
      temp.addLast(candidates[i]);
      combinationSum(candidates, target - candidates[i], res, temp, i);
      temp.removeLast();
    }
    combinationSum(candidates, target, res, temp, i + 1);
  }

  /// ////////////////////////////////////
  public static List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<>();
    partition(res, s, new ArrayList<>(), 0);
    return res;
  }

  private static void partition(List<List<String>> res, String s, List<String> list, int start) {
    // base condition
    if (start >= s.length()) {
      res.add(List.copyOf(list));
      return;
    }
    for (int end = start; end < s.length(); end++) {
      if (isPalindrome(s, start, end)) {
        list.add(s.substring(start, end + 1));
        partition(res, s, list, end + 1);
        list.removeLast();
      }
    }
  }

  private static boolean isPalindrome(CharSequence sb, int l, int r) {
    while (l <= r) {
      if (sb.charAt(l++) != sb.charAt(r--)) {
        return false;
      }
    }
    return true;
  }

  /// ////////////////////////////////////////////////////

  public static boolean wordBreak(String s, List<String> words) {
    Map<Integer, Boolean> memo = new HashMap<>();
    return wordBreak(s, new HashSet<>(words), 0, memo);
  }

  private static boolean wordBreak(
      String s, Set<String> words, int start, Map<Integer, Boolean> memo) {
    if (memo.containsKey(start)) {
      return memo.get(start);
    }
    if (start == s.length()) {
      return true;
    }
    for (int end = start + 1; end <= s.length(); end++) {
      if (words.contains(s.substring(start, end)) && wordBreak(s, words, end, memo)) {
        memo.put(start, true);
        return true;
      }
    }
    memo.put(start, false); // ✅ This is critical
    return false;
  }

  // Returns the required string, by appending frequency
  static String countFreq(String s) {
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == s.charAt(i - 1)) {
        count++;
      } else {
        sb.append(count);
        sb.append(s.charAt(i - 1));
        count = 1;
      }
    }
    sb.append(count);
    sb.append(s.charAt(s.length() - 1));
    return sb.toString();
  }

  public static List<String> removeInvalidParentheses(String s) {
    List<String> res = new ArrayList<>();
    char[] check = new char[] {'(', ')'};
    removeInvalidParentheses(s, res, check, 0, 0);
    return res;
  }

  public static void removeInvalidParentheses(
      String s, List<String> res, char[] check, int last_i, int last_j) {
    int count = 0;
    int i = last_i;
    while (i < s.length() && count >= 0) {
      if (s.charAt(i) == check[0]) count++;
      if (s.charAt(i) == check[1]) count--;
      i++;
    }
    if (count >= 0) {
      // no extra ')' is detected. We now have to detect extra '(' by reversing the string.
      String reversed = new StringBuffer(s).reverse().toString();
      if (check[0] == '(') {
        removeInvalidParentheses(reversed, res, new char[] {')', '('}, 0, 0);
      } else {
        res.add(reversed);
      }
    } else { // extra ')' is detected and we have to do something
      i -= 1; // 'i-1' is the index of abnormal ')' which makes count<0
      for (int j = last_j; j <= i; j++) {
        if (s.charAt(j) == check[1] && (j == last_j || s.charAt(j - 1) != check[1])) {
          removeInvalidParentheses(s.substring(0, j) + s.substring(j + 1), res, check, i, j);
        }
      }
    }
  }

  /// //////////////////////////////////
  public static int minFrogKJump(int[] nums, int k) {
    return minFrogKJump(nums, nums.length - 1, k);
  }

  private static int minFrogKJump(int[] nums, int i, int k) {
    if (i == 0) {
      return 0;
    }
    int minCost = Integer.MAX_VALUE;
    for (int j = 1; j <= k; j++) {
      if (i - j >= 0) {
        int jumpCost = minFrogKJump(nums, i - j, k) + Math.abs(nums[i] - nums[i - j]);
        minCost = Math.min(minCost, jumpCost);
      }
    }
    return minCost;
  }

  public static int minFrogKJumpDp(int[] nums, int k) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = 1; j <= k; j++) {
        if (i - j >= 0) {
          int jumpCost = dp[i - j] + Math.abs(nums[i] - nums[i - j]);
          dp[i] = Math.min(dp[i], jumpCost);
        }
      }
    }
    return dp[nums.length - 1];
  }

  /// ////////////////////////////
  public static int minFrogJump(int[] nums) {
    return minFrogJump(nums, nums.length - 1);
  }

  private static int minFrogJump(int[] nums, int i) {
    if (i == 0) return 0;
    if (i == 1) return Math.abs(nums[1] - nums[0]);
    int left = minFrogJump(nums, i - 1) + Math.abs(nums[i] - nums[i - 1]);
    int right = minFrogJump(nums, i - 2) + Math.abs(nums[i] - nums[i - 2]);
    return Math.min(left, right);
  }

  public static int minFrogJumpDp(int[] nums) {
    int[] dp = new int[nums.length];
    dp[1] = Math.abs(nums[1] - nums[0]);
    for (int i = 2; i < nums.length; i++) {
      int left = dp[i - 1] + Math.abs(nums[i] - nums[i - 1]);
      int right = dp[i - 2] + Math.abs(nums[i] - nums[i - 2]);
      dp[i] = Math.min(left, right);
    }
    return dp[nums.length - 1];
  }

  public static int minFrogJumpBottomUpDp(int[] nums) {
    int prev2 = 0;
    int prev = Math.abs(nums[1] - nums[0]);
    for (int i = 2; i < nums.length; i++) {
      int left = prev + Math.abs(nums[i] - nums[i - 1]);
      int right = prev2 + Math.abs(nums[i] - nums[i - 2]);
      int cur = Math.min(left, right);
      prev2 = prev;
      prev = cur;
    }
    return prev;
  }

  /// ////////////////////////////

  public static List<List<Integer>> nonAdjacentSubseq(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    nonAdjacentSubseq(res, nums, new ArrayList<>(), 0);
    return res;
    // [1,2,3,4,5,6]
  }

  private static void nonAdjacentSubseq(
      List<List<Integer>> res, int[] nums, List<Integer> temp, int start) {
    if (start >= nums.length) {
      res.add(List.copyOf(temp));
      return;
    }
    temp.add(nums[start]);
    nonAdjacentSubseq(res, nums, temp, start + 2);
    temp.removeLast();
    nonAdjacentSubseq(res, nums, temp, start + 1);
  }

  public static int nonAdjacentSubseqMaxSum(int[] nums) {
    return nonAdjacentSubseqMaxSum(nums, nums.length - 1);
  }

  private static int nonAdjacentSubseqMaxSum(int[] nums, int i) {
    if (i == 0) return nums[0];
    if (i < 0) return 0;
    int pick = nums[i] + nonAdjacentSubseqMaxSum(nums, i - 2);
    int notPick = nonAdjacentSubseqMaxSum(nums, i - 1);
    return Math.max(pick, notPick);
  }

  public static int nonAdjacentSubseqMaxSumDp(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    }
    return dp[nums.length - 1];
  }

  public static int nonAdjacentSubseqMaxSpaceOptimised(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    int prev2 = nums[0];
    int prev = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      int cur = Math.max(prev2 + nums[i], prev);
      prev2 = prev;
      prev = cur;
    }
    return prev;
  }

  /// /////////////////////////

  public static int houseRobber(int[] valuesInHouses) {
    int prev2 = valuesInHouses[0];
    int prev = Math.max(valuesInHouses[0], valuesInHouses[1]);
    for (int i = 2; i < valuesInHouses.length - 1; i++) {
      int take = prev2 + valuesInHouses[i];
      int nonTake = prev;
      int cur = Math.max(take, nonTake);
      prev2 = prev;
      prev = cur;
    }
    int maxExceptLast = prev;
    prev2 = valuesInHouses[1];
    prev = Math.max(valuesInHouses[1], valuesInHouses[2]);
    for (int i = 3; i < valuesInHouses.length; i++) {
      int take = prev2 + valuesInHouses[i];
      int nonTake = prev;
      int cur = Math.max(take, nonTake);
      prev2 = prev;
      prev = cur;
    }
    return Math.max(maxExceptLast, prev);
  }

  /// /////////////////////////////////////

  /**
   * activities is m * 3 matrix. total 3 activities, can't perform same activity on consecutive day.
   * https://www.youtube.com/watch?v=AE39gJYuRog&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=8&ab_channel=takeUforward
   */
  public static int ninjaTraining(int[][] activities) {
    // for first iter, it doesn't matter what the last activity is, thus pass a num so that we try
    // out all cases
    int[][] dp = new int[activities.length][activities[0].length + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return ninjaTraining(activities, activities.length - 1, activities[0].length, dp);
  }

  private static int ninjaTraining(int[][] activities, int day, int lastActivity, int[][] dp) {
    if (day == 0) {
      int max = Integer.MIN_VALUE;
      for (int j = 0; j < activities[0].length; j++) {
        if (lastActivity != j) {
          max = Math.max(max, activities[day][j]);
        }
      }
      return max;
    }
    if (dp[day][lastActivity] != -1) {
      return dp[day][lastActivity];
    }
    int max = Integer.MIN_VALUE;
    for (int j = 0; j < activities[0].length; j++) {
      if (lastActivity != j) {
        int points = activities[day][j] + ninjaTraining(activities, day - 1, j, dp);
        max = Math.max(max, points);
      }
    }
    return dp[day][lastActivity] = max;
  }

  public static int ninjaTrainingDp(int[][] activities) {
    // {10, 50, 1}, {5, 100, 11}
    int[] dp = new int[activities[0].length];

    for (int i = 0; i < dp.length; i++) {
      int max = 0;
      for (int j = 0; j < activities[0].length; j++) {
        if (j != i) {
          max = Math.max(max, activities[0][j]);
        }
      }
      dp[i] = max;
    }

    for (int day = 1; day < activities.length; day++) {
      int[] currDp = new int[dp.length];
      for (int activity = 0; activity < activities[day].length; activity++) {
        int max = Integer.MIN_VALUE;
        for (int lastActivity = 0; lastActivity < activities[day].length; lastActivity++) {
          if (lastActivity != activity) {
            int points = activities[day][activity] + dp[lastActivity];
            max = Math.max(max, points);
          }
        }
        currDp[activity] = max;
      }
      dp = currDp;
    }
    return Arrays.stream(dp).max().orElse(0);
  }

  /// /////////////////////
  /** we can only move right or down. count num of ways to go from (0,0) to (m-1,n-1). */
  static long uniqueWays(int m, int n) {
    long[][] dp = new long[m][n];
    for (long[] p : dp) {
      Arrays.fill(p, -1);
    }
    return uniqueWaysRecur(m - 1, n - 1, dp);
  }

  private static long uniqueWaysRecur(int i, int j, long[][] dp) {
    if (i < 0 || j < 0) {
      return 0;
    }
    if (i == 0 && j == 0) {
      return 1;
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    return dp[i][j] = uniqueWaysRecur(i - 1, j, dp) + uniqueWaysRecur(i, j - 1, dp);
  }

  static long uniqueWaysDp(int m, int n) {
    long[][] dp = new long[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = 1;
        } else {
          var up = i < 1 ? 0 : dp[i - 1][j];
          var left = j < 1 ? 0 : dp[i][j - 1];
          dp[i][j] = up + left;
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  static long uniqueWaysDpSpaceOptimised(int m, int n) {
    long[] prev = new long[m];
    for (int i = 0; i < m; i++) {
      long[] curr = new long[m];
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          curr[j] = 1;
        } else {
          var up = i < 1 ? 0 : prev[j];
          var left = j < 1 ? 0 : curr[j - 1];
          curr[j] = up + left;
        }
      }
      prev = curr;
    }
    return prev[n - 1];
  }

  /// /////////////////////

  /*
  Find ways to go from 0,0 to m-1, n-1. If you encounter -1, you can't move ahead.
   */
  static long mazeObstacles(int[][] grid) {
    long[][] dp = new long[grid.length][grid[0].length];
    for (long[] p : dp) {
      Arrays.fill(p, -1);
    }
    return mazeObstaclesRecur(grid.length - 1, grid[0].length - 1, grid, dp);
  }

  private static long mazeObstaclesRecur(int i, int j, int[][] grid, long[][] dp) {
    if (i < 0 || j < 0) {
      return 0;
    }
    if (i == 0 && j == 0) {
      return 1;
    }
    if (grid[i][j] == -1) {
      return 0;
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    return dp[i][j] =
        mazeObstaclesRecur(i - 1, j, grid, dp) + mazeObstaclesRecur(i, j - 1, grid, dp);
  }

  /// //////////////////////////

  /** Find a path from 0,0 to m-1, n-1 which minimises the cost of all numbers. */
  public static int minSumPath(int[][] grid) {
    int[][] dp = new int[grid.length][grid[0].length];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return minSumPathRecur(grid, grid.length - 1, grid[0].length - 1, dp);
  }

  private static int minSumPathRecur(int[][] grid, int i, int j, int[][] dp) {
    if (i == 0 && j == 0) {
      return grid[i][j];
    }
    if (i < 0 || j < 0) {
      return Integer.MAX_VALUE / 2;
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    int left = grid[i][j] + minSumPathRecur(grid, i - 1, j, dp);
    int up = grid[i][j] + minSumPathRecur(grid, i, j - 1, dp);
    return dp[i][j] = Math.min(left, up);
  }

  /// //////////////////////////

  public static int minSumPathDp(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[] prev = new int[m];
    for (int i = 0; i < n; i++) {
      int[] curr = new int[m];
      for (int j = 0; j < m; j++) {
        if (i == 0 && j == 0) {
          curr[j] = grid[i][j];
        } else {
          int up = grid[i][j];

          if (i > 0) up += prev[j];
          else up = Integer.MAX_VALUE / 2;

          int left = grid[i][j];
          if (j > 0) left += curr[j - 1];
          else left = Integer.MAX_VALUE / 2;
          curr[j] = Math.min(left, up);
        }
      }
      prev = curr;
    }
    return prev[m - 1];
  }

  /** https://leetcode.com/problems/triangle/ */
  public static int minimumTotal(List<List<Integer>> triangle) {
    int[][] dp = new int[triangle.size()][triangle.size()];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return minimumTotalRecur(0, 0, triangle, dp);
  }

  private static int minimumTotalRecur(int i, int j, List<List<Integer>> triangle, int[][] dp) {
    if (i >= triangle.size()) {
      return 0;
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    int curr = triangle.get(i).get(j);
    int down = curr + minimumTotalRecur(i + 1, j, triangle, dp);
    int downDiag = curr + minimumTotalRecur(i + 1, j + 1, triangle, dp);
    return dp[i][j] = Math.min(down, downDiag);
  }

  public static int minimumTotalTabulation(List<List<Integer>> triangle) {
    int n = triangle.size();
    int[][] dp = new int[n][n];
    for (int j = 0; j < n; j++) {
      dp[n - 1][j] = triangle.get(n - 1).get(j);
    }
    for (int i = n - 2; i >= 0; i--) {
      for (int j = i; j >= 0; j--) {
        int curr = triangle.get(i).get(j);
        int down = curr + dp[i + 1][j];
        int downDiag = curr + dp[i + 1][j + 1];
        dp[i][j] = Math.min(down, downDiag);
      }
    }
    return dp[0][0];
  }

  /// /////////////////////////////

  // Check if there exists a subset whose sum is k
  public static boolean subsetSumOfK(int[] nums, int k) {
    int[][] dp = new int[nums.length][k + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return subsetSumOfK(nums, nums.length - 1, k, dp);
  }

  private static boolean subsetSumOfK(int[] nums, int i, int target, int[][] dp) {
    if (target == 0) {
      dp[i][target] = 1;
      return true;
    }
    if (i == 0) return (dp[i][target] = nums[i] == target ? 1 : 0) == 1;
    if (dp[i][target] != -1) return dp[i][target] == 1;
    boolean notPick = subsetSumOfK(nums, i - 1, target, dp);
    boolean pick = false;
    if (nums[i] <= target) pick = subsetSumOfK(nums, i - 1, target - nums[i], dp);
    return (dp[i][target] = (pick || notPick) ? 1 : 0) == 1;
  }

  public static boolean subsetSumOfKTabulation(int[] nums, int k) {
    int N = nums.length;
    boolean[][] dp = new boolean[N][k + 1];
    for (int i = 0; i < N; i++) {
      dp[i][0] = true; // A sum of 0 is always possible
    }
    if (nums[0] <= k) dp[0][nums[0]] = true; // Initialize the first element properly
    for (int i = 1; i < N; i++) {
      for (int target = 1; target <= k; target++) {
        boolean notPick = dp[i - 1][target]; // Exclude the current element
        boolean pick = false;
        if (nums[i] <= target) pick = dp[i - 1][target - nums[i]]; // Include the current element
        dp[i][target] = pick || notPick;
      }
    }
    return dp[N - 1][k];
  }

  public static boolean checkSubsequenceSumSpace(int N, int[] nums, int k) {
    boolean[] prev = new boolean[k + 1];
    boolean[] curr = new boolean[k + 1];
    prev[0] = true;
    curr[0] = true;
    if (nums[0] <= k) prev[nums[0]] = true; // Initialize the first element properly
    for (int i = 1; i < N; i++) {
      curr[0] = true;
      for (int target = 1; target <= k; target++) {
        boolean notPick = prev[target]; // Exclude the current element
        boolean pick = false;
        if (nums[i] <= target) pick = prev[target - nums[i]]; // Include the current element
        curr[target] = pick || notPick;
      }
      prev = curr;
    }
    return prev[k];
  }

  /// ////////////////////

  /** [2,3,3,3,4,5] -> [2,3,5] and [3,3,4] add up to same 10. */
  public static boolean canPartition(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    if (sum % 2 != 0) return false;
    int target = sum / 2;
    int[][] dp = new int[nums.length][target + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return subsetSumOfK(nums, nums.length - 1, target, dp);
  }

  /// ////////////////////////////

  /** https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1 */
  public static int minDiffBetweenTwoSubSequences(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    int N = nums.length;
    boolean[][] dp = new boolean[N][sum + 1];
    for (int i = 0; i < N; i++) {
      dp[i][0] = true; // A sum of 0 is always possible
    }
    if (nums[0] <= sum) dp[0][nums[0]] = true; // Initialize the first element properly
    for (int i = 1; i < N; i++) {
      for (int target = 1; target <= sum; target++) {
        boolean notPick = dp[i - 1][target]; // Exclude the current element
        boolean pick = false;
        if (nums[i] <= target) pick = dp[i - 1][target - nums[i]]; // Include the current element
        dp[i][target] = pick || notPick;
      }
    }

    int mini = Integer.MAX_VALUE;
    for (int i = 0; i <= sum; i++) {
      if (dp[N - 1][i]) {
        int s2 = sum - i;
        mini = Math.min(mini, Math.abs(i - s2));
      }
    }
    return mini;
  }

  /** https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1 same as findWays. */
  public static int perfectSum(int[] nums, int k) {
    int[][] dp = new int[nums.length][k + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return perfectSumRecur(nums, k, nums.length - 1, dp);
  }

  private static int perfectSumRecur(int[] nums, int target, int i, int[][] dp) {
    if (i == 0) {
      // when at first ele, if target is 0 and ele is 0, we have 2 ways picking or not picking are
      // both valid cases
      if (target == 0 && nums[i] == 0) return 2;
      if (target == 0 || target == nums[i]) return 1;
      return 0;
    }
    // instead of above, we can also just keep the below code.
    //    if (i < 0) {
    //      return target == 0 ? 1 : 0;
    //    }
    if (dp[i][target] != -1) return dp[i][target];
    int notPick = perfectSumRecur(nums, target, i - 1, dp); // Exclude the current element
    int pick = 0;
    if (nums[i] <= target)
      pick = perfectSumRecur(nums, target - nums[i], i - 1, dp); // Include the current element
    return dp[i][target] = notPick + pick; // Return the total count
  }

  /// //////////////////////////

  /** Count partitions where abs diff between sum of each partition is diff. */
  public static int countPartitions(int[] nums, int diff) {
    int totalSum = Arrays.stream(nums).sum();
    if (totalSum - diff < 0 || (totalSum - diff) % 2 == 1) {
      return 0;
    }
    return perfectSum(nums, (totalSum - diff) / 2);
  }

  /// //////////////////////////

  // 0-1 knapsack
  /** W = 4, val[] = [1, 2, 3], wt[] = [4, 5, 1] */
  static int knapsack(int W, int[] val, int[] wt) {
    int[][] dp = new int[val.length][W + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return knapsackRecur(val.length - 1, W, val, wt, dp);
  }

  static int knapsackRecur(int i, int w, int[] val, int[] wt, int[][] dp) {
    if (i < 0) return 0;
    if (w < 0) return (int) -1e6;
    if (dp[i][w] != -1) return dp[i][w];
    int notPick = knapsackRecur(i - 1, w, val, wt, dp);
    int pick = w >= wt[i] ? val[i] + knapsackRecur(i - 1, w - wt[i], val, wt, dp) : (int) -1e6;
    return dp[i][w] = Math.max(pick, notPick);
  }

  static int knapsackTabulation(int W, int[] val, int[] wt) {
    int[][] dp = new int[val.length][W + 1];

    // Base case: Fill for the first item
    for (int w = 0; w <= W; w++) {
      dp[0][w] = (w >= wt[0]) ? val[0] : 0;
    }

    for (int i = 1; i < val.length; i++) {
      for (int w = 0; w <= W; w++) {
        int notPick = dp[i - 1][w];
        int pick = w >= wt[i] ? val[i] + dp[i - 1][w - wt[i]] : (int) -1e6;
        dp[i][w] = Math.max(pick, notPick);
      }
    }
    return dp[val.length - 1][W];
  }

  static int knapsackTabulationSpace(int W, int[] val, int[] wt) {
    int[] prev = new int[W + 1];

    // Base case: Fill for the first item
    for (int w = 0; w <= W; w++) {
      prev[w] = (w >= wt[0]) ? val[0] : 0;
    }

    for (int i = 1; i < val.length; i++) {
      for (int w = W; w >= 0; w--) {
        int notPick = prev[w];
        int pick = w >= wt[i] ? val[i] + prev[w - wt[i]] : (int) -1e6;
        prev[w] = Math.max(pick, notPick);
      }
    }
    return prev[W];
  }

  /// ///////////////////////////
  ///
  /// unbounder knapsack
  /// ///////////////////////////
  ///

  static int unboundedKnapsack(int W, int[] val, int[] wt) {
    int[][] dp = new int[val.length][W + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return unboundedKnapsackRecur(val.length - 1, W, val, wt, dp);
  }

  static int unboundedKnapsackRecur(int i, int w, int[] val, int[] wt, int[][] dp) {
    if (i < 0) return 0;
    if (w < 0) return (int) -1e6;
    if (dp[i][w] != -1) return dp[i][w];
    int notPick = unboundedKnapsackRecur(i - 1, w, val, wt, dp);
    int pick = w >= wt[i] ? val[i] + unboundedKnapsackRecur(i, w - wt[i], val, wt, dp) : (int) -1e6;
    return dp[i][w] = Math.max(pick, notPick);
  }

  /// /////////////////////////
  /// Rod cutting
  /// https://www.geeksforgeeks.org/problems/rod-cutting0840/1
  ///
  /// Given a rod of length n(size of price) inches and an array of prices, price. price[i]
  /// denotes the value of a piece of length i. Determine the maximum value obtainable by cutting up
  // the rod and selling the pieces.
  ///
  /// Input: price[] = [1, 5, 8, 9, 10, 17, 17, 20]
  /// Output: 22
  /// Explanation: The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6,
  // i.e., 5+17=22.
  /// /////////////////////////
  public static int cutRod(int[] price) {
    int[][] dp = new int[price.length][price.length + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return cutRodRecur(price.length - 1, price.length, price, dp);
  }

  public static int cutRodRecur(int i, int N, int[] price, int[][] dp) {
    int rodLen = i + 1;
    if (i == 0) {
      return N * price[0];
    }
    if (dp[i][N] != -1) return dp[i][N];
    int notTake = cutRodRecur(i - 1, N, price, dp);
    int take = (int) -1e9;
    if (rodLen <= N) take = price[i] + cutRodRecur(i, N - rodLen, price, dp);
    return dp[i][N] = Math.max(take, notTake);
  }

  public static int cutRodTab(int[] price) {
    int n = price.length;
    int[][] dp = new int[n][n + 1];
    for (int N = 0; N <= n; N++) {
      dp[0][N] = N * price[0];
    }

    for (int i = 1; i < n; i++) {
      int rodLen = i + 1;
      for (int N = 0; N <= n; N++) {
        int notTake = dp[i - 1][N];
        int take = (int) -1e9;
        if (rodLen <= N) take = price[i] + dp[i][N - rodLen];
        dp[i][N] = Math.max(take, notTake);
      }
    }
    return dp[n - 1][n];
  }

  /// /////////////////////////
  public static int coinChange(int[] coins, int amount) {
    int[][] dp = new int[coins.length][amount + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    int res = coinChangeRecur(coins, amount, coins.length - 1, dp);
    return res == 1e9 ? -1 : res;
  }

  private static int coinChangeRecur(int[] coins, int amount, int i, int[][] dp) {
    int defaultVal = (int) 1e9;
    if (i == 0) {
      if (amount % coins[i] == 0) {
        return amount / coins[i];
      }
      return defaultVal;
    }
    if (i < 0 || amount < 0) return defaultVal;
    if (dp[i][amount] != -1) return dp[i][amount];
    // not taking current coin, so moving to previous coint
    int notTake = coinChangeRecur(coins, amount, i - 1, dp);
    int take = defaultVal;
    if (coins[i] <= amount) {
      // taking the coin, this add 1 and remain at same pos to take more of it.
      take = 1 + coinChangeRecur(coins, amount - coins[i], i, dp);
    }
    return dp[i][amount] = Math.min(take, notTake);
  }

  public static int coinChangeTab(int[] coins, int amount) {
    int[][] dp = new int[coins.length][amount + 1];
    int defaultVal = (int) 1e9;

    for (int i = 0; i <= amount; i++) {
      dp[0][i] = i % coins[0] == 0 ? i / coins[0] : defaultVal;
    }

    for (int i = 1; i < coins.length; i++) {
      for (int j = 0; j <= amount; j++) {
        int notTake = dp[i - 1][j];
        int take = defaultVal;
        if (coins[i] <= j) {
          take = 1 + dp[i][j - coins[i]];
        }
        dp[i][j] = Math.min(take, notTake);
      }
    }
    // Return the result or -1 if the amount cannot be formed
    return dp[coins.length - 1][amount] == defaultVal ? -1 : dp[coins.length - 1][amount];
  }

  public static int coinChangeTabDifferent(int[] coins, int amount) {
    // This approach allocates a dummy row at the top filled with very large values.
    // This makes the base case handling non-issue.
    int[][] dp = new int[coins.length + 1][amount + 1];
    int defaultVal = (int) 1e9;

    for (int j = 1; j <= amount; j++) {
      dp[0][j] = defaultVal;
    }

    for (int i = 1; i <= coins.length; i++) {
      for (int j = 1; j <= amount; j++) {
        int notTake = dp[i - 1][j];
        int take = defaultVal;
        if (coins[i - 1] <= j) {
          take = 1 + dp[i][j - coins[i - 1]];
        }
        dp[i][j] = Math.min(take, notTake);
      }
    }
    return dp[coins.length][amount];
  }

  /// ////////////////////

  /** Coin change 2. */
  public static int change(int amount, int[] coins) {
    int[][] dp = new int[coins.length][amount + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return changeRecur(coins, amount, coins.length - 1, dp);
  }

  private static int changeRecur(int[] coins, int target, int i, int[][] dp) {
    if (i == 0) {
      return target % coins[0] == 0 ? 1 : 0;
    }
    if (dp[i][target] != -1) return dp[i][target];
    int notTake = changeRecur(coins, target, i - 1, dp);
    int take = 0;
    if (coins[i] <= target) {
      take = changeRecur(coins, target - coins[i], i, dp);
    }
    return dp[i][target] = take + notTake;
  }

  public static int changeTable(int amount, int[] coins) {
    int[][] dp = new int[coins.length][amount + 1];

    for (int i = 0; i <= amount; i++) {
      dp[0][i] = i % coins[0] == 0 ? 1 : 0;
    }

    for (int i = 1; i < coins.length; i++) {
      for (int j = 0; j <= amount; j++) {
        int notTake = dp[i - 1][j];
        int take = 0;
        if (j - coins[i] >= 0) take = dp[i][j - coins[i]];
        dp[i][j] = notTake + take;
      }
    }
    return dp[coins.length - 1][amount];
  }

  /// ////////////////////

  /** https://leetcode.com/problems/target-sum/description/ nums = [1,1,1,1,1], target = 3 */
  public static int findTargetSumWays(int[] nums, int target) {
    Map<String, Integer> memo = new HashMap<>();
    return findTargetSumWaysRecur(nums, target, nums.length - 1, memo);
  }

  private static int findTargetSumWaysRecur(
      int[] nums, int target, int i, Map<String, Integer> memo) {
    if (i < 0) {
      return target == 0 ? 1 : 0;
    }
    var key = "%d,%d".formatted(i, target);
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    int negative = findTargetSumWaysRecur(nums, target - nums[i], i - 1, memo);
    int plus = findTargetSumWaysRecur(nums, target + nums[i], i - 1, memo);
    int sum = plus + negative;
    memo.put(key, sum);
    return sum;
  }

  /// /////////////////////////////
  /// https://leetcode.com/problems/split-array-largest-sum/
  /// Given an integer array nums and an integer k, split nums into k non-empty subarrays such that
  /// the largest sum of any subarray is minimized.
  /// Return the minimized largest sum of the split.
  ///
  /// Input: nums = [7,2,5,10,8], k = 2
  /// Output: 18
  /// /////////////////////////////
  public static List<List<List<Integer>>> splitArray(int[] nums, int k) {
    List<List<List<Integer>>> result = new ArrayList<>();
    backtrack(nums, 0, k, new ArrayList<>(), result);
    return result;
  }

  private static void backtrack(
      int[] nums, int start, int k, List<List<Integer>> current, List<List<List<Integer>>> result) {

    if (k == 0 && start == nums.length) {
      // Successfully split into k parts
      result.add(current.stream().map(List::copyOf).toList());
      return;
    }

    if (k == 0 || start == nums.length) {
      // Either used up all partitions or all elements prematurely
      return;
    }

    // Not enough elements left
    if (nums.length - start < k) return;

    for (int end = start + 1; end <= nums.length; end++) {
      List<Integer> sub = Arrays.stream(nums, start, end).boxed().toList();
      current.add(sub);
      backtrack(nums, end, k - 1, current, result);
      current.removeLast();
    }
  }

  /// /////////////////////////////////////
  /// Same as above, just maintain sums of sub arrays
  public static List<List<Integer>> splitArraySum(int[] nums, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrackSum(nums, 0, k, new ArrayList<>(), result);
    return result;
  }

  private static void backtrackSum(
      int[] nums, int start, int k, List<Integer> current, List<List<Integer>> result) {

    if (k == 0 && start == nums.length) {
      // Successfully split into k parts
      result.add(List.copyOf(current));
      return;
    }

    if (k == 0 || start == nums.length) {
      // Either used up all partitions or all elements prematurely
      return;
    }

    // Not enough elements left
    if (nums.length - start < k) return;

    for (int end = start + 1; end <= nums.length; end++) {
      int sum = Arrays.stream(nums, start, end).sum();

      current.add(sum);
      backtrackSum(nums, end, k - 1, current, result);
      current.removeLast();
    }
  }

  /// ///////////
  /// Only maintain max sum of the sub array combination
  public static List<Integer> splitArrayMinSum(int[] nums, int k) {
    List<Integer> result = new ArrayList<>();
    backtrackMinSum(nums, 0, k, new ArrayList<>(), result);
    return result;
  }

  private static void backtrackMinSum(
      int[] nums, int start, int k, List<Integer> current, List<Integer> result) {

    if (k == 0 && start == nums.length) {
      // Successfully split into k parts
      result.add(Collections.max(current));
      return;
    }

    if (k == 0 || start == nums.length) {
      // Either used up all partitions or all elements prematurely
      return;
    }

    // Not enough elements left
    if (nums.length - start < k) return;

    int sum = 0;
    for (int end = start; end < nums.length; end++) {
      sum += nums[end];

      current.add(sum);
      backtrackMinSum(nums, end + 1, k - 1, current, result);
      current.removeLast();
    }
  }
}
