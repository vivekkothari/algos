package org.example;

import java.util.ArrayList;
import java.util.List;

class Backtracking {

  public static void main(String[] args) {
    System.out.println(subsets(new int[] {}));
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
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
  }

  private static void backtrack(
      List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
      tempList.add(nums[i]);
      backtrack(list, tempList, nums, i + 1);
      tempList.removeLast();
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
