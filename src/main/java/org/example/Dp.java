package org.example;

import java.util.Arrays;

public class Dp {

  public static void main(String[] args) {
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
    System.out.println(shortestCommonSuperSequence("brute", "groot"));
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
}
