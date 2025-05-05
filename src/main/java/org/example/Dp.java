package org.example;

import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Dp {

  public static void main(String[] args) {
    //    maxCoins(new int[] {3, 1, 5, 8});
    System.out.println(pushDominoes(".L.R...LR..L.."));
    //    longestBitonicSequence(5, new int[] {1, 2, 5, 3, 2});
    //    System.out.println(longestStrChain(new String[] {"a", "b", "ba", "bca", "bda", "bdca"}));
    //    System.out.println(largestDivisibleSubset(new int[] {3, 4, 16, 8}));
    //    System.out.println(isMatch("aa", "*"));
    //    System.out.println(isMatch("abcabczzzde", "*abc???de*"));
    //        System.out.println(isMatch("aa", "a"));
    //    System.out.println(isMatch("aab", "c*a*b"));
    //    System.out.println(isMatch("cb", "?a"));
    //    System.out.println(isMatch("adceb", "*a*b"));
    //    System.out.println(longestPalindromeSubseq("bbabcbcab"));
    //    System.out.println(longestCommonSubsequence("abcd", "aced"));
    //    System.out.println(longestCommonSubstring("abcd", "abd"));
    //    System.out.println(getLongestCommonSubstring("abcd", "abd"));
    //    System.out.println(getLcs("abcd", "aced"));
    //    System.out.println(getLcsTabulation("abcd", "aczd"));
    //    System.out.println(longestCommonSubsequenceTabulation("abcd", "ace"));
    //    System.out.println(longestCommonSubsequenceTabulationOptimised("abcd", "ace"));
    //    System.out.println(getLcs("vflkrovy", "koaecjotj"));
    //    System.out.println(minOperations("vflkrovy", "koaecjotj"));
    //    System.out.println(shortestCommonSuperSequence("brute", "groot"));
  }

  /// /////////////////////////
  /// https://leetcode.com/problems/longest-common-subsequence/
  /// /////////////////////////
  public static int longestCommonSubsequence(String text1, String text2) {
    int[][] dp = new int[text1.length()][text2.length()];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return lcs(text1.length() - 1, text2.length() - 1, text1, text2, dp);
  }

  private static int lcs(int i1, int i2, String text1, String text2, int[][] dp) {
    if (i1 < 0 || i2 < 0) return 0;
    if (dp[i1][i2] != -1) return dp[i1][i2];
    if (text1.charAt(i1) == text2.charAt(i2)) {
      return dp[i1][i2] = 1 + lcs(i1 - 1, i2 - 1, text1, text2, dp);
    }
    return dp[i1][i2] =
        Math.max(lcs(i1 - 1, i2, text1, text2, dp), lcs(i1, i2 - 1, text1, text2, dp));
  }

  public static int longestCommonSubsequenceTabulation(String s, String t) {
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[n][m];
  }

  public static int longestCommonSubsequenceTabulationOptimised(String s, String t) {
    int n = s.length();
    int m = t.length();
    int[] prev = new int[m + 1];
    for (int i = 1; i <= n; i++) {
      int[] curr = new int[m + 1];
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          curr[j] = 1 + prev[j - 1];
        } else {
          curr[j] = Math.max(prev[j], curr[j - 1]);
        }
      }
      prev = curr;
    }
    return prev[m];
  }

  /// /////////////////////////
  /// Uses a lot of space. As we are using 2-D string array.
  /// ///////////////////////
  public static String getLcs(String text1, String text2) {
    String[][] dp = new String[text1.length()][text2.length()];
    return getLcs(text1.length() - 1, text2.length() - 1, text1, text2, dp);
  }

  private static String getLcs(int i1, int i2, String text1, String text2, String[][] dp) {
    if (i1 < 0 || i2 < 0) return "";
    if (dp[i1][i2] != null) return dp[i1][i2];

    if (text1.charAt(i1) == text2.charAt(i2)) {
      dp[i1][i2] = getLcs(i1 - 1, i2 - 1, text1, text2, dp) + text1.charAt(i1);
    } else {
      String left = getLcs(i1 - 1, i2, text1, text2, dp);
      String right = getLcs(i1, i2 - 1, text1, text2, dp);
      dp[i1][i2] = left.length() > right.length() ? left : right;
    }
    return dp[i1][i2];
  }

  public static String getLcsTabulation(String s, String t) {
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    char[] res = new char[dp[n][m]];
    int i = n, j = m, k = res.length - 1;
    while (i > 0 || j > 0) {
      if (s.charAt(i - 1) == t.charAt(j - 1)) {
        res[k] = s.charAt(i - 1);
        i--;
        j--;
        k--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }
    return new String(res);
  }

  /// ///////////////////////
  /// https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
  /// ///////////////////////
  public static int longestCommonSubstring(String s, String t) {
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];
    int res = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        // only if it matches, populate dp table.
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
          res = Math.max(res, dp[i][j]);
        }
      }
    }
    return res;
  }

  public static String getLongestCommonSubstring(String s, String t) {
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];
    int len = 0;
    int endIndex = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        // only if it matches, populate dp table.
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
          if (dp[i][j] > len) {
            len = dp[i][j];
            endIndex = i;
          }
        }
      }
    }
    return s.substring(endIndex - len, endIndex);
  }

  /// ////////////////////////////
  /// Longest palindromic subsequence
  /// https://leetcode.com/problems/longest-palindromic-subsequence/
  /// ////////////////////////////

  public static int longestPalindromeSubseq(String s) {
    return longestCommonSubsequence(s, new StringBuilder(s).reverse().toString());
  }

  /// ////////////////////////////
  /// Minimum number of deletions and insertions
  /// https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1
  /// ////////////////////////////
  public static int minOperations(String s1, String s2) {
    int lcsLen = longestCommonSubsequenceTabulationOptimised(s1, s2);
    return s1.length() + s2.length() - 2 * lcsLen;
  }

  /// ////////////////////////////
  /// 1092 shortest common supersequence
  /// https://leetcode.com/problems/shortest-common-supersequence/
  /// ////////////////////////////
  public static String shortestCommonSuperSequence(String s, String t) {
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    int lcsLen = dp[n][m];
    char[] res = new char[s.length() + t.length() - lcsLen];
    int i = n, j = m, k = res.length - 1;
    // follow DP table to get till either string exhausts.
    while (i > 0 && j > 0) {
      if (s.charAt(i - 1) == t.charAt(j - 1)) {
        res[k] = s.charAt(i - 1);
        i--;
        j--;
        k--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        res[k] = s.charAt(i - 1);
        i--;
        k--;
      } else {
        res[k] = t.charAt(j - 1);
        k--;
        j--;
      }
    }
    // look at some of the chars left
    while (i > 0) {
      res[k] = s.charAt(i - 1);
      i--;
      k--;
    }
    // look at some of the chars left
    while (j > 0) {
      res[k] = t.charAt(j - 1);
      k--;
      j--;
    }
    return new String(res);
  }

  /// //////////////////
  /// https://leetcode.com/problems/distinct-subsequences/
  /// Given two strings s and t, return the number of distinct subsequences of s which equals t.
  ///
  /// The test cases are generated so that the answer fits on a 32-bit signed integer.
  /// Example 1:
  ///
  /// Input: s = "rabbbit", t = "rabbit"
  /// Output: 3
  /// Explanation:
  /// As shown below, there are 3 ways you can generate "rabbit" from s.
  /// Input: s = "babgbag", t = "bag"
  /// Output: 5
  /// Explanation:
  /// //////////////////

  public static int numDistinct(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return numDistinct(s.length(), t.length(), s, t, dp);
  }

  public static int numDistinct(int si, int ti, String s, String t, int[][] dp) {
    if (ti == 0) return 1;
    if (si == 0 || si < ti) return 0;
    if (dp[si][ti] != -1) return dp[si][ti];
    if (s.charAt(si - 1) == t.charAt(ti - 1)) {
      return dp[si][ti] = numDistinct(si - 1, ti - 1, s, t, dp) + numDistinct(si - 1, ti, s, t, dp);
    } else {
      return dp[si][ti] = numDistinct(si - 1, ti, s, t, dp);
    }
  }

  public static int numDistinctTabulation(String s, String t) {
    long[][] dp = new long[s.length() + 1][t.length() + 1];
    for (int i = 0; i <= s.length(); i++) {
      dp[i][0] = 1;
    }
    for (int si = 1; si <= s.length(); si++) {
      for (int ti = 1; ti <= t.length(); ti++) {
        if (s.charAt(si - 1) == t.charAt(ti - 1)) {
          dp[si][ti] = dp[si - 1][ti - 1] + dp[si - 1][ti];
        } else {
          dp[si][ti] = dp[si - 1][ti];
        }
      }
    }
    return Math.toIntExact(dp[s.length()][t.length()]);
  }

  /// //////////////////////////////
  /// https://leetcode.com/problems/edit-distance/
  /// Given two strings word1 and word2, return the minimum number of operations required to convert
  // word1 to word2.
  ///
  /// You have the following three operations permitted on a word:
  ///
  /// Insert a character
  /// Delete a character
  /// Replace a character
  /// Example 1:
  /// Input: word1 = "horse", word2 = "ros"
  /// Output: 3
  /// Explanation:
  /// horse -> rorse (replace 'h' with 'r')
  /// rorse -> rose (remove 'r')
  /// rose -> ros (remove 'e')
  /// //////////////////////////////
  public static int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return minDistanceRecur(word1.length() - 1, word2.length() - 1, word1, word2, dp);
  }

  private static int minDistanceRecur(int i, int j, String word1, String word2, int[][] dp) {
    // if word1 exhausts, return j+1 to indicate j+1 insert operations
    if (i < 0) {
      return j + 1;
    }
    if (j < 0) {
      return i + 1;
    }
    if (dp[i][j] != -1) return dp[i][j];
    if (word1.charAt(i) == word2.charAt(j)) {
      return dp[i][j] = minDistanceRecur(i - 1, j - 1, word1, word2, dp);
    } else {
      int delete = 1 + minDistanceRecur(i - 1, j, word1, word2, dp);
      int replace = 1 + minDistanceRecur(i - 1, j - 1, word1, word2, dp);
      int add = 1 + minDistanceRecur(i, j - 1, word1, word2, dp);
      return dp[i][j] = Math.min(delete, Math.min(replace, add));
    }
  }

  /// ////////////////////
  /// https://leetcode.com/problems/wildcard-matching/description/
  /// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support
  /// for '?' and '*' where:
  /// '?' Matches any single character.
  /// '*' Matches any sequence of characters (including the empty sequence).
  /// The matching should cover the entire input string (not partial).
  ///
  /// Input: s = "aa", p = "a"
  /// Output: false
  /// Explanation: "a" does not match the entire string "aa".
  ///
  /// Input: s = "aa", p = "*"
  /// Output: true
  /// Explanation: '*' matches any sequence.
  ///
  /// Input: s = "cb", p = "?a"
  /// Output: false
  /// Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
  /// ////////////////////

  public static boolean isMatch(String s, String p) {
    int[][] dp = new int[p.length()][s.length()];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return isMatch(p.length() - 1, s.length() - 1, p, s, dp);
  }

  private static boolean isMatch(int i, int j, String p, String s, int[][] dp) {
    // base case
    if (i < 0 && j < 0) {
      // both strings exhausted
      return true;
    }
    if (i < 0) return false;
    if (j < 0) {
      for (int k = 0; k <= i; k++) {
        if (p.charAt(k) != '*') return false;
      }
      return true;
    }
    if (dp[i][j] != -1) return dp[i][j] == 1;
    if (p.charAt(i) == s.charAt(j) || p.charAt(i) == '?') {
      boolean match = isMatch(i - 1, j - 1, p, s, dp);
      dp[i][j] = match ? 1 : 0;
      return match;
    } else {
      if (p.charAt(i) == '*') {
        boolean match = isMatch(i - 1, j, p, s, dp) || isMatch(i, j - 1, p, s, dp);
        dp[i][j] = match ? 1 : 0;
        return match;
      } else {
        dp[i][j] = 0;
        return false;
      }
    }
  }

  /// ///////////////////////////
  /// https://leetcode.com/problems/largest-divisible-subset/description/
  /// //////////////
  public static List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int[] dp = new int[n];
    // This simply stores the indices
    int[] hash = new int[n];
    Arrays.fill(dp, 1);
    Arrays.fill(hash, -1);

    int lastIndex = 0;
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
          hash[i] = j;
        }
      }
      if (dp[i] > dp[lastIndex]) {
        lastIndex = i;
      }
    }

    List<Integer> result = new LinkedList<>();
    for (int i = lastIndex; i >= 0; i = hash[i]) {
      result.addFirst(nums[i]);
    }
    return result;
  }

  /// ///////////////////////////
  /// https://leetcode.com/problems/longest-string-chain/
  /// ///////////////////////////
  public static int longestStrChain(String[] words) {
    Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());
    int[] dp = new int[words.length];
    Arrays.fill(dp, 1);
    int max = 1;
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < i; j++) {
        if (isPossible(words[i], words[j])) {
          dp[i] = Math.max(1 + dp[j], dp[i]);
        }
      }
      max = Math.max(max, dp[i]);
    }
    return max;
  }

  private static boolean isPossible(String w1, String w2) {
    // should be exactly one length diff.
    if (w1.length() != w2.length() + 1) return false;

    int i = 0, j = 0;
    boolean skipped = false;

    while (i < w1.length() && j < w2.length()) {
      if (w1.charAt(i) == w2.charAt(j)) {
        i++;
        j++;
      } else {
        if (skipped) return false; // already skipped once before
        skipped = true;
        i++; // skip one char in w1
      }
    }

    // After the loop, we must have matched all of w2
    return true;
  }

  /// ////////////////////////////////
  /// https://www.naukri.com/code360/problems/longest-bitonic-sequence_1062688
  ///
  /// https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
  /// GFG has a tweak where we only want to include if its strictly increasing then decreasing.
  /// ////////////////////////////////

  public static int longestBitonicSequence(int n, int[] nums) {
    int[] fwDp = new int[n];
    int[] revDp = new int[n];

    for (int i = 0; i < n; i++) {
      fwDp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          fwDp[i] = Math.max(1 + fwDp[j], fwDp[i]);
        }
      }
    }

    for (int i = n - 1; i >= 0; i--) {
      revDp[i] = 1;
      for (int j = n - 1; j > i; j--) {
        if (nums[i] > nums[j]) {
          revDp[i] = Math.max(1 + revDp[j], revDp[i]);
        }
      }
    }
    int max = 0;
    for (int i = 0; i < fwDp.length; i++) {
      if (fwDp[i] > 1 && revDp[i] > 1) { // this line is needed for GFG problem
        max = Math.max(max, fwDp[i] + revDp[i] - 1);
      }
    }
    return max;
  }

  /// ///////////////////////////
  /// https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
  /// ///////////////////////////

  static int matrixMultiplication(int[] arr) {
    int[][] dp = new int[arr.length][arr.length];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    return matrixMultiplicationRecur(1, arr.length - 1, arr, dp);
  }

  private static int matrixMultiplicationRecur(int i, int j, int[] arr, int[][] dp) {
    if (i >= j) return 0;
    if (dp[i][j] != -1) return dp[i][j];
    int cost = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int currentCost = arr[i - 1] * arr[k] * arr[j];
      int otherCosts =
          matrixMultiplicationRecur(i, k, arr, dp) + matrixMultiplicationRecur(k + 1, j, arr, dp);
      cost = Math.min(cost, currentCost + otherCosts);
    }
    return dp[i][j] = cost;
  }

  static int matrixMultiplicationTabulation(int[] arr) {
    int N = arr.length;
    int[][] dp = new int[N][N];

    // i goes from N-1 to 1 because in recursion we went from 1 to N-1
    for (int i = N - 1; i >= 1; i--) {
      // we want to consider only the elements not yet included in i's range, thus i+1 and upto N.
      for (int j = i + 1; j < N; j++) {
        int cost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          int currentCost = arr[i - 1] * arr[k] * arr[j];
          int otherCosts = dp[i][k] + dp[k + 1][j];
          cost = Math.min(cost, currentCost + otherCosts);
        }
        dp[i][j] = cost;
      }
    }
    return dp[1][N - 1];
  }

  /// /////////////////////////////
  /// https://leetcode.com/problems/minimum-cost-to-cut-a-stick
  /// /////////////////////////////
  public static int minCost(int n, int[] cuts) {
    // sorting is needed to make sure we can divide the arrays without any dependencies.
    int originalCutsLen = cuts.length;
    int[][] dp = new int[originalCutsLen][originalCutsLen];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    Arrays.sort(cuts);
    // Add 0 to the first, indicating 0 len and n at the end denoting last case.
    List<Integer> newCuts = new ArrayList<>(Arrays.stream(cuts).boxed().toList());
    newCuts.addFirst(0);
    newCuts.addLast(n);
    cuts = newCuts.stream().mapToInt(i -> i).toArray();
    return minCostRecur(1, originalCutsLen, cuts, dp);
  }

  private static int minCostRecur(int i, int j, int[] cuts, int[][] dp) {
    // we do i > j and not i >= j here because if we are at i==j, we still need to commute the cost.
    if (i > j) return 0;
    if (dp[i - 1][j - 1] != -1) return dp[i - 1][j - 1];
    int cost = (int) 1e8;
    for (int k = i; k <= j; k++) {
      int currentCost = cuts[j + 1] - cuts[i - 1];
      int otherCost = minCostRecur(i, k - 1, cuts, dp) + minCostRecur(k + 1, j, cuts, dp);
      cost = Math.min(cost, currentCost + otherCost);
    }
    return dp[i - 1][j - 1] = cost;
  }

  /// /////////////////////////////
  /// https://leetcode.com/problems/burst-balloons/
  ///
  /// Input: nums = [3,1,5,8]
  /// Output: 167
  /// Explanation:
  /// nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
  /// coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
  /// /////////////////////////////
  public static int maxCoins(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n + 1][n + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }
    int[] extendedBalloon = new int[n + 2];
    extendedBalloon[0] = 1;
    System.arraycopy(nums, 0, extendedBalloon, 1, n);
    extendedBalloon[extendedBalloon.length - 1] = 1;
    return maxCoinsRecur(1, n, extendedBalloon, dp);
  }

  private static int maxCoinsRecur(int i, int j, int[] nums, int[][] dp) {
    if (i > j) {
      return 0;
    }
    if (dp[i][j] != -1) return dp[i][j];
    int max = 0;
    for (int k = i; k <= j; k++) {
      int coins = nums[i - 1] * nums[k] * nums[j + 1];
      // add i <= k - 1 to prevent the base case recursion to get triggered
      int left = i <= k - 1 ? maxCoinsRecur(i, k - 1, nums, dp) : 0;
      // add k + 1 <= j to prevent the base case recursion to get triggered
      int right = k + 1 <= j ? maxCoinsRecur(k + 1, j, nums, dp) : 0;
      int otherCoins = left + right;
      max = Math.max(max, coins + otherCoins);
    }
    return dp[i][j] = max;
  }

  /// //////////////////////////////////
  ///
  // https://leetcode.com/problems/push-dominoes?envType=company&envId=google&favoriteSlug=google-thirty-days
  /// ///////////////////////////////////////////////
  public static String pushDominoes(String dominoes) {
    char[] dom = dominoes.toCharArray();
    Queue<int[]> q = new LinkedList<>();

    char STANDING = '.';
    char LEFT = 'L';
    char RIGHT = 'R';
    for (int i = 0; i < dom.length; i++) {
      if (dom[i] != STANDING) {
        q.add(new int[] {i, dom[i]});
      }
    }
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      int i = curr[0];
      char d = (char) curr[1];
      if (d == LEFT && i > 0 && dom[i - 1] == STANDING) {
        q.add(new int[] {i - 1, LEFT});
        dom[i - 1] = LEFT;
      } else if (d == RIGHT) {
        if (i + 1 < dom.length && dom[i + 1] == STANDING) {
          if (i + 2 < dom.length && dom[i + 2] == LEFT) {
            q.poll();
          } else {
            q.add(new int[] {i + 1, RIGHT});
            dom[i + 1] = RIGHT;
          }
        }
      }
    }

    return new String(dom);
  }
}
