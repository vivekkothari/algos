package org.example;

import java.util.ArrayList;
import java.util.List;

class Backtracking {

  public static void main(String[] args) {
    //    System.out.println(subsets(new int[] {}));
    //    System.out.println(permute(new int[] {1, 2, 3}));
    //    System.out.println(findAllBinaryStrings(7));
    //    System.out.println(permute("ABC", true));
    //    System.out.println(permute("ABC", false));
    System.out.println(generateParenthesis(5));
  }

  /**
   * https://leetcode.com/problems/generate-parentheses/?envType=daily-question&envId=2025-02-19
   *
   * <pre>
   *   Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
   * Example 1:
   *
   * Input: n = 3
   * Output: ["((()))","(()())","(())()","()(())","()()()"]
   * Example 2:
   *
   * Input: n = 1
   * Output: ["()"]
   * </pre>
   */
  public static List<String> generateParenthesis(int n) {
    var res = new ArrayList<String>();
    generateParenthesis(res, 0, 0, new StringBuilder(), n);
    return res;
  }

  static void generateParenthesis(List<String> res, int open, int closed, StringBuilder sb, int n) {
    if (open == closed && open == n) {
      res.add(sb.toString());
      return;
    }
    if (open < n) {
      sb.append('(');
      generateParenthesis(res, open + 1, closed, sb, n);
      sb.deleteCharAt(sb.length() - 1);
    }
    if (closed < open) {
      sb.append(')');
      generateParenthesis(res, open, closed + 1, sb, n);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  /**
   * https://leetcode.com/problems/subsets/description/
   *
   * <pre>
   *   Given an integer array nums of unique elements, return all possible
   * subsets
   *  (the power set).
   *
   * The solution set must not contain duplicate subsets. Return the solution in any order.
   *
   * Example 1:
   *
   * Input: nums = [1,2,3]
   * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
   * Example 2:
   *
   * Input: nums = [0]
   * Output: [[],[0]]
   *
   * </pre>
   */
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    java.util.Arrays.sort(nums);
    subsets(list, new ArrayList<>(), nums, 0);
    return list;
  }

  private static void subsets(
      List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
      tempList.add(nums[i]);
      subsets(list, tempList, nums, i + 1);
      tempList.removeLast();
    }
  }

  /**
   * https://leetcode.com/problems/permutations/?envType=daily-question&envId=2025-02-19 Given an
   * array nums of distinct integers, return all the possible permutations . You can return the
   * answer in any order.
   *
   * <p>Example 1:
   *
   * <p>Input: nums = [1,2,3] Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]] Example 2:
   *
   * <p>Input: nums = [0,1] Output: [[0,1],[1,0]] Example 3:
   *
   * <p>Input: nums = [1] Output: [[1]]
   */
  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    permute(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
  }

  public static void permute(
      List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
    if (nums.length == tempList.size()) {
      list.add(new ArrayList<>(tempList));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) continue;
      used[i] = true;
      tempList.add(nums[i]); // choose an element
      permute(list, tempList, nums, used); // explore more options
      used[i] = false;
      tempList.removeLast(); // undo the last choice
    }
  }

  public static List<String> permute(String string, boolean reuseAllowed) {
    List<String> res = new ArrayList<>();
    var used = new boolean[string.length()];
    permute(res, new StringBuilder(), string, used, reuseAllowed);
    return res;
  }

  private static void permute(
      List<String> res, StringBuilder sb, String s, boolean[] used, boolean reuseAllowed) {
    if (sb.length() == s.length()) {
      res.add(sb.toString());
      return;
    }
    char[] charArray = s.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      if (used[i] && !reuseAllowed) continue;
      used[i] = true;
      var c = charArray[i];
      sb.append(c);
      permute(res, sb, s, used, reuseAllowed);
      used[i] = false;
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  /**
   * Print All Binary Strings of Length N Problem: Given an integer N, print all binary strings of
   * length N. Example: N = 2 → { "00", "01", "10", "11" }
   *
   * <p>✅ Key Concept: Either choose 0 or 1 at each step.
   */
  public static List<String> findAllBinaryStrings(int n) {
    List<String> res = new ArrayList<>();
    findAllBinaryStrings(res, new StringBuilder(), n);
    return res;
  }

  private static void findAllBinaryStrings(List<String> list, StringBuilder sb, int n) {
    if (sb.length() == n) {
      list.add(sb.toString());
      return;
    }
    for (var c : "01".toCharArray()) {
      sb.append(c);
      findAllBinaryStrings(list, sb, n);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  /**
   * https://leetcode.com/problems/find-the-punishment-number-of-an-integer/description/?envType=daily-question&envId=2025-02-15
   *
   * <pre>
   *   Given a positive integer n, return the punishment number of n.
   *
   * The punishment number of n is defined as the sum of the squares of all integers i such that:
   *
   * 1 <= i <= n
   * The decimal representation of i * i can be partitioned into contiguous substrings such that the sum of the integer values of these substrings equals i.
   *
   *
   * Example 1:
   *
   * Input: n = 10
   * Output: 182
   * Explanation: There are exactly 3 integers i in the range [1, 10] that satisfy the conditions in the statement:
   * - 1 since 1 * 1 = 1
   * - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 and 1 with a sum equal to 8 + 1 == 9.
   * - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 and 0 with a sum equal to 10 + 0 == 10.
   * Hence, the punishment number of 10 is 1 + 81 + 100 = 182
   * Example 2:
   *
   * Input: n = 37
   * Output: 1478
   * Explanation: There are exactly 4 integers i in the range [1, 37] that satisfy the conditions in the statement:
   * - 1 since 1 * 1 = 1.
   * - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
   * - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
   * - 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
   * Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478
   * </pre>
   */
  public static int punishmentNumber(int n) {
    int punishmentNum = 0;

    // Iterate through numbers in range [1, n]
    for (int currentNum = 1; currentNum <= n; currentNum++) {
      int squareNum = currentNum * currentNum;

      // Check if valid partition can be found and add squared number if so
      if (canPartition(Integer.toString(squareNum), currentNum)) {
        punishmentNum += squareNum;
      }
    }
    return punishmentNum;
  }

  public static boolean canPartition(String stringNum, int target) {
    // Valid Partition Found
    if (stringNum.isEmpty() && target == 0) {
      return true;
    }

    // Invalid Partition Found
    if (target < 0) {
      return false;
    }

    // Recursively check all partitions for a valid partition
    for (int index = 0; index < stringNum.length(); index++) {
      String left = stringNum.substring(0, index + 1);
      String right = stringNum.substring(index + 1);
      int leftNum = Integer.parseInt(left);

      if (canPartition(right, target - leftNum)) {
        return true;
      }
    }
    return false;
  }
}
