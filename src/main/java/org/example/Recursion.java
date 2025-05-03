package org.example;

import java.util.*;
import java.util.Arrays;

class Recursion {

  public static void main(String[] args) {
    System.out.println(uniqueWays(2, 2));
    System.out.println(uniqueWaysDpSpaceOptimised(2, 2));
    System.out.println(uniqueWaysDp(10, 10));
    System.out.println(uniqueWaysDpSpaceOptimised(10, 10));
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
    long[] prev = new long[n];
    for (int i = 0; i < m; i++) {
      long[] curr = new long[n];
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
}
